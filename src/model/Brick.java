package model;

public class Brick extends BaseObject{
    private int level;
    private int health;

    public Brick(int x, int y, int width, int height, int level) {
        super(x, y, width, height);
        this.level = level;
        this.health = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void decHealth() {
        this.health--;
    }
}
