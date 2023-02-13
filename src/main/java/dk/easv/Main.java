package dk.easv;

import dk.easv.gui.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private static Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/Login.fxml"));
        root = loader.load();
        LoginController controller = loader.getController();

        primaryStage.setTitle("Movie Recommendation System 0.01 Beta");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnShown((event) -> controller.setStage(primaryStage));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/movie_projector.png"))));
        root.getStylesheets().add(Main.class.getResource("/dk/easv/css/LoginStyle.css").toExternalForm());
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static void manageDisplayMode(){
        if (!LoginController.checkIfDarkMode()){
            root.getStylesheets().add(Main.class.getResource("/dk/easv/css/LoginStyle.css").toExternalForm());
        }else
            root.getStylesheets().add(Main.class.getResource("/dk/easv/css/LoginStyleDarkMode.css").toExternalForm());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
