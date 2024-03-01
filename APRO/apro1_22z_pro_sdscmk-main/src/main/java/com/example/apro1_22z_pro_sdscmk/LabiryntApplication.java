
package com.example.apro1_22z_pro_sdscmk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LabiryntApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Labirynt");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("tytulowa.fxml"));
        Parent root = loader.load();
        Image icon = new Image("logo.png");
        stage.getIcons().add(icon);
        Scene scene = new Scene (root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
}

    public static void main(String[] args) {
        launch();
    }
}

