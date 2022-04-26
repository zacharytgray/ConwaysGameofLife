package GameOfLife;

public class Game {

    public static final int STARTING_FREQ = 3;


//    public static void calculateNextGen(CompactMatrix m, CompactMatrix nextM) {
//        int size = m.getSize();
//
//        for (int r = 0; r < size; r++) {
//            for (int c = 0; c < size; c++) {
//
//                boolean value = m.getCell(r, c);
//                int neighbors = m.calculateNeighbors(r, c);
//
//                if (value && (neighbors == 2 || neighbors == 3)) {
//                    nextM.add(r, c);
//                }
//                else if (!value && neighbors == 3) {
//                    nextM.add(r, c);
//                }
//                else {
//                    nextM.removeRC(r, c);
//                }
//            }
//        }
//
//    }


//    public static void setNextBoard(CompactMatrix m, CompactMatrix nextM) {
////        int size = nextM.getSize();
////
////        m.clearAll();
////
////        for (int i = 0; i < size; i++) {
////            int row = nextM.getRow(i);
////            int column = nextM.getColumn(i);
////            m.add(row, column);
////        }
//
//        m = nextM;
//        nextM = new CompactMatrix()
//
//    }

}