package homeworks.hw1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static JButton btnStart;
    private static JButton btnStop;
    private static JTextArea txtArea;
    public static boolean isStarted = false;
    private static ArrayList<ClientGUI> users = new ArrayList<>();
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
        isStarted = true;
        addToLog("Сервер запущен!");
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
    }

    private static void serverStop() {
        isStarted = false;
        addToLog("Сервер остановлен!");
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        sendToClients("Вы были отключены от сервера!");
        users.clear();
    }

    private static void addToLog(String message) {
        txtArea.setText(txtArea.getText()+message+"\n");
    }

    public static boolean isLoginCorrect(ClientGUI newUser) {
        for (ClientGUI user: users){
            if (user.getLogin().equals(newUser.getLogin()))
                return false;
        }
        users.add(newUser);
        return true;
    }

    private static void sendToClients(String message) {
        for (ClientGUI user: users)
            user.messageFromServer(message);
    }

}
