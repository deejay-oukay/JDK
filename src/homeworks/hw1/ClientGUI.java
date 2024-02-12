package homeworks.hw1;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private ServerWindow serverWindow;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnSend;
    private JTextField txtField;
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private JTextArea txtArea;
    private String login;
    private JPanel pTop;
    ClientGUI(ServerWindow serverWindow) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Chat client");
        setResizable(false);
        JTextField txtIP = new JTextField("127.0.0.1");
        JTextField txtPort = new JTextField("8189");
        JLabel lSpacer = new JLabel();
        JPanel pSettings = new JPanel(new GridLayout(1, 3));
        pSettings.add(txtIP,BorderLayout.WEST);
        pSettings.add(txtPort);
        pSettings.add(lSpacer,BorderLayout.EAST);
        txtLogin = new JTextField();
        txtPassword = new JPasswordField("12345");
        JButton btnLogin = new JButton("login");
        btnLogin.addActionListener(e -> login());
        JPanel pUserData = new JPanel(new GridLayout(1, 3));
        pUserData.add(txtLogin,BorderLayout.WEST);
        pUserData.add(txtPassword);
        pUserData.add(btnLogin,BorderLayout.EAST);
        pTop = new JPanel(new GridLayout(2, 3));
        pTop.add(pSettings,BorderLayout.NORTH);
        pTop.add(pUserData,BorderLayout.SOUTH);
        add(pTop,BorderLayout.NORTH);
        btnSend = new JButton("send");
        btnSend.addActionListener(e -> send());
        btnSend.setEnabled(false);
        txtField = new JTextField();
        JPanel pBottom = new JPanel(new GridLayout(1, 2));
        pBottom.add(txtField);
        pBottom.add(btnSend,BorderLayout.EAST);
        add(pBottom, BorderLayout.SOUTH);
        txtArea = new JTextArea();
        //txtArea.setEditable(false);
        add(txtArea);
        setVisible(true);
    }

    private void send() {

    }

    private void login() {
        setLogin(txtLogin.getText());
        if ("".equals(txtLogin.getText()))
            addToLog("Логин не указан");
        else if ("".equals(txtPassword.getText()))
            addToLog("Пароль не указан");
        else if (!serverWindow.isStarted)
            addToLog("Сервер остановлен");
        else if (!serverWindow.isLoginCorrect(this))
            addToLog("Такой логин уже кем-то занят");
        else
        {
            pTop.setVisible(false);
            addToLog("Вы успешно подключились");
            btnSend.setEnabled(true);
        }
    }

    private void addToLog(String message) {
        txtArea.setText(txtArea.getText()+message+"\n");
    }

    public void messageFromServer(String message) {
        addToLog(message);
        System.out.println(message);
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }
}
