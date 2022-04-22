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

//    private BoardState b;

//    private BoardState nextBoard;

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

//        b = new BoardState(gridSize, gridSize);
//        nextBoard = new BoardState(gridSize, gridSize);

        SparseMatrix s = new SparseMatrix(gridSize);
        s.setRandom(4);
        matrix = new CompactMatrix(s);
        SparseMatrix nextS = new SparseMatrix(gridSize);
        nextMatrix = new CompactMatrix(nextS);

        cell_width = rectPane.getPrefWidth() / gridSize;

        boardColors.getSelectionModel().select(Colors.WHITE);
        cellColors.getSelectionModel().select(Colors.GREEN);

        for (Colors c: Colors.values()){
            cellColors.getItems().add(c);
            boardColors.getItems().add(c);
        }


//        Game.initialize(b, gridSize);
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
        System.out.println(matrix.getSize());

        System.out.println(matrix.getSize());


    }

    public void refreshGrid() {
        updateColors();
        rectPane.getChildren().clear();
//        for (int r = 0; r < gridSize; r++) {
//            for (int c = 0; c < gridSize; c++) {
//                boolean value = matrix.getCell(r, c);
//                Rectangle rect = new Rectangle(cell_width, cell_width);
//                if (value) {
//                    rect.setFill(liveCellColor.getColor());
//                    GridPane.setConstraints(rect, c, r);
//                    grid.getChildren().add(rect);
//                }
//                else {
//                    rect.setFill(deadCellColor.getColor());
//                    GridPane.setConstraints(rect, c, r);
//                    grid.getChildren().add(rect);
//                }
//                int finalR = r;
//                int finalC = c;
//                rect.setOnMouseClicked(event -> {
//                    matrix.toggleCell(finalR, finalC);
//                    refreshGrid();
//                });
//            }
//        }

        for (int i = 0; i < matrix.getSize(); i++) {

            int r = matrix.getRow(i);
            int c = matrix.getColumn(i);

            Rectangle rect = new Rectangle(c * cell_width, r * cell_width, cell_width, cell_width);
            rect.setFill(liveCellColor.getColor());

            rect.setStroke(Color.BLACK);

//            rectPane.setConstraints(rect, c, r);

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
//        b = new BoardState(gridSize, gridSize);
//        nextBoard = new BoardState(gridSize, gridSize);

        SparseMatrix s = new SparseMatrix(gridSize);
        s.setRandom(4);
        matrix = new CompactMatrix(s);
        SparseMatrix nextS = new SparseMatrix(gridSize);
        nextMatrix = new CompactMatrix(nextS);

//        Game.initialize(b, gridSize);
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

