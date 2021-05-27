/*
 * Copyright (c) 2021. Galassi Meshua, Gibertoni Giada
 */


package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.nio.file.Paths;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Paths.get("src/main/resources/scene.fxml").toUri().toURL());

        Scene scene = new Scene(root);
        scene.getStylesheets().add((Paths.get("src/main/resources/style.css").toUri().toURL()).toExternalForm());

        stage.setTitle("JavaFX and Gradle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}