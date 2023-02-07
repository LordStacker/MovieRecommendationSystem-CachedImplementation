package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.*;
import dk.easv.gui.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class AppController implements Initializable {

    public Pane loginPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Group loginGroup;
    private AppModel model = new AppModel();
    private long timerStartMillis = 0;
    private String timerMsg = "";
    private Stage stage;


    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void loginButton(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/AppTile.fxml")));
        stage.setScene(new Scene(newScene));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
