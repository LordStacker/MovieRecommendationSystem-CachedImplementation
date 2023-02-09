package dk.easv;

import dk.easv.gui.controller.LoginController;
import dk.easv.gui.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/MainWindow.fxml"));
        Parent root = loader.load();
        MainWindowController controller = loader.getController();

        primaryStage.setTitle("Movie Recommendation System 0.01 Beta");
        primaryStage.setScene(new Scene(root));
//        primaryStage.setOnShown((event) -> controller.setStage(primaryStage));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/movie_projector.png"))));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
