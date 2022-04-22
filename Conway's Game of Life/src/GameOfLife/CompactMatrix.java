package GameOfLife;

import java.util.ArrayList;

public class CompactMatrix {

//    int[][] compactMatrix; // 0 is row, 1 is column
    ArrayList<Integer>[] compactMatrix;
    int gridSize;

    public CompactMatrix(SparseMatrix s) {
        compactMatrix = new ArrayList[2];
        compactMatrix[0] = new ArrayList<Integer>();
        compactMatrix[1] = new ArrayList<Integer>();

//        compactMatrix = new int[2][size]; // you want a coordinate for each live cell, so we allocate those spots
        gridSize = s.getGridSize();


        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {

                if (s.getCell(r, c)) {

                    compactMatrix[0].add(r);
                    compactMatrix[1].add(c);

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
//                    System.out.println(r + " " + c  + "\n");
                }
            }
        }
        return count;
    }


    // WRITE ADD
    public void add(int row, int column) {
        compactMatrix[0].add(row);
        compactMatrix[1].add(column);

    }

    public void remove(int index) {
//        for (int i = 0; i < compactMatrix[0].size(); i++) {
//            if (compactMatrix[0].get(i) == row && compactMatrix[1].get(i) == column) {
                compactMatrix[0].remove(index);
                compactMatrix[1].remove(index);
//                break;
//            }
//        }
    }

    public void toggleCell(int row, int column, int index) {
        if (getCell(row, column)) {
            remove(index);
        }
        else {
            add(row, column);
        }
    }

    public void setCell(int row, int column, boolean alive, int index) {
        if (alive && !getCell(row, column)) {
            add(row, column);
        }
        else if (!alive && getCell(row, column)) {
            remove(index);
        }
        else {
            System.out.println("Invalid conditions in setCell()");
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



    public String toString() {
        for (int i = 0; i < compactMatrix.length; i++) {
            String result = "";
            for (int j = 0; j < compactMatrix[0].size(); j++) {
                result += compactMatrix[i].get(j);
            }
            System.out.println(result);
        }
        return "";
    }
}
