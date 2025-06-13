package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.entity.Result;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static com.javarush.island.ostapenko.constans.GUIViewConstans.*;

public class GUIView implements View{
    Stage stage;
    public GUIView(Stage stage) {
        this.stage = stage;
        initUI();
        setHandlers();
        stage.show();
    }

    private void setHandlers() {
    }

    private void initUI() {
        stage.setTitle(WINDOW_NAME);
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1800, 1000);
        stage.setScene(scene);
    }

    @Override
    public String[] getParametrs() {
        //TODO получение параметров из интерефейса
        return new String[0];
    }

    @Override
    public void printResult(Result result) {
    //TODO возврашение результата работы программы в интерефейс
    }
}
