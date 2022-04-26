package GameOfLife;

public class Tuple {

    private int x;
    private int y;

    public Tuple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object O)  {
        if (O instanceof Tuple) {
            Tuple other = (Tuple)O;
            if (x == other.getX() && y == other.getY()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return x + y;
    }

}
