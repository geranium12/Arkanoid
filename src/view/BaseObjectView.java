package view;

import model.BaseObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BaseObjectView {
    protected static final String DATA_LOCATION = "C:\\Archive\\JAVA\\UP\\Lab11\\Arkanoid\\src\\data\\";
    protected Image image;

    public BaseObjectView() {
        image = null;
    }

    public BaseObjectView(String filename) {
        try {
            image = ImageIO.read(new File(DATA_LOCATION + filename));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Open image error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Image getImage() {
        return image;
    }
}
