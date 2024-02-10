package seminars.s1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;
    private int gameMode = 0;
    private int fieldSize = 3;
    private int victoryLength = 3;

    JButton btnStart;

    SettingWindow(GameWindow gameWindow){
        btnStart = new JButton("Start new game");

        setLocationRelativeTo(gameWindow);
        setSize(WIDTH, HEIGHT);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameWindow.startNewGame(gameMode, fieldSize, fieldSize, victoryLength);
            }
        });

        add(createMainPanel());
        add(btnStart, BorderLayout.SOUTH);
    }

    private JPanel createModePanel() {
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        JLabel jL = new JLabel("Выберите режим игры");
        JRadioButton rb1 = new JRadioButton("Человек против компьютера");
        JRadioButton rb2 = new JRadioButton("Человек против человека");
        ButtonGroup bg = new ButtonGroup();
        rb1.setSelected(true);
        rb1.addActionListener((e)-> {
            gameMode = 0;
        });
        rb2.addActionListener((e)-> {
            gameMode = 1;
        });
        bg.add(rb1);
        bg.add(rb2);
        jPanel.add(jL);
        jPanel.add(rb1);
        jPanel.add(rb2);
        return jPanel;
    }

    private JPanel createFieldPanel() {
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        JLabel jL = new JLabel("Выберите размер поля");
        JSlider jSlider = new JSlider(3,10,3);
        JLabel jL2 = new JLabel(String.format("Установленный размер поля: %d",jSlider.getValue()));
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jSlider.getValue() < victoryLength)
                    jSlider.setValue(victoryLength);
                jL2.setText(String.format("Установленный размер поля: %d",jSlider.getValue()));
                fieldSize = jSlider.getValue();
            }
        });
        jPanel.add(jL);
        jPanel.add(jL2);
        jPanel.add(jSlider);
        return jPanel;
    }

    private JPanel createVictoryPanel() {
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        JLabel jL = new JLabel("Выберите длину для победы");
        JSlider jSlider = new JSlider(3,10,3);
        JLabel jL2 = new JLabel(String.format("Установленная длина поля: %d",jSlider.getValue()));
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (jSlider.getValue() > fieldSize)
                    jSlider.setValue(fieldSize);
                jL2.setText(String.format("Установленная длина: %d",jSlider.getValue()));
                victoryLength = jSlider.getValue();
            }
        });
        jPanel.add(jL);
        jPanel.add(jL2);
        jPanel.add(jSlider);
        return jPanel;
    }

    private JPanel createMainPanel() {
        JPanel jPanel = new JPanel(new GridLayout(3,1));
        jPanel.add(createModePanel());
        jPanel.add(createFieldPanel());
        jPanel.add(createVictoryPanel());
        return jPanel;
    }
}