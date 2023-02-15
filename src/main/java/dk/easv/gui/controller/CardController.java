package dk.easv.gui.controller;

import dk.easv.be.Card;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    @FXML
    private GridPane gridPane;
    @FXML
    private GridPane mainGrid;

    @FXML
    private Label titleMovie;


    public void setCards(Card card) {
        if (card.getMoviePhoto() == null) {
            gridPane.setStyle(gridPane.getStyle() + "-fx-background-image : url(https://i.imgflip.com/6ahkvd.jpg);" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: none;");
            titleMovie.setText(card.getTitle()+ " (" + card.getYear() + ")");
        } else {
            gridPane.setStyle(gridPane.getStyle() + "-fx-background-image : url(https://image.tmdb.org/t/p/w200" + card.getMoviePhoto() + ");" +
                    "-fx-background-size: cover;" +
                    "-fx-background-position: center center;" +
                    "-fx-background-repeat: none;" +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);" +
                    "-fx-background-radius: 10 0 0 10;");
            titleMovie.setText(card.getTitle() + " (" + card.getYear() + ")");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupListeners();
    }

    private void setupListeners() {
        mainGrid.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                mainGrid.setStyle(mainGrid.getStyle() + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            } else {
                mainGrid.setStyle(mainGrid.getStyle() + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
            }
        });
    }
}

