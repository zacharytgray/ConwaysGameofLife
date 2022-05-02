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
    private Button plusOneGen;

    @FXML
    private Slider speed;

    @FXML
    private Slider gridSizeControl;

    @FXML
    private ChoiceBox<Colors> cellColors;

    @FXML
    private ChoiceBox<Template> templates;

    @FXML
    private Label fpsLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label generationsLabel;

    private int generations;

    private Movement clock;

    private CompactMatrix matrix;

    private double cell_width;

    private boolean running = false;

    private int gridSize;

    private Colors liveCellColor;

    private Template activeTemplate;

    private final int cell_frequency = 4; // 1 in x cells are set to alive during initialization of new random board


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clock = new Movement();

        gridSize = (int)gridSizeControl.getValue();

        cellColors.getSelectionModel().select(Colors.ORANGE);
        templates.getSelectionModel().select(Template.RANDOM);
        activeTemplate = templates.getValue();

        matrix = new CompactMatrix(gridSize, cell_frequency, activeTemplate);

        cell_width = rectPane.getPrefWidth() / gridSize;

        for (Colors c: Colors.values()){
            cellColors.getItems().add(c);
        }

        for (Template t: Template.values()) {
            templates.getItems().add(t);
        }

        updateColors();
        refreshGrid();

        String strSize = Integer.toString(gridSize);
        sizeLabel.setText(strSize + "x" + strSize);

        int intSpeed = (int)speed.getValue();
        fpsLabel.setText(Integer.toString(intSpeed));

        generations = 0;
        generationsLabel.setText(Integer.toString(generations));

    }

    @FXML
    public void updateBoard() {
            getNextGen();
            refreshGrid();

        }

    public void getNextGen() {
        generations++;
        generationsLabel.setText(Integer.toString(generations));
        matrix = matrix.calculateNextGen();
        rectPane.getChildren().clear();

    }

    public void refreshGrid() {
        updateColors();
        rectPane.getChildren().clear();

        for (Tuple t : matrix.toList()) {

            int r = t.getX();
            int c = t.getY();

            Rectangle rect = new Rectangle(c * cell_width, r * cell_width, cell_width, cell_width);
            rect.setFill(liveCellColor.getColor());
            rectPane.getChildren().add(rect);

            double ratio = (double) 4 / 27;
            rect.setArcWidth(cell_width * ratio);
            rect.setArcHeight(cell_width * ratio);

            rectPane.setOnMouseClicked(event -> {
                double x = event.getSceneX();
                double y = event.getSceneY();

                int new_x = snapCoordinate(x - 300);
                int new_y = snapCoordinate(y);

                if (matrix.getCell(new_y, new_x)) {
                    matrix.remove(new_y, new_x);
                }
                else {
                    matrix.add(new_y, new_x);
                }

                refreshGrid();
            });

        }
    }

    public int snapCoordinate(double coordinate) {
        for (double i = 0.0; i <= gridSize * cell_width + cell_width; i+= cell_width) {
            if (i > coordinate) {
                double c = i - cell_width;
                return (int)Math.round(c / cell_width);
            }
        }
        return 0;
    }

    public void pause() {
        clock.stop();
    }

    public void start() {
        clock.start();
    }

    @FXML
    public void oneGen() {
        if (!running) {
            updateBoard();
        }
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
        generations = 0;
        generationsLabel.setText(Integer.toString(generations));
        updateColors();
        pause();
        running = false;

        gridSize = (int)gridSizeControl.getValue();

        String strSize = Integer.toString(gridSize);
        sizeLabel.setText(strSize + "x" + strSize);

        int intSpeed = (int)speed.getValue();
        fpsLabel.setText(Integer.toString(intSpeed));

        rectPane.getChildren().clear();

        activeTemplate = templates.getValue();

        matrix = new CompactMatrix(gridSize, cell_frequency, activeTemplate);

        cell_width = rectPane.getPrefWidth() / gridSize;

        refreshGrid();

    }

    @FXML
    public void updateColors() {
        liveCellColor = cellColors.getValue();
    }

    private class Movement extends AnimationTimer {

        private long fps = (long)speed.getValue();
        private long interval = 1000000000L / fps;

        private long last = 0;

        @Override
        public void handle(long now) {

            int intSpeed = (int)speed.getValue();
            fpsLabel.setText(Integer.toString(intSpeed));

            String strSize = Integer.toString(gridSize);
            sizeLabel.setText(strSize + "x" + strSize);

            fps = (long)speed.getValue();
            interval = 1000000000L / fps;
            if (now - last > interval) {
                updateBoard();
                last = now;
            }
        }
    }

}

