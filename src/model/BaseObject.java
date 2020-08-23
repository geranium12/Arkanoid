package model;

public abstract class BaseObject {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    protected BaseObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isIntersecting(BaseObject o) {
        //brick, paddle = this
        //ball = o
        return this.getRight() >= o.getLeft() && this.getLeft() <= o.getRight() &&
                this.getBottom() >= o.getTop() && this.getTop() <= o.getBottom();
    }

    int getLeft() {
        return x;
    }

    int getRight() {
        return x + width;
    }

    int getTop() {
        return y;
    }

    int getBottom() {
        return y + height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

