package GameOfLife;
import java.util.*;

public class Game {

//    public static final int ROWS = 40;
//    public static final int COLUMNS = 40;
    public static final int STARTING_FREQ = 3;

    // methods
    public static void initialize(BoardState b, int gridSize) {
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++) {
                int rand = (int) (Math.random() * STARTING_FREQ);
                if (rand == 0) {
                    b.setCell(r, c, true);
                }
                else {
                    b.setCell(r, c, false);
                }
            }
        }

//        System.out.println(b.toString());
//        displayBoard(b);
    }

//    public static void displayBoard(BoardState b) {
//
//        for (int r = 0; r < ROWS; r++) {
//            String result = "";
//            for (int c = 0; c < COLUMNS; c++) {
//                if (!b.getCell(r, c)) {
//                    result += ".";
//                }
//                else {
//                    result += "0";
//                }
//            }
//            System.out.println(result);
//        }
//        System.out.println("\n");
//    }

//    public static void calculateNextGen(BoardState board, BoardState nextBoard, int gridSize) {
//        for (int r = 0; r < gridSize; r++) {
//            for (int c = 0; c < gridSize; c++) {
//                int neighbors = calculateNeighbors(r, c, board, gridSize);
//                if (board.getCell(r, c) && neighbors < 2) {
//                    nextBoard.setCell(r, c, false);
//                }
//                else if (board.getCell(r, c) && neighbors < 4) {
//                    nextBoard.setCell(r, c, true);
//                }
//                else if (board.getCell(r, c) && neighbors > 3) {
//                    nextBoard.setCell(r, c, false);
//                }
//                else if (!board.getCell(r, c) && neighbors == 3) {
//                    nextBoard.setCell(r, c, true);
//                }
//                else {
//                    nextBoard.setCell(r, c, false);
//                }
//            }
//        }
//    }

    public static void calculateNextGen(CompactMatrix m, CompactMatrix nextM) { // compare twoversions of this
        int size = m.getSize();
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int neighbors = m.calculateNeighbors(r, c);
                if (m.getCell(r, c) && neighbors < 2) {
                    nextM.setCell(r, c, false);
                }
                else if (m.getCell(r, c) && neighbors < 4) {
                    nextM.setCell(r, c, true);
                }
                else if (m.getCell(r, c) && neighbors > 3) {
                    nextM.setCell(r, c, false);
                }
                else if (!m.getCell(r, c) && neighbors == 3) {
                    nextM.setCell(r, c, true);
                }
                else {
                    nextM.setCell(r, c, false);
                }
            }
        }

//        for (int i = 0; i < size; i++) {
//
//            int row = m.getRow(i);
//            int column = m.getColumn(i);
//            int neighbors = m.calculateNeighbors(row, column);
//            boolean exists = m.getCell(row, column);
//
//            if (exists && neighbors < 2) {
////                nextM.setCell(row, column, false, i);
//                nextM.remove(i);
//            }
//            else if (exists && neighbors < 4) {
////                nextM.setCell(row, column, true, i);
//                nextM.add(row, column);
//            }
//            else if (exists && neighbors > 3) {
////                nextM.setCell(row, column, false, i);
//                nextM.remove(i);
//            }
//            else if (!exists && neighbors == 3) {
////                nextM.setCell(row, column, true, i);
//                nextM.add(row, column);
//            }
//            else {
////                nextM.setCell(row, column, false, i);
//                nextM.remove(i);
//            }
//
//
//        }

    }

//    public static int calculateNeighbors(int row, int column, BoardState b, int gridSize) {
//        int count = 0;
//        for (int r = row - 1; r <= row + 1; r++) { // checking the row above and below cell
//            for (int c = column - 1; c <= column + 1; c++) { // checking column left and right of cell
//                if (r >= 0 && r < gridSize && // making sure the cell we're counting is on the board
//                    c >= 0 && c < gridSize && // ^
//                    !(r == row && c == column) && // making sure we don't count the cell we're looking at
//                    b.getCell(r, c)) { // making sure neighbor we're looking at is alive
//                    count++;
//                }
//            }
//        }
//        return count;
//    }

//    public static void setNextBoard(BoardState b, BoardState nextBoard, int gridSize) {
//        for (int r = 0; r < gridSize; r++) {
//            for (int c = 0; c < gridSize; c++) {
//                boolean value = nextBoard.getCell(r, c);
//                b.setCell(r, c, value);
//            }
//        }
//    }

    public static void setNextBoard(CompactMatrix m, CompactMatrix nextM) {
        int size = m.getSize();
//        for (int r = 0; r < size; r++) {
//            for (int c = 0; c < size; c++) {
//                boolean value = nextM.getCell(r, c);
//                m.setCell(r, c, value);
//            }
//        }

        for (int i = 0; i < size; i++) {
            int row = m.getRow(i);
            int column = m.getColumn(i);
            boolean value = nextM.getCell(row, column);
            m.setCell(row, column, value, i);
        }
    }

}
// grid interface
//