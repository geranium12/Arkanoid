package model;

public class Paddle extends BaseObject {
    private final int n = 9;
    private int sideLength;
    public Paddle(int x, int y, int width, int height) {
        super(x, y, width, height);
        sideLength = width / n;
    }

    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    public int getN() {
        return n;
    }
}
