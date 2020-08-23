package view;

import model.Brick;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static model.Model.*;

public class GamePanel extends JPanel {
    private Model model;

    private BaseObjectView background;
    private BaseObjectView ballView;
    private BaseObjectView paddleView;

    GamePanel(Model model) {
        this.model = model;
        background = new BaseObjectView("space.jpg");
        ballView = new BaseObjectView("58-Breakout-Tiles.png");
        paddleView = new BaseObjectView("56-Breakout-Tiles.png");
    }
    @Override
    public void paint(Graphics g) {

        Graphics2D graphics2D = (Graphics2D)g;
        //RenderingHints rh = new RenderingHints(
        //       RenderingHints.KEY_RENDERING,
        //       RenderingHints.VALUE_RENDER_SPEED);
        //graphics2D.setRenderingHints(rh);
                /*graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);*/
        render(graphics2D);
    }
    private void render(Graphics2D g) {
        drawBackground(g);
        drawBricks(g);
        drawBall(g);
        drawPaddle(g);
        drawScore(g);
    }

    private void drawBackground(Graphics2D g) {
        int windowWidth = (int) model.getWindowDimension().getWidth();
        int windowHeight = (int) model.getWindowDimension().getHeight();

        g.drawRect(0, 0, windowWidth, windowHeight);
        int x = windowWidth - 2 * INDENT;
        int y = windowHeight - 2 * INDENT - 25;
        g.drawImage(background.getImage(), INDENT, INDENT, x, y, null);
    }

    private void drawBall(Graphics2D g) {
        g.drawImage(ballView.getImage(), model.getBall().getX(), model.getBall().getY(),
                model.getBall().getWidth(), model.getBall().getHeight(), null);
    }

    private void drawPaddle(Graphics2D g) {
        g.drawImage(paddleView.getImage(), model.getPaddle().getX(), model.getPaddle().getY(),
                    model.getPaddle().getWidth(), model.getPaddle().getHeight(), null);
    }

    private void drawBricks(Graphics2D g) {
        //try {
        ArrayList<Brick> bricks = model.getBricks();
        for (Brick brick : bricks) {
            BrickView brickView = new BrickView(brick.getLevel(), brick.getHealth());
            g.drawImage(brickView.getImage(), brick.getX(), brick.getY(),
                    BRICK_WIDTH, BRICK_HEIGHT, null);
        }
        //} catch (ConcurrentModificationException ex) {
        //JOptionPane.showMessageDialog(null, ex.getMessage(), "Brick arraylist error!", JOptionPane.ERROR_MESSAGE);
        //}
    }

    private void drawScore(Graphics2D g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Score: " + model.getScore(), (int)(INDENT * 2), (int)(INDENT * 2.5));
    }

}
