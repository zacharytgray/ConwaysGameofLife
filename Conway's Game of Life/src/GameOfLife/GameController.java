package GameOfLife;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private BorderPane pane;

    @FXML
    private Pane rectPane;

    @FXML
    private Button startStop;

    @FXML
    private Button reset;

    @FXML
    private Slider speed;

    @FXML
    private Slider gridSizeControl;

    @FXML
    private ChoiceBox<Colors> cellColors;

    @FXML
    private ChoiceBox<Colors> boardColors;

    @FXML
    private Label fpsLabel;

    @FXML
    private Label sizeLabel;

    private Movement clock;

    private CompactMatrix matrix;

    private CompactMatrix nextMatrix;

    private double cell_width;

    private boolean running = false;

    private int gridSize;

    private Colors deadCellColor;

    private Colors liveCellColor;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clock = new Movement();

        gridSize = (int)gridSizeControl.getValue();

        matrix = new CompactMatrix(gridSize, 4);
        nextMatrix = new CompactMatrix(gridSize, 4);




        cell_width = rectPane.getPrefWidth() / gridSize;

        boardColors.getSelectionModel().select(Colors.WHITE);
        cellColors.getSelectionModel().select(Colors.GREEN);

        for (Colors c: Colors.values()){
            cellColors.getItems().add(c);
            boardColors.getItems().add(c);
        }

        updateColors();
        refreshGrid();

    }

    @FXML
    public void updateBoard() {
            getNextGen();
            refreshGrid();
        }

    public void getNextGen() {

        Game.calculateNextGen(matrix, nextMatrix);
        Game.setNextBoard(matrix, nextMatrix);

        rectPane.getChildren().clear();

    }

    public void refreshGrid() {
        updateColors();
        rectPane.getChildren().clear();

        for (int i = 0; i < matrix.getSize(); i++) {

            int r = matrix.getRow(i);
            int c = matrix.getColumn(i);

            Rectangle rect = new Rectangle(c * cell_width, r * cell_width, cell_width, cell_width);
            rect.setFill(liveCellColor.getColor());

            rect.setStroke(Color.BLACK);

            rectPane.getChildren().add(rect);

            int finalI = i;
            rect.setOnMouseClicked(event -> {
                matrix.toggleCell(r, c, finalI);
                refreshGrid();
            });
        }
    }

    public void pause() {
        clock.stop();
    }

    public void start() {
        clock.start();
    }

    @FXML
    public void toggleRunning() {
        if (running) {
            pause();
        }
        else {
            start();
        }
        running = !running;
    }

    @FXML
    public void reset() {
        updateColors();
        pause();
        running = false;

        gridSize = (int)gridSizeControl.getValue();

        rectPane.getChildren().clear();

        matrix = new CompactMatrix(gridSize, 4);
        nextMatrix = new CompactMatrix(gridSize, 4);

        cell_width = rectPane.getPrefWidth() / gridSize;

        refreshGrid();

    }

    @FXML
    public void updateColors() {
        deadCellColor = boardColors.getValue();
        liveCellColor = cellColors.getValue();
    }

    private class Movement extends AnimationTimer {

        private long fps = (long)speed.getValue();
        private long interval = 1000000000L / fps;

        private long last = 0;

        @Override
        public void handle(long now) {

            int intSpeed = (int)speed.getValue();
            fpsLabel.setText(Integer.toString(intSpeed) + ",");

            String strSize = Integer.toString(gridSize);
            sizeLabel.setText(strSize + "x" + strSize);

            refreshGrid();

            fps = (long)speed.getValue();
            interval = 1000000000L / fps;
            if (now - last > interval) {
                updateBoard();
                last = now;
            }
        }
    }

}

