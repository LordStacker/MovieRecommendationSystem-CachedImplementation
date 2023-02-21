package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Card;
import dk.easv.be.Movie;
import dk.easv.util.MovieFetcher;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.tv.TvSeries;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InfoScreenController implements Initializable {
    public Label movieName;
    public Label movieDescription;
    public AnchorPane imagePane;
    public MFXScrollPane mainScrollPane;
    private Card card;
    private final MovieFetcher movieFetcher = MovieFetcher.getInstance();
    private Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void setCard(Card card) {
        this.card = card;
        setScene();
    }

    private void setScene() {
        getMovie(new Movie(0, card.getTitle(), card.getYear()));
        stage.setHeight(stage.getHeight()-1);
        stage.setWidth(stage.getWidth()-1);
    }

    @FXML
    private void goBackAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/MainWindow.fxml")));
            Parent parent = fxmlLoader.load();
            MainWindowController mainController = fxmlLoader.getController();
            mainController.setStage(stage);
            stage.setScene(new Scene(parent));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getMovie(Movie movie) {
        stage = Main.getStage();
        movieName.setText(movie.getTitle());

        TmdbSearch.MultiListResultsPage multis = movieFetcher.searchMulti(movie.getTitle());
        mainScrollPane.setPrefWidth(stage.getWidth());
        imagePane.setPrefWidth(stage.getWidth());


        mainScrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            imagePane.setMinHeight(newValue.doubleValue()-300); // magic number probably should be done with a binding or something
        });
        if (multis.getResults().get(0).getMediaType() == Multi.MediaType.MOVIE) {
            MovieDb movieDb = (MovieDb) multis.getResults().get(0);
            movieDescription.setText(movieDb.getOverview());
            imagePane.setStyle(imagePane.getStyle() + "-fx-background-image : url(https://image.tmdb.org/t/p/original" + movieDb.getBackdropPath() + ");" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: top left;" +
                    "-fx-background-repeat: none;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 2, 3);");
        } else if (multis.getResults().get(0).getMediaType() == Multi.MediaType.TV_SERIES) {
            TvSeries tvSeries = (TvSeries) multis.getResults().get(0);
            movieDescription.setText(tvSeries.getOverview());
            imagePane.setStyle(imagePane.getStyle() + "-fx-background-image : url(https://image.tmdb.org/t/p/original" + tvSeries.getBackdropPath() + ");" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: none;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 2, 3);");
        }
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            newValue = newValue.doubleValue() - 20;
            imagePane.setPrefWidth(newValue.doubleValue());
            mainScrollPane.setPrefWidth(newValue.doubleValue());
            movieDescription.setPrefWidth(newValue.doubleValue());
        });
        movieDescription.setWrapText(true);
        movieDescription.setMinHeight(Region.USE_PREF_SIZE);

    }

}
