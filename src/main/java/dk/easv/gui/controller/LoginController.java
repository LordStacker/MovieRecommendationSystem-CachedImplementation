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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private MFXTextField usernameTextField;
    @FXML
    private MFXPasswordField passwordTextField;
    @FXML
    private GridPane loginGrid;
    @FXML
    private ImageView userIcon, usernameIcon, passwordIcon;
    private AppModel model = AppModel.getInstance();
    private long timerStartMillis = 0;
    private String timerMsg = "";
    private Stage stage;
    private static Boolean isDarkMode = false;
    private Image userImageDark = new Image(getClass().getResourceAsStream("/dk/easv/icons/userDark.png"));
    private Image userNameImageDark = new Image(getClass().getResourceAsStream("/dk/easv/icons/userLogDark.png"));
    private Image passwordImageDark = new Image(getClass().getResourceAsStream("/dk/easv/icons/lockIconLogDark.png"));
    private Image userImage = new Image(getClass().getResourceAsStream("/dk/easv/icons/user.png"));
    private Image userNameImage = new Image(getClass().getResourceAsStream("/dk/easv/icons/userLog.png"));
    private Image passwordImage = new Image(getClass().getResourceAsStream("/dk/easv/icons/lockIconLog.png"));

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
            for (User user: users) {
                if(user.getName().equals(usernameTextField.getText())){
                    System.out.println("Login successful");
                    model.loadData(user);
                    Parent newScene = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/MainWindow.fxml")));
                    stage.setScene(new Scene(newScene));
                    stage.centerOnScreen();
                    return;
                }
            }
            System.out.println("Login failed");
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setMinWidth(loginGrid.getWidth());
        stage.setMinHeight(loginGrid.getHeight() + 30); // +30 because of the title bar and window border
    }

    public void switchDisplayMode(){
        if(isDarkMode){
            isDarkMode = false;
            Main.manageDisplayMode();
            userIcon.setImage(userImage);
            usernameIcon.setImage(userNameImage);
            passwordIcon.setImage(passwordImage);

        }else {
            isDarkMode = true;
            Main.manageDisplayMode();
            userIcon.setImage(userImageDark);
            usernameIcon.setImage(userNameImageDark);
            passwordIcon.setImage(passwordImageDark);
        }
    }

    public static Boolean checkIfDarkMode(){
        return isDarkMode;
    }
}
