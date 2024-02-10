package homeworks.hw1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static JButton btnStart;
    private static JButton btnStop;

    private static JTextArea txtArea;
    ServerWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat server");
        setResizable(false);
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");
        btnStart.addActionListener(e -> serverStart());
        btnStop.addActionListener(e -> serverStop());
        btnStop.setEnabled(false);
        JPanel pBottom = new JPanel(new GridLayout(1, 2));
        pBottom.add(btnStart);
        pBottom.add(btnStop);
        txtArea = new JTextArea("Сервер ожидает запуска...\n");
        txtArea.setEditable(false);
        add(txtArea);

        add(pBottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    private static void serverStart() {
        serverMessage("Сервер запущен!");
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    private static void serverStop() {
        serverMessage("Сервер остановлен!");
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
    }

    private static void serverMessage(String newMessage) {
        txtArea.setText(txtArea.getText()+newMessage+"\n");
    }
}
