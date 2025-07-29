package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.entity.Result;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.javarush.island.ostapenko.constants.GUIViewConstans.*;
/**
 * Class GUIView use JavaFx library.
 * Contains interface rendering and event handling.
 */
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

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        for (int i = 0; i < 20; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(column);
        }
        for (int i = 0; i < 20; i++) {
            RowConstraints row = new RowConstraints();
            row.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(row);
        }

        // Заполнение ячеек
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                Label cell = new Label("R" + row + " C" + col);
                cell.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                cell.setStyle("-fx-border-color: black; -fx-alignment: CENTER;");
                grid.add(cell, col, row);
            }
        }

        Scene scene = new Scene(grid, 400, 400);
        stage.setTitle(WINDOW_NAME);
        stage.setScene(scene);
        stage.show();



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
