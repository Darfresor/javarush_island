package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.dto.ModelResponse;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.javarush.island.ostapenko.constants.GUIViewConstans.*;
/**
 * Class GUIView use JavaFx library.
 * Contains interface rendering and event handling.
 */
public class GUIView implements ViewFacade {
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

        TabPane tabPane = new TabPane();
        Tab gridTab = new Tab("Отображение острова", createGridPane());
        gridTab.setClosable(false);

        Tab settingPane = new Tab("Конфигурация острова",createSetting());
        settingPane.setClosable(false);

        Tab statisticPane = new Tab("Текущая статистика",staticticInfo());
        statisticPane.setClosable(false);

        tabPane.getTabs().addAll(gridTab,settingPane,statisticPane);


        Scene scene = new Scene(tabPane, 1600, 800);
        stage.setTitle(WINDOW_NAME);
        stage.setScene(scene);
        stage.show();



    }

    private Pane staticticInfo() {
        TextArea textFiled = new TextArea("Статистика на текущем ходу");
        VBox pane = new VBox(10, textFiled);
        return pane;
    }

    private Pane createSetting() {

        TextArea textFiled = new TextArea("Какие-то настройки");
        VBox pane = new VBox(10,textFiled);
        return pane;
    }

    private static GridPane createGridPane() {
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
        return grid;
    }



    @Override
    public String[] getParametrs() {
        //TODO получение параметров из интерефейса
        return new String[0];
    }

    @Override
    public void printResult(ModelResponse modelResponse) {
    //TODO возврашение результата работы программы в интерефейс
    }
}
