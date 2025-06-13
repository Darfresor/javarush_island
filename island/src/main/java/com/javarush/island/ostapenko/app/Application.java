package com.javarush.island.ostapenko.app;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Application  extends javafx.application.Application {

        @Override
        public void start(Stage stage) throws Exception {
            stage.setTitle("Остров");
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 1800, 1000);
            stage.setScene(scene);
            stage.show();
        }
}
