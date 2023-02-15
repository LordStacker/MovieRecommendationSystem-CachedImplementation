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
            for (int i = 0; i < 25; i++) {
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


    private String getMovieImage(Movie movie) {
        String imageURL = null;
        TmdbSearch.MultiListResultsPage multis = movieFetcher.searchMulti(movie.getTitle());

        if (!multis.getResults().isEmpty()){
            imageURL = getMovieImageMoreAccurate(movie, multis);
        } else if (movie.getTitle().contains(":") || movie.getTitle().contains("(")) {
            imageURL = getMovieImage(new Movie(movie.getId(), movie.getTitle().split("[:(]", 2)[0], movie.getYear()));
        }
        return imageURL;
    }

    private String getMovieImageMoreAccurate(Movie movie, TmdbSearch.MultiListResultsPage rs) {
        String imageURL = null;
        if (rs.getResults().get(0).getMediaType() == Multi.MediaType.MOVIE) {
            try {
                imageURL = movieFetcher.searchMovie(movie.getTitle(), movie.getYear()).getResults().get(0).getPosterPath();
            } catch (Exception e){
                imageURL = ((MovieDb) rs.getResults().get(0)).getPosterPath();
            }
        } else if (rs.getResults().get(0).getMediaType() == Multi.MediaType.TV_SERIES) {
            TvSeries tv = (TvSeries) rs.getResults().get(0);
            imageURL = tv.getPosterPath();
        }
        return imageURL;
    }
}
