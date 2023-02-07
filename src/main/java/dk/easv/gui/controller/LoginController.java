package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.gui.model.AppModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private GridPane loginGrid;
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
        model.loadUsers();
    }

    public void loginButton(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/MainWindow.fxml")));
        stage.setScene(new Scene(newScene));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setMinWidth(loginGrid.getWidth());
        stage.setMinHeight(loginGrid.getHeight()+30); // +20 because of the title bar and window border

    }
}
