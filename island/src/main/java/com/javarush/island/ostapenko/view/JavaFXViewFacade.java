package com.javarush.island.ostapenko.view;

import com.javarush.island.ostapenko.constants.CommandType;
import com.javarush.island.ostapenko.core.dto.CommandRequest;
import com.javarush.island.ostapenko.core.dto.CommandResponse;
import com.javarush.island.ostapenko.core.dto.SimulationStatistics;
import com.javarush.island.ostapenko.core.exception.ApplicationException;
import com.javarush.island.ostapenko.core.interfaces.IViewFacade;
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
    private TextArea textFiledStatistics;

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
        textFiledStatistics = new TextArea("Статистика на текущем ходу");
        textFiledStatistics.setWrapText(true);
        VBox pane = new VBox(10, textFiledStatistics);
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
        return new CommandRequest(commandType, "настройки");
    }

    @Override
    public void printResult(CommandResponse commandResponse) {
        switch (commandResponse.getCommandType()) {
            case CommandType.UPDATE_STATISTICS ->
                    updateStatistics(commandResponse.getResult(SimulationStatistics.class));
            case null -> throw new ApplicationException("Команда не может быть пустой");
            default -> throw new ApplicationException("Команда не поддерживается");
        }
    }

    @Override
    public void setupEventHandlers(Runnable runnable) {
        stopSimulation.setOnAction(event -> {
            lastClickedButton = (Button) event.getSource();
            runnable.run();
        });
        startSimulation.setOnAction(event -> {
            lastClickedButton = (Button) event.getSource();
            runnable.run();
        });
    }

    public void updateStatistics(SimulationStatistics simulationStatistics) {
        String totalAnimal = String.format("Общее кол-во животных = %d %n", simulationStatistics.getTotalAnimal());
        String totalPlant = String.format("Общее кол-во растений = %d", simulationStatistics.getTotalPlants());
        textFiledStatistics.setText(totalAnimal + totalPlant);
    }
}
