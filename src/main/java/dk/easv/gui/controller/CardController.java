package dk.easv.gui.controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import dk.easv.be.Card;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
        @FXML
        private VBox vBox;

        @FXML
        private ImageView movieImage;


        @FXML
        private Label titleMovie;


        public void setCards(Card card){
            Image image = new Image(card.getMoviePhoto());
            movieImage.setPreserveRatio(true);
            movieImage.fitWidthProperty().bind(vBox.widthProperty());
            movieImage.fitHeightProperty().bind(vBox.heightProperty());
            //import image from the API
            movieImage.setImage(image);
            titleMovie.setText(card.getTitle());
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

