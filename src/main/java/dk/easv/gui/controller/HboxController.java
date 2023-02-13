package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Card;
import dk.easv.be.Movie;
import dk.easv.util.MovieFetcher;
import info.movito.themoviedbapi.TmdbSearch;
import info.movito.themoviedbapi.model.MovieDb;
import info.movito.themoviedbapi.model.Multi;
import info.movito.themoviedbapi.model.tv.TvSeries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HboxController implements Initializable {
    private final MovieFetcher movieFetcher = MovieFetcher.getInstance();
    private ObservableList<Movie> movies = null;
    @FXML
    private HBox mainHbox;


    private long timerStartMillis = 0;
    private String timerMsg = "";

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void populateHbox(){
        try {
            ObservableList<Node> children =  mainHbox.getChildren();
            for (int i = 0; i < 15; i++) {
                startTimer("Loading card " + i);
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/Card.fxml")));
                Parent parent = loader.load();
                CardController cardController = loader.getController();


                cardController.setCards(new Card(movies.get(i).getTitle(), getMovieImage(movies.get(i)), movies.get(i).getYear()));
                stopTimer();
                children.addAll(parent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setMovieList(ObservableList<Movie> movieList) {
        this.movies = movieList;
        populateHbox();
    }

    private String getMovieImage(Movie movie){
        TmdbSearch.MultiListResultsPage multiListResultsPage = movieFetcher.searchMulti(movie.getTitle());
        Multi.MediaType type;
        if (!multiListResultsPage.getResults().isEmpty()) {
            type = multiListResultsPage.getResults().get(0).getMediaType();
        } else {
            type = null;
        }


        String imageURL = null;
        if (type == Multi.MediaType.MOVIE) {
            MovieDb movieDb = (MovieDb) multiListResultsPage.getResults().get(0);
            imageURL = movieDb.getPosterPath();
        } else if (type == Multi.MediaType.TV_SERIES) {
            TvSeries tvSeries = (TvSeries) multiListResultsPage.getResults().get(0);
            imageURL = tvSeries.getPosterPath();
        }

        return imageURL;
    }
}
