package app;

import model.Model;
import view.StartPanel;
import view.View;

import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import static model.Model.*;

public class Controller {
    private final int delay = 0;
    private final int period = 12;

    private Model model;
    private View view;
    private Timer timer;
    private TimerTask timerTask;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        addListeners();
    }

    private void addTimer() {
        timer = new Timer("timer");
        timerTask = new TimerTask() {
            @Override
            public void run() {
                model.update();
                view.repaint();

                if (isDead) {
                    cancel();
                    //view.switchToDie();
                    view.switchToWin();
                }
                if (isWon) {
                    cancel();
                    //view.switchToWin();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, delay, period);
    }

    private void addListeners() {
        //move paddle
        this.view.getGamePanel().addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX();
                model.setPaddlePosition(x);
                if (!isStarted) {
                    model.setBallPosition(x);
                }
                view.repaint();
            }
        });

        //click paddle
        this.view.getGamePanel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                isStarted = true;
                addTimer();
            }
        });

        //click play button
        ((StartPanel) (this.view.getStartPanel())).getStart().addActionListener(actionEvent -> {
            view.switchToGamePanel();
        });

        //continue
        this.view.getWinPanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                if (model.getLevel() == 2) {
                    model.setLevel(1);
                } else {
                    model.incLevel();
                }
                model.restart();
                view.switchToGamePanel();
            }
            }
        });

        //restart
        this.view.getDiePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {
                    model.setLevel(1);
                    model.restart();
                    view.switchToGamePanel();
                }
            }
        });

        //f1
        this.view.getGamePanel().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_ESCAPE) {
                    JOptionPane.showMessageDialog(null, "Created by Hanna Herasimchyk.\n" +
                            "Press Enter to \"Go to the next level\" (if you won)\nor \"Restart\" (if you lose)");
                }
            }
        });

    }
}
