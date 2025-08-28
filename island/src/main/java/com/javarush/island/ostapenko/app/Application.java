package com.javarush.island.ostapenko.app;

import com.javarush.island.ostapenko.controller.ControllerFacade;
import com.javarush.island.ostapenko.controller.IControllerFacade;
import com.javarush.island.ostapenko.view.JavaFXViewFacade;
import com.javarush.island.ostapenko.view.IViewFacade;
import javafx.stage.Stage;

public class Application  extends javafx.application.Application {

        @Override
        public void start(Stage stage) throws Exception {
            IViewFacade view = new JavaFXViewFacade(stage);
            IControllerFacade controllerFacade = new ControllerFacade(view);
            controllerFacade.initializeCommandHandler();
        }
}
