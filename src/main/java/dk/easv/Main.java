package dk.easv;

import dk.easv.gui.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();

        primaryStage.setTitle("Movie Recommendation System 0.01 Beta");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnShown((event) -> controller.setStage(primaryStage));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
