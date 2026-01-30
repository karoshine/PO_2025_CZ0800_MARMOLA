package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {

    private WorldMap worldMap;

    private static final int CELL_SIZE = 45;
    private static final double GRID_LINE_WIDTH = 2.0;

    @FXML
    private Label infoLabel;

    @FXML
    private Label moveInfoLabel;

    @FXML
    private Canvas mapGrid;

    @FXML
    private TextField moveListField;

    public void setWorldMap(WorldMap map) {
        this.worldMap = map;
        map.subscribe(this);
    }

    @Override
    public void mapChanged(WorldMap map, String message) {
        Platform.runLater(() -> {
            redraw();
            updateMoveInfo(message);
        });
    }

    private void updateMoveInfo(String message) {
        if (moveInfoLabel != null) {
            moveInfoLabel.setText(message);
        }
    }

    public void drawMap() {
        redraw();
    }

    private void redraw() {
        Vector2d bottomLeft = new Vector2d(1, 1);
        Vector2d topRight = new Vector2d(10, 10);

        int width = topRight.getX() - bottomLeft.getX() + 1;
        int height = topRight.getY() - bottomLeft.getY() + 1;

        resizeCanvas(width + 1, height + 1);

        GraphicsContext gc = mapGrid.getGraphicsContext2D();
        clear(gc);
        drawGrid(gc, width + 1, height + 1);
        setupTextStyle(gc);

        gc.fillText("y\\x", CELL_SIZE / 2.0, CELL_SIZE / 2.0);

        for (int i = 0; i < width; i++) {
            gc.fillText(
                    String.valueOf(bottomLeft.getX() + i),
                    (i + 1) * CELL_SIZE + CELL_SIZE / 2.0,
                    CELL_SIZE / 2.0
            );
        }

        for (int i = 0; i < height; i++) {
            gc.fillText(
                    String.valueOf(bottomLeft.getY() + i),
                    CELL_SIZE / 2.0,
                    (i + 1) * CELL_SIZE + CELL_SIZE / 2.0
            );
        }

        renderObjects(gc, bottomLeft, topRight);
    }

    private void resizeCanvas(int widthCells, int heightCells) {
        mapGrid.setWidth(widthCells * CELL_SIZE);
        mapGrid.setHeight(heightCells * CELL_SIZE);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.BLUEVIOLET);
        gc.fillRect(0, 0, mapGrid.getWidth(), mapGrid.getHeight());
    }

    private void drawGrid(GraphicsContext gc, int widthCells, int heightCells) {
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(GRID_LINE_WIDTH);

        double offset = GRID_LINE_WIDTH / 2;

        for (int x = 0; x <= widthCells; x++) {
            double px = x * CELL_SIZE + offset;
            gc.strokeLine(px, 0, px, mapGrid.getHeight());
        }

        for (int y = 0; y <= heightCells; y++) {
            double py = y * CELL_SIZE + offset;
            gc.strokeLine(0, py, mapGrid.getWidth(), py);
        }
    }

    private void setupTextStyle(GraphicsContext gc) {
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font("Arial", 22));
        gc.setFill(Color.BLACK);
    }

    private void renderObjects(GraphicsContext gc, Vector2d lowerLeft, Vector2d upperRight) {
        int cols = upperRight.getX() - lowerLeft.getX() + 1;
        int rows = upperRight.getY() - lowerLeft.getY() + 1;

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                Vector2d position = new Vector2d(
                        lowerLeft.getX() + col,
                        lowerLeft.getY() + row
                );

                if (!worldMap.isOccupied(position)) {
                    continue;
                }

                Object element = worldMap.objectAt(position);
                if (element == null) {
                    continue;
                }

                double x = (col + 1) * CELL_SIZE + CELL_SIZE / 2.0;
                double y = (row + 1) * CELL_SIZE + CELL_SIZE / 2.0;

                gc.fillText(element.toString(), x, y);
            }
        }
    }

    @FXML
    public void onSimulationStartCl(ActionEvent event) {
        String input = moveListField.getText();
        List<MoveDirection> directions = OptionsParser.parserChange(input.split("\\s+"));

        List<Vector2d> startPositions = List.of(
                new Vector2d(3, 4),
                new Vector2d(9, 7)
        );

        Simulation simulation = new Simulation(startPositions, directions, worldMap);
        SimulationEngine engine = new SimulationEngine(List.of(simulation));

        engine.runAsyncInThreadPool();
    }
}
