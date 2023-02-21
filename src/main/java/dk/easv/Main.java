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
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("views/Login.fxml"));
        Parent root = loader.load();
        LoginController controller = loader.getController();

        primaryStage.setTitle("Movie Recommendation System by The Mexicans");
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnShown((event) -> controller.setStage(primaryStage));
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("icons/movie_projector.png"))));
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    public static Stage getStage() {
        return stage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
