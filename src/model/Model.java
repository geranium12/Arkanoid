package model;

import app.Controller;
import tool.Observable;
import tool.Observer;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Model {
    public static final int BRICK_WIDTH = 85, BRICK_HEIGHT = 30;
    public static final int BALL_VELOCITY_X = 3, BALL_VELOCITY_Y = 3;
    public static final int INDENT = 20;
    public static final int MARGIN = 100;

    public static boolean isStarted;
    public static boolean isDead;
    public static boolean isWon;

    private int score;

    private Set<Observer> observers = new HashSet<>();
    private Dimension windowDimension;
    private Ball ball;
    private Paddle paddle;
    private ArrayList<Brick> bricks;
    private int level;
    private int ballX, ballY;

    public Model(Dimension windowDimension) {
        this.windowDimension = windowDimension;
        level = 1;
        restart();
    }

    public void restart() {
        ballX = (int)(windowDimension.getWidth() - 20) / 2;
        ballY = (int)(windowDimension.getHeight()) - 2 * INDENT - 48;
        ball = new Ball(ballX, ballY, 20, 20);
        int paddleWidth1 = 146;
        int paddleWidth2 = 48;

        if (level == 1) {
            paddle = new Paddle((int) (windowDimension.getWidth() - paddleWidth1) / 2,
                    (int) windowDimension.getHeight() - 2 * INDENT - 32, paddleWidth1, 27);
        } else {
            paddle = new Paddle((int) (windowDimension.getWidth() - paddleWidth2) / 2,
                    (int) windowDimension.getHeight() - 2 * INDENT - 32, paddleWidth2, 27);
        }
        bricks = Level.createLevel(level);
        isStarted = false;
        isDead = false;
        isWon = false;
        score = 0;
    }

    public void setPaddlePosition(int x) {
        int pos = x - paddle.getWidth() / 2;
        if (pos < INDENT) {
            pos = INDENT;
        }
        if (pos + paddle.getWidth() > windowDimension.getWidth() - INDENT) {
            pos = (int) windowDimension.getWidth() - INDENT - paddle.getWidth();
        }
        paddle.setX(pos);
    }

    public void setBallPosition(int x) {
        int pos = x;
        if (pos < INDENT + paddle.getWidth() / 2) {
            pos = INDENT + paddle.getWidth() / 2;
        }
        if (pos + paddle.getWidth() / 2 > windowDimension.getWidth() - INDENT) {
            pos = (int) windowDimension.getWidth() - INDENT - paddle.getWidth() / 2;
        }
        pos -= ball.getWidth() / 2;
        ball.setX(pos);
    }

    public void update() {
        ball.update();
        checkBorders();
        if (paddle.isIntersecting(ball)) {
            //ball.checkPaddle(paddle);
            ball.changeVelocity(0, -Model.BALL_VELOCITY_Y);
        }

        Iterator<Brick> it = bricks.iterator();
        Brick brickToRemove = null;
        while (it.hasNext()) {
            Brick brick = it.next();
            if (brick.isIntersecting(ball)) {
                ball.checkBrick(brick);
                incScore();
                brick.decHealth();
                if (brick.getHealth() == 0) {
                    //it.remove();
                    brickToRemove = brick;
                }
                break;
            }
        }

        if (brickToRemove != null) {
            bricks.remove(brickToRemove);
        }

        if (getBricks().isEmpty()) {
            isWon = true;
        }
    }

    public void checkBorders() {
        if (ball.getLeft() < getLeft()) {
            ball.changeVelocity(Model.BALL_VELOCITY_X, 0);
        } else if (ball.getRight() > getRight()) {
            ball.changeVelocity(-Model.BALL_VELOCITY_Y, 0);
        }

        if (ball.getTop() < getTop()) {
            ball.changeVelocity(0, Model.BALL_VELOCITY_Y);
        } else if (ball.getBottom() > getBottom()) {
            isDead = true;
        }
    }

    public void incScore() {
        score++;
    }

    int getLeft() {
        return INDENT;
    }

    int getRight() {
        return (int) windowDimension.getWidth() - INDENT;
    }

    int getTop() {
        return INDENT;
    }

    int getBottom() {
        return (int) windowDimension.getHeight() - INDENT;
    }

    public Dimension getWindowDimension() {
        return windowDimension;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void incLevel() {
        level++;
    }

}
