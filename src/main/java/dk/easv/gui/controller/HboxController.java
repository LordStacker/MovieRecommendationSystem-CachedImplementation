package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Card;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HboxController implements Initializable {

    @FXML
    private HBox mainHbox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateHbox();
    }

    private void populateHbox(){
        try {
            ObservableList<Node> children =  mainHbox.getChildren();
            for (int i = 0; i < 24; i++) {
                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/Card.fxml")));
                Parent parent= loader.load();

                CardController cardController = loader.getController();
                cardController.setCards(new Card("Testing", "https://image.tmdb.org/t/p/w500/8WUVHemHFH2ZIP6NWkwlHWsyrEL.jpg"));
                children.addAll(parent);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
