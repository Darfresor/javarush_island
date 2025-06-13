package com.javarush.island.ostapenko.app;

import com.javarush.island.ostapenko.view.GUIView;
import com.javarush.island.ostapenko.view.View;
import javafx.stage.Stage;

public class Application  extends javafx.application.Application {

        @Override
        public void start(Stage stage) throws Exception {
            View view = new GUIView(stage);
        }
}
