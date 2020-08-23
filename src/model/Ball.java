package model;

public class Ball extends BaseObject {
    private int velocityX = -Model.BALL_VELOCITY_X, velocityY = -Model.BALL_VELOCITY_Y;

    public Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void update() {
        x += velocityX;
        y += velocityY;
    }

    public void changeVelocity(int newVelocityX, int newVelocityY) {
        if (newVelocityX != 0) {
            velocityX = newVelocityX;
        }
        if (newVelocityY != 0) {
            velocityY = newVelocityY;
        }
    }

    void checkBrick(Brick brick) {
        //brick.setDestroyed(true);

        int overlapLeft = this.getRight() - brick.getLeft();
        int overlapRight = brick.getRight() - this.getLeft();
        int overlapTop = this.getBottom() - brick.getTop();
        int overlapBottom = brick.getBottom() - this.getTop();

        boolean ballFromLeft = overlapLeft < overlapRight;
        boolean ballFromTop = overlapTop < overlapBottom;

        int minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
        int minOverlapY = ballFromTop ? overlapTop : overlapBottom;

        if (minOverlapX < minOverlapY) {
            this.velocityX = ballFromLeft ? -Model.BALL_VELOCITY_X : Model.BALL_VELOCITY_X;
        } else {
            this.velocityY = ballFromTop ? -Model.BALL_VELOCITY_Y : Model.BALL_VELOCITY_Y;
        }
    }

    void checkPaddle(Paddle paddle) {
        if (x + width / 2 != paddle.getX() + paddle.getWidth() / 2) {
        int mid = (x + width) / 2;
        int side = (mid - paddle.getX()) / paddle.getSideLength();
        int n = (int)Math.floor(paddle.getN() / 2) + 1;
        velocityX = side - n;
        velocityY = - n - velocityX;
        }
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }
}
