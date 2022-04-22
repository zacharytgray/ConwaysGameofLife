package GameOfLife;

public class BoardState {

    private Boolean [][] b;

    // methods
    public BoardState(int rows, int columns) {
        b = new Boolean[rows][columns];
    }

    public boolean getCell(int row, int column) {
        return b[row][column];
    }

    public void setCell(int row, int column, boolean value) {
        b[row][column] = value;
    }

    public void toggleCell(int row, int column) {
        b[row][column] = !b[row][column];
    }

    public int getRows() {
        return b.length;
    }

    public int getColumns() {
        return b[0].length;
    }

    public Boolean[][] getB() {
        return b;
    }

    public String toString() {
        String result = "";
        for (int r = 0; r < getRows(); r++) {
            for (int c = 0; c < getColumns(); c++) {
                if (b[r][c].equals(true)) {
                    result += "T";
                }
                else {
                    result += "F";
                }
            }
            result += "\n";
        }
        return result;
    }
}
