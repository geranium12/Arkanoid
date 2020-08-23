package view;

import model.Model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class View extends JFrame {
    private final String startName = "Start";
    private final String gameName = "Game";
    private final String deadName = "Dead";
    private final String wonName = "Won";


    private BaseObjectView background;
    private BaseObjectView ballView;
    private BaseObjectView paddleView;
    private MusicView musicView;
    private Model model;

    private JPanel cards;
    private JPanel startPanel;
    private GamePanel gamePanel;
    private DiePanel diePanel;
    private JPanel winPanel;

    public View(Model model) throws IOException {
        this.model = model;
        initialize();
    }

    @Override
    public void update(Graphics g) {
        this.repaint();
    }

    private void initialize() throws IOException {
        this.setPreferredSize(model.getWindowDimension());
        this.setResizable(true);
        this.setUndecorated(true);

        try {
            MusicView musicView = new MusicView();
            Clip clip = AudioSystem.getClip();
            clip.open(musicView.getAis());
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Music error!", JOptionPane.ERROR_MESSAGE);
        }

        createGamePanel();
        createStartPanel();
        createDeadPanel();
        createWonPanel();

        cards = new JPanel(new CardLayout());
        cards.add(startPanel, startName);
        cards.add(gamePanel, gameName);
        cards.add(diePanel, deadName);
        cards.add(winPanel, wonName);

        JPanel pane = new JPanel();
        pane.add(cards);

        this.add(pane);

        cards.setPreferredSize(model.getWindowDimension());
        setExtendedState(MAXIMIZED_BOTH);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createGamePanel() {
        gamePanel = new GamePanel(model);
    }

    public void createStartPanel() {
        startPanel = new StartPanel(model);
    }

    public void createDeadPanel() {
        diePanel = new DiePanel(model);
    }

    public void createWonPanel() {
        winPanel = new WinPanel(model);
    }

    public void switchToStart() {
        ((CardLayout) (cards.getLayout())).show(cards, startName);
        startPanel.requestFocus();
    }

    public void switchToGamePanel() {
        ((CardLayout) (cards.getLayout())).show(cards, gameName);
        gamePanel.requestFocus();
    }

    public void switchToDie() {
        ((CardLayout) (cards.getLayout())).show(cards, deadName);
        diePanel.requestFocus();
    }

    public void switchToWin() {
        ((CardLayout) (cards.getLayout())).show(cards, wonName);
        winPanel.requestFocus();
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }


    public JPanel getWinPanel() {
        return winPanel;
    }

    public JPanel getStartPanel() {
        return startPanel;
    }

    public DiePanel getDiePanel() {
        return diePanel;
    }
}
