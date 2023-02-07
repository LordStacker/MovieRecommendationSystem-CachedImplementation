package dk.easv;

import dk.easv.gui.controller.AppController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/App.fxml"));
        Parent root = loader.load();
        AppController controller = loader.getController();
        controller.setStage(primaryStage);
        primaryStage.setTitle("Movie Recommendation System 0.01 Beta");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
