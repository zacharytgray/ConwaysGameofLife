package GameOfLife;

public class SparseMatrix {

    private int gridSize;
    boolean[][] sparseMatrix;
    int size = 0;

    public SparseMatrix(int gridSize) {
        this.gridSize = gridSize;
        sparseMatrix = new boolean[gridSize][gridSize];
    }

    public void setCell(int row, int column, boolean value) {
        sparseMatrix[row][column] = value;
    }

    public boolean getCell(int row, int column) {
        return sparseMatrix[row][column];
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getSize() {
        return size;
    }

    public void setRandom(int cell_frequency) {
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                int rand = (int) (Math.random() * cell_frequency);
                if (rand == 0) {
                    setCell(r, c, true);
                    size++;
                } else {
                    setCell(r, c, false);
                }
            }
        }
    }

    public String toString() {
        for (int i = 0; i < gridSize; i++) {
            String result = "";
            for (int j = 0; j < gridSize; j++) {
                result += sparseMatrix[i][j] + ", ";
            }
            System.out.println(result);
        }
        return "";
    }

}