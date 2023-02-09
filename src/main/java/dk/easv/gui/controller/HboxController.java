package dk.easv.gui.controller;

import dk.easv.Main;
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
                VBox vBox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Card.fxml")));
                children.addAll(vBox);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
