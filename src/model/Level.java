package model;

import java.util.ArrayList;

import static model.Model.*;

public class Level {
    public static ArrayList<Brick> createLevel(int level) {
        ArrayList<Brick> bricks = new ArrayList<>();
        switch (level) {
            case 1: {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 15 - i * 2; j++) {
                        int x = INDENT + MARGIN + BRICK_WIDTH * j;
                        int y = INDENT + MARGIN + BRICK_HEIGHT * (i * 2 + j);
                        Brick brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, i / 2 + 1);
                        bricks.add(brick);
                    }
                break;
            }

            case 2: {
                for (int i = 0; i < 10; i++)
                    for (int j = 0; j < 15; j++) {
                        int x = INDENT + MARGIN + BRICK_WIDTH * j;
                        int y = INDENT + MARGIN + BRICK_HEIGHT * i;
                        Brick brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, i / 2 + 1);
                        bricks.add(brick);
                    }
                break;
            }
        }
        return bricks;
    }
}
