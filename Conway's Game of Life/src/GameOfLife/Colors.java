package GameOfLife;

import javafx.scene.paint.Color;

public enum Colors {
    RED{
        @Override
        public Color getColor() {
            return Color.RED;
        }

        @Override
        public String toString() {
            return "Red";
        }
    },
    ORANGE{
        @Override
        public Color getColor() {
            return Color.ORANGE;
        }

        @Override
        public String toString() {
            return "Orange";
        }
    },
    YELLOW{
        @Override
        public Color getColor() {
            return Color.YELLOW;
        }

        @Override
        public String toString() {
            return "Yellow";
        }
    },
    GREEN{
        @Override
        public Color getColor() {
            return Color.GREEN;
        }

        @Override
        public String toString() {
            return "Green";
        }
    },
    BLUE{
        @Override
        public Color getColor() {
            return Color.BLUE;
        }

        @Override
        public String toString() {
            return "Blue";
        }
    },
    PURPLE{
        @Override
        public Color getColor() {
            return Color.PURPLE;
        }

        @Override
        public String toString() {
            return "Purple";
        }
    },
    BLACK{
        @Override
        public Color getColor() {
            return Color.BLACK;
        }

        @Override
        public String toString() {
            return "Black";
        }
    },
    GRAY{
        @Override
        public Color getColor() {
            return Color.GRAY;
        }

        @Override
        public String toString() {
            return "Gray";
        }
    },
    WHITE{
        @Override
        public Color getColor() {
            return Color.WHITE;
        }

        @Override
        public String toString() {
            return "White";
        }
    };

    abstract public Color getColor();
    abstract public String toString();
}
