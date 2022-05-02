package GameOfLife;

public enum Template {

    RANDOM{
        @Override
        void setBoard(int gridSize, int cell_frequency, CompactMatrix m) {
            for (int r = 0; r < gridSize; r++) {
                for (int c = 0; c < gridSize; c++) {
                    int rand = (int) (Math.random() * cell_frequency);
                    if (rand == 0) {
                        m.add(r, c);
                    }
                }
            }
        }
    },

    EMPTY{
        @Override
        void setBoard(int gridSize, int cell_frequency, CompactMatrix m) {

        }
    },

    GLIDER{
        @Override
        void setBoard(int gridSize, int cell_frequency, CompactMatrix m) {
            m.add(1,2);
            m.add(2,3);
            m.add(3,1);
            m.add(3,2);
            m.add(3,3);
        }
    },

    GLIDER_GUN{
        @Override
        void setBoard(int gridSize, int cell_frequency, CompactMatrix m) {
            m.add(5,1);
            m.add(5,2);
            m.add(6,1);
            m.add(6,2);

            m.add(5,11);
            m.add(6,11);
            m.add(7,11);
            m.add(4,12);
            m.add(8,12);
            m.add(3,13);
            m.add(9,13);
            m.add(3,14);
            m.add(9,14);

            m.add(6,15);

            m.add(4,16);
            m.add(8,16);
            m.add(5,17);
            m.add(6,17);
            m.add(7,17);
            m.add(6,18);

            m.add(3,21);
            m.add(4,21);
            m.add(5,21);
            m.add(3,22);
            m.add(4,22);
            m.add(5,22);
            m.add(2,23);
            m.add(6,23);

            m.add(1,25);
            m.add(2,25);

            m.add(6,25);
            m.add(7,25);

            m.add(3,35);
            m.add(4,35);
            m.add(3,36);
            m.add(4,36);


        }
    };


    abstract void setBoard(int gridSize, int cell_frequency, CompactMatrix m);

}
