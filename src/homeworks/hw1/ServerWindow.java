package homeworks.hw1;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerWindow extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private static JButton btnStart;
    private static JButton btnStop;
    private static JTextArea txtArea;
    public static boolean isStarted = false;
    private static ArrayList<ClientGUI> users = new ArrayList<>();
    private static File log = new File("history.log");
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
        btnStart.requestFocus();
    }

    private static void serverStart() {
        isStarted = true;
        addToLog("Сервер запущен!");
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);
        btnStop.requestFocus();
    }

    private static void serverStop() {
        isStarted = false;
        addToLog("Сервер остановлен!");
        btnStop.setEnabled(false);
        btnStart.setEnabled(true);
        btnStart.requestFocus();
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
        String history = restoreFromFile();
        sendToClient(newUser, "Вы успешно подключились\n");
        sendToClient(newUser, restoreFromFile());
        return true;
    }

    private static void sendToClients(String message) {
        for (ClientGUI user: users)
            user.messageFromServer(message);
    }

    private static void sendToClient(ClientGUI user, String message) {
        user.messageFromServer(message);
    }

    public static boolean isStarted() {
        return isStarted;
    }

    public static void getMessage(ClientGUI user, String message) {
        addToLog(user.getLogin()+": "+message);
        sendToClients(user.getLogin()+": "+message);
        saveToFile(user.getLogin()+": "+message);
    }

    private static void saveToFile(String message) {
        try (FileWriter fw = new FileWriter(log,true)) {
            fw.write(message+"\n");
        } catch (IOException e) {
            addToLog(e.getMessage());
        }
    }

    private static String restoreFromFile() {
        try (Scanner scanner = new Scanner(log)) {
            scanner.useDelimiter("\\Z");
            return(scanner.next());
        } catch (FileNotFoundException e) {
            if (e.getMessage() != "history.log (Не удается найти указанный файл)")
                addToLog(e.getMessage());
        }
        return "";
    }
}
