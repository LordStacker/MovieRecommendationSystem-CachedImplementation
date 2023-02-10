package dk.easv.gui.controller;

import dk.easv.Main;
import dk.easv.gui.model.AppModel;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

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
        try {
            for (int i = 0; i < 3; i++) {

                Label label = new Label("Label " + i);
                mainVBox.getChildren().add(label);


                HBox hBox = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/Hbox.fxml")));
                MFXScrollPane scrollPane = new MFXScrollPane(hBox);
                scrollPane.setFitToHeight(true);

                mainVBox.getChildren().add(scrollPane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
