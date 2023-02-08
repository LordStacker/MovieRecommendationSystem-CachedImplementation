package dk.easv.gui.controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import dk.easv.be.Card;

public class CardController {
        @FXML
        private VBox Card;

        @FXML
        private ImageView MovieImage;

        @FXML
        private Label subTitleMovie;

        @FXML
        private Label titleMovie;

        private void setCards(Card cards){
            //Image image = new Image(HERE THE IMAGE FROM API IN THE OBJECT);
            //import image from the API
            //MovieImage.setImage(image);
            titleMovie.setText(cards.getTitle());
            subTitleMovie.setText(cards.getSubTitleMovie());
        }

    }

