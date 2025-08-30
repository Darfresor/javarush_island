package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.core.dto.CommandRequest;
import com.javarush.island.ostapenko.core.dto.CommandResponse;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import static com.javarush.island.ostapenko.constants.GUIViewConstans.*;

/**
 * Class GUIView use JavaFx library.
 * Contains interface rendering and event handling.
 */
public class JavaFXViewFacade implements IViewFacade {
    private Stage stage;
    private Button lastClickedButton;
    private Button startSimulation;
    private Button stopSimulation;

    public JavaFXViewFacade(Stage stage) {
        this.stage = stage;
        initUI();
        stage.show();
    }


    private void initUI() {

        TabPane tabPane = new TabPane();
        Tab gridTab = new Tab("Отображение острова", createGridPane());
        gridTab.setClosable(false);

        Tab settingPane = new Tab("Конфигурация острова", createSetting());
        settingPane.setClosable(false);

        Tab statisticPane = new Tab("Текущая статистика", staticticInfo());
        statisticPane.setClosable(false);

        tabPane.getTabs().addAll(gridTab, settingPane, statisticPane);


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
        startSimulation = new Button("Запустить симуляцию");
        startSimulation.setUserData(CommandType.START_SUMULATION);
        stopSimulation = new Button("Оставить симуляцию");
        stopSimulation.setUserData(CommandType.STOP_SIMULATION);
        VBox pane = new VBox(10, textFiled, startSimulation, stopSimulation);

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
    public CommandRequest getParametrs() {
        CommandType commandType = (CommandType) lastClickedButton.getUserData();
        return new CommandRequest(commandType,"настройки");
    }

    @Override
    public void printResult(CommandResponse commandResponse) {
        //TODO возврашение результата работы программы в интерефейс
    }

    @Override
    public void setupEventHandlers(Runnable runnable) {
        stopSimulation.setOnAction(event->{
            lastClickedButton = (Button) event.getSource();
            runnable.run();
        });
        startSimulation.setOnAction(event->{
            lastClickedButton = (Button) event.getSource();
            runnable.run();
        });
    }
}
