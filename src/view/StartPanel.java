package view;

import model.Model;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {
    private Model model;
    private JButton start;

    StartPanel(Model model) {
        this.model = model;

        setLayout(null);
        setBackground(Color.WHITE);

        start = new JButton();
        start.setBackground(Color.WHITE);

        BaseObjectView startButtonImage = new BaseObjectView("play_.png");
        ImageIcon imageIcon = new ImageIcon(startButtonImage.getImage());
        start.setIcon(imageIcon);

        start.setOpaque(true);
        start.setContentAreaFilled(false);
        start.setBorderPainted(false);

        int windowWidth = (int) model.getWindowDimension().getWidth();
        int windowHeight = (int) model.getWindowDimension().getHeight();

        start.setBounds(windowWidth / 2 - startButtonImage.getImage().getWidth(null) / 2,
                windowHeight / 2 - startButtonImage.getImage().getHeight(null) / 2,
                startButtonImage.getImage().getWidth(null),
                startButtonImage.getImage().getHeight(null));

        add(start);
    }

    public JButton getStart() {
        return start;
    }
}

