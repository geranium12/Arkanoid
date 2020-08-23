package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

import static model.Model.*;

public class WinPanel extends JPanel {
    private Model model;
    private BaseObjectView background;

    WinPanel(Model model) {
        this.model = model;
        background = new BaseObjectView("space.jpg");
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        render(graphics2D);
    }
    private void render(Graphics2D g) {
        drawBackground(g);
        drawText(g);
    }

    private void drawBackground(Graphics2D g) {
        int windowWidth = (int) model.getWindowDimension().getWidth();
        int windowHeight = (int) model.getWindowDimension().getHeight();

        g.drawRect(0, 0, windowWidth, windowHeight);
        int x = windowWidth - 2 * INDENT;
        int y = windowHeight - 2 * INDENT - 25;
        g.drawImage(background.getImage(), INDENT, INDENT, x, y, null);
    }


    private void drawText(Graphics2D g) {
        int windowWidth = (int) model.getWindowDimension().getWidth();
        int windowHeight = (int) model.getWindowDimension().getHeight();

        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 90));
        g.setColor(Color.RED);
        g.drawString("You win with score = " + model.getScore() + "!", (int)(windowWidth / 6),
                (int)(windowHeight / 2.2));
    }
}
