package homeworks.hw2.server;

import homeworks.hw2.client.Client;
import homeworks.hw2.client.ClientGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Фронтэнд Сервера, который всё знает о фронтэнде
public class ServerWindow extends JFrame implements ServerView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static JButton btnStart;
    private static JButton btnStop;
    private static JTextArea txtArea;
    public Server server;
    public ServerWindow() {
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
        btnStart.requestFocus();
        server = new Server(this);
    }

    private void serverStart() {
        server.start();
        addToLog("Сервер запущен!");
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        btnStop.requestFocus();
    }

    private void serverStop() {
        addToLog("Сервер остановлен!");
        sendToClients("Вы были отключены от сервера!");
        server.stop();
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        btnStart.requestFocus();
    }

    private static void addToLog(String message) {
        txtArea.append(message+"\n");
    }

    @Override
    public void sendToClients(String message) {
        for (Client user: server.getUsers())
            user.messageFromServer(message);
    }

    @Override
    public void sendToClient(Client user, String message) {
        user.messageFromServer(message);
    }

    @Override
    public void newUserConnection(Client client) {
        try {
            sendToClient(client, "Вы успешно подключились!");
            sendToClient(client, server.historyRestore());
        } catch (FileNotFoundException e) {
            addToLog("Файл с историей не найден");
        }
    }

    @Override
    public void getMessage(Client user, String message) {
        message = user.getName()+": "+message;
        addToLog(message);
        sendToClients(message);
        try {
            server.logSaveToFile(message);
        } catch (IOException e) {
            addToLog(e.getMessage());
        }
    }

}
