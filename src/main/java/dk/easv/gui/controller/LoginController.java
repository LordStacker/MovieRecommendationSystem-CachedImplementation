package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.User;
import dk.easv.gui.model.AppModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.collections.ObservableList;
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

import static java.awt.SystemColor.WINDOW_BORDER;

public class LoginController implements Initializable {

    @FXML
    private MFXTextField usernameTextField;
    @FXML
    private MFXPasswordField passwordTextField;
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
        ObservableList<User> users = model.getObsUsers();

        if (usernameTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            System.out.println("Please fill in all fields");
        } else {
            if(users.stream().anyMatch(user -> user.getName().equals(usernameTextField.getText()))){
                System.out.println("Login successful");
                Parent newScene = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/MainWindow.fxml")));
                stage.setScene(new Scene(newScene));
                stage.centerOnScreen();
            } else {
                System.out.println("Login failed");
            }
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setMinWidth(loginGrid.getWidth());
        stage.setMinHeight(loginGrid.getHeight() + 30); // +30 because of the title bar and window border

    }
}
