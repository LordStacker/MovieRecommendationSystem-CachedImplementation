package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Movie;
import dk.easv.gui.model.AppModel;
import dk.easv.util.MovieFetcher;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    @FXML
    private Label movieName;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane grdPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private MFXScrollPane mainScrollPane;

    private final AppModel model = AppModel.getInstance();
    private long timerStartMillis = 0;
    private String timerMsg = "";
    private Stage stage;
    private final MovieFetcher movieFetcher = MovieFetcher.getInstance();

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMovies();
    }

    private void initMovies(){
        // Storing map of string and list of movies
        LinkedHashMap<String, ObservableList<Movie>> map = new LinkedHashMap<>();
        // Convert list of TopMovie into list of Movie
        ObservableList<Movie> topMovies = FXCollections.observableArrayList();
        model.getObsTopMoviesSimilarUsers().forEach(topMovie -> topMovies.add(topMovie.getMovie()));
        // put the list into map
        map.put("Popular movies", convertMovieDbToMovie(movieFetcher.getPopularMovies()));
        map.put("Upcoming movies", convertMovieDbToMovie(movieFetcher.getUpcomingMovies()));
        map.put("Top movies you might like", topMovies);
        map.put("Top movies you have seen", model.getObsTopMovieSeen());
        map.put("Top movies you have not seen", model.getObsTopMovieNotSeen());




        MovieDb movieDb = movieFetcher.getPopularMovies().getResults().get((int) (Math.random() * 19)); //


        anchorPane.setStyle("-fx-background-image: url(https://www.themoviedb.org/t/p/original/" + movieDb.getBackdropPath() + ");" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: none;");
        movieName.setText(movieDb.getTitle());
        mainScrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            anchorPane.setMinHeight(newValue.doubleValue()-100); // magic number probably should be done with a binding or something
        });
        // for each key in the map initialize new hbox and set the movies
        Set<String> keys = map.keySet();
        try {
                for (String key : keys){
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/Hbox.fxml")));
                    HBox hBox = loader.load();
                    HboxController hboxController = loader.getController();

                    Label label = new Label(key);
                    label.getStyleClass().add("heading1");
                    mainVBox.getChildren().add(label);

                    hboxController.setMovieList(map.get(key));
                    MFXScrollPane scrollPane = new MFXScrollPane(hBox);
                    scrollPane.setFitToHeight(true);
                    scrollPane.getStyleClass().add("sideScroll");
                    mainVBox.getChildren().add(scrollPane);
                }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setStage(Stage oldStage){
        this.stage = oldStage;
        stage.setMinHeight(mainScrollPane.getPrefHeight());
        stage.setMinWidth(mainScrollPane.getMinWidth());
        stage.setHeight(oldStage.getHeight());
        stage.setWidth(oldStage.getWidth());
    }


    private ObservableList<Movie> convertMovieDbToMovie(MovieResultsPage movieResultsPage){
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        for (MovieDb movieDb : movieResultsPage.getResults()) {
            movies.add(new Movie(0, movieDb.getTitle(), Integer.parseInt(movieDb.getReleaseDate().split("-")[0])));
        }
        return movies;
    }
}
