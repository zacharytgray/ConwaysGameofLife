package GameOfLife;

import java.util.HashSet;
import java.util.List;

public class CompactMatrix{

    HashSet<Tuple> map;

    int gridSize;
    int cell_frequency;
    Template t;

    public CompactMatrix(int gridSize, int cell_frequency, Template template) {
        this.gridSize = gridSize;
        this.cell_frequency = cell_frequency;

        map = new HashSet<>();
        t = template;
        t.setBoard(gridSize, cell_frequency, this);

    }

    public boolean getCell(int row, int column) {

        return map.contains(new Tuple(row, column));

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
        map.add(new Tuple(row, column));
    }


    public void remove(int row, int column) {

        map.remove(new Tuple(row, column));

    }

    public CompactMatrix calculateNextGen() {
        int size = gridSize;
        CompactMatrix nextM = new CompactMatrix(gridSize, cell_frequency, Template.EMPTY);

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {

                boolean value = getCell(r, c);
                int neighbors = calculateNeighbors(r, c);

                if (value && (neighbors == 2 || neighbors == 3)) {
                    nextM.add(r, c);
                }
                else if (!value && neighbors == 3) {
                    nextM.add(r, c);
                }
            }
        }

        return nextM;

    }

    public List<Tuple> toList() {
        return map.stream().toList();
    }

}
