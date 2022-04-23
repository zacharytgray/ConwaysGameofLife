package GameOfLife;

import java.util.ArrayList;

public class CompactMatrix {

    ArrayList<Integer>[] compactMatrix;
    int gridSize;
    int cell_frequency;
    InsertionSort ob;

    public CompactMatrix(int gridSize, int cell_frequency) {
        this.gridSize = gridSize;
        this.cell_frequency = cell_frequency;
        compactMatrix = new ArrayList[2];
        compactMatrix[0] = new ArrayList<Integer>();
        compactMatrix[1] = new ArrayList<Integer>();
        ob = new InsertionSort();
        setRandom();

    }

    public void setRandom() {
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                int rand = (int) (Math.random() * cell_frequency);
                if (rand == 0) {
                    add(r, c);
                }
            }
        }
    }

    public boolean getCell(int row, int column) {
        for (int i = 0; i < compactMatrix[0].size(); i++) {
            if (compactMatrix[0].get(i) == row && compactMatrix[1].get(i) == column) {
                return true;
            }
        }
        return false;
    }

    public int calculateNeighbors(int row, int column) {
        int count = 0;
        for (int r = row - 1; r <= row + 1; r++) { // checking the row above and below cell
            for (int c = column - 1; c <= column + 1; c++) { // checking column left and right of cell
                if (r >= 0 && r < gridSize && // making sure the cell we're counting is on the board
                        c >= 0 && c < gridSize && // ^
                        !(r == row && c == column) && // making sure we don't count the cell we're looking at
                        getCell(r, c)) { // making sure neighbor we're looking at exists and is alive
                    count++;
                }
            }
        }
        return count;
    }

    public void add(int row, int column) {
        if (!getCell(row, column)) {
            compactMatrix[0].add(row);
            compactMatrix[1].add(column);

            ob.sort(compactMatrix[0],compactMatrix[1]);
        }

    }

    public void remove(int index) {
            compactMatrix[0].remove(index);
            compactMatrix[1].remove(index);
    }


    public void removeRC(int row, int column) {
        for (int i = 0; i < compactMatrix[0].size(); i++) {
            if (compactMatrix[0].get(i) == row && compactMatrix[1].get(i) == column) {
                compactMatrix[0].remove(i);
                compactMatrix[1].remove(i);
                break;
            }
        }
    }

    public void toggleCell(int row, int column, int index) {
        if (getCell(row, column)) {
            remove(index);
        }
        else {
            add(row, column);
        }
    }

    public int getSize() {
        return compactMatrix[0].size();
    }

    public int getRow(int index) {
        return compactMatrix[0].get(index);
    }

    public int getColumn(int index) {
        return compactMatrix[1].get(index);
    }

    public void clearAll() {
        compactMatrix[0].clear();
        compactMatrix[1].clear();
    }



    public String toString() {
        int size = getSize();
        int[][] coords = new int[size][2];
        for (int i = 0; i < size; i++) {
            coords[i][0] = getRow(i);
            coords[i][1] = getColumn(i);
        }

        String fin = "";
        for(int[] c : coords) {
            fin += c[0] + "," + c[1] + " ";
        }
        return fin;
    }
}
