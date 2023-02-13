package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.be.Movie;
import dk.easv.gui.model.AppModel;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    public GridPane grdPane;
    @FXML
    private VBox mainVBox;
    @FXML
    private MFXScrollPane mainScrollPane;

    private final AppModel model = AppModel.getInstance();
    private long timerStartMillis = 0;
    private String timerMsg = "";

    private void startTimer(String message){
        timerStartMillis = System.currentTimeMillis();
        timerMsg = message;
    }

    private void stopTimer(){
        System.out.println(timerMsg + " took : " + (System.currentTimeMillis() - timerStartMillis) + "ms");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LinkedHashMap<String, ObservableList<Movie>> map = new LinkedHashMap<>();
        map.put("Top movies you have seen", model.getObsTopMovieSeen());
        map.put("Top movies you have not seen", model.getObsTopMovieNotSeen());
        Set<String> keys = map.keySet();
        try {
            for (String key : keys){
                Label label = new Label(key);
                mainVBox.getChildren().add(label);

                FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(Main.class.getResource("views/Hbox.fxml")));
                HBox hBox = loader.load();
                HboxController hboxController = loader.getController();
                hboxController.setMovieList(map.get(key));
                MFXScrollPane scrollPane = new MFXScrollPane(hBox);
                scrollPane.setFitToHeight(true);
                scrollPane.getStyleClass().add("sideScroll");
                mainVBox.getChildren().add(scrollPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
