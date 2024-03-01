package com.example.apro1_22z_pro_sdscmk;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LabiryntController class controls home page part of user interface.
 */
public class HomePageController implements Initializable {
    @FXML
    private ChoiceBox<String> algorithm;

    @FXML
    private ChoiceBox<String> difficulty;

    @FXML
    private Button go;

    private Stage stage;
    private Scene scene;
    private Parent root;
    Data data = Data.getInstance();

    /**
     * This method switches scene to maze scene.
     *
     * @param e action on using button
     * @throws IOException
     */
    public void go(ActionEvent e) throws IOException {
        String diff = difficulty.getValue();
        String algo = algorithm.getValue();
        data.setDifficulty(diff);
        data.setAlgorithm(algo);
        if (diff == null || algo == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.show();
            alert.setHeaderText("Mode not chosen!");
            alert.setTitle("Warning!");
            alert.setContentText("Choose mode!");
            return;
        }
        root = FXMLLoader.load((getClass().getResource("labirynt.fxml")));

        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();

        scene = new Scene(root);
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("labirynt.fxml"));
        root = loader.load();
        LabiryntController labiryntController = loader.getController();
        labiryntController.setSize(diff);
        labiryntController.setAlgorithm(algo);
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        if (diff == "easy") {
            scene = new Scene(root, 336, 400);
        }
        if (diff == "medium") {
            scene = new Scene(root, 496, 560);
        }
        if (diff == "hard") {
            scene = new Scene(root, 656, 720);
        }
        stage.setResizable(false);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    labiryntController.moveUp();
                    break;
                case S:
                    labiryntController.moveDown();
                    break;
                case A:
                    labiryntController.moveLeft();
                    break;
                case D:
                    labiryntController.moveRight();
                    break;
            }
        });
        */
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algorithm.getItems().addAll("Eller's", "BackTracking");
        difficulty.getItems().addAll("easy", "medium", "hard");
    }
}
