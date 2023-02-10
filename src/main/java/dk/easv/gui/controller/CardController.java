package dk.easv.gui.controller;

import dk.easv.be.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    public GridPane gridPane;

    @FXML
    private Label titleMovie;


    public void setCards(Card card) {
        if (card.getMoviePhoto() == null) {
            gridPane.setStyle("-fx-background-image : url(https://i.imgflip.com/6ahkvd.jpg);" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: none;");
            titleMovie.setText(card.getTitle()+ " (" + card.getYear() + ")");
        } else {
            gridPane.setStyle("-fx-background-image : url(https://image.tmdb.org/t/p/original" + card.getMoviePhoto() + ");" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: none;");
            titleMovie.setText(card.getTitle() + " (" + card.getYear() + ")");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

