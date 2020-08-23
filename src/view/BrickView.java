package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BrickView extends BaseObjectView {
    private int level;

    public BrickView(int level, int health) {
        this.level = level;
        int newLevel = (level - 1) * 2 + 1;
        try {
            if (health == level) {
                image = ImageIO.read(new File(DATA_LOCATION + "0" + newLevel + "-Breakout-Tiles.png"));
            } else {
                image = ImageIO.read(new File(DATA_LOCATION + "0" + (newLevel + 1) + "-Breakout-Tiles.png"));
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Open image error!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
