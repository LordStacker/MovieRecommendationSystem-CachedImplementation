package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Card;
import dk.easv.be.Movie;
import dk.easv.be.TopMovie;
import dk.easv.gui.model.AppModel;
import dk.easv.util.MovieFetcher;
import info.movito.themoviedbapi.model.core.MovieResultsPage;
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
    private static final AppModel model = AppModel.getInstance();
    private final MovieFetcher movieFetcher = new MovieFetcher();
    @FXML
    private HBox mainHbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateHbox();
    }

    private void populateHbox(){
        try {
            ObservableList<Node> children =  mainHbox.getChildren();
            ObservableList<TopMovie> movies = model.getObsTopMoviesSimilarUsers();
            System.out.println(movies);
            for (int i = 0; i < 15; i++) {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/Card.fxml")));
                Parent parent = loader.load();

                CardController cardController = loader.getController();
                MovieResultsPage movieResultsPage = movieFetcher.searchMovie(movies.get(i).getTitle(), movies.get(i).getYear());

                String imageURL;

                if (movieResultsPage.getResults().isEmpty()) {
                    imageURL = null;
                } else {
                    imageURL = movieFetcher.getMovie(movieResultsPage.getResults().get(0).getId()).getPosterPath();
                }

                cardController.setCards(new Card(movies.get(i).getTitle(), imageURL, movies.get(i).getYear()));
                children.addAll(parent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
