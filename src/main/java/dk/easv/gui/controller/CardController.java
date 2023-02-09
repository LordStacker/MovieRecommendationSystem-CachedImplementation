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
        gridPane.setStyle("-fx-background-image : url(" + card.getMoviePhoto() + ");" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center center;" +
                "-fx-background-repeat: none;");
        titleMovie.setText(card.getTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

