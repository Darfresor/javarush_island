package com.javarush.island.ostapenko.app;

import com.javarush.island.ostapenko.controller.ControllerFacade;
import com.javarush.island.ostapenko.core.interfaces.IControllerFacade;
import com.javarush.island.ostapenko.core.interfaces.IModelFacade;
import com.javarush.island.ostapenko.model.facade.ModelFacade;
import com.javarush.island.ostapenko.view.JavaFXViewFacade;
import com.javarush.island.ostapenko.core.interfaces.IViewFacade;
import javafx.stage.Stage;

public class Application  extends javafx.application.Application {

        @Override
        public void start(Stage stage) throws Exception {
            IViewFacade view = new JavaFXViewFacade(stage);
            IModelFacade model = new ModelFacade();
            IControllerFacade controllerFacade = new ControllerFacade(view, model);
            controllerFacade.initializeCommandHandler();
        }
}
