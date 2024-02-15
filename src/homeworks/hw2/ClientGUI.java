package homeworks.hw2;

import javax.swing.*;
import java.awt.*;

public class ClientGUI extends JFrame {
    private final ServerWindow serverWindow;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JButton btnSend;
    private final JTextField txtMessage;
    private final JTextField txtLogin;
    private final JPasswordField txtPassword;
    private final JTextArea txtArea;
    private String login;
    private final JPanel pTop;
    ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
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
        txtLogin.addActionListener(e -> login());
        txtPassword = new JPasswordField("12345");
        txtPassword.addActionListener(e -> login());
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

        txtMessage = new JTextField();
        txtMessage.addActionListener(e -> send());
        btnSend = new JButton("send");
        btnSend.addActionListener(e -> send());
        btnSend.setEnabled(false);

        JPanel pBottom = new JPanel(new GridLayout(1, 2));
        pBottom.add(txtMessage);
        pBottom.add(btnSend,BorderLayout.EAST);
        add(pBottom, BorderLayout.SOUTH);
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        add(txtArea);

        setVisible(true);
        txtLogin.requestFocus();
    }

    private void send() {
        String message = txtMessage.getText();
        if (!"".equals(message))
            ServerWindow.getMessage(this,txtMessage.getText());
        else
            txtMessage.requestFocus();
        txtMessage.setText("");
    }

    private void login() {
        setLogin(txtLogin.getText());
        if ("".equals(txtLogin.getText()))
        {
            addToLog("Логин не указан");
            txtLogin.requestFocus();
        }
        else if ("".equals(txtPassword.getText()))
        {
            addToLog("Пароль не указан");
            txtPassword.requestFocus();
        }
        else if (!ServerWindow.isStarted)
            addToLog("Сервер остановлен");
        else if (!ServerWindow.isLoginCorrect(this))
        {
            addToLog("Такой логин уже кем-то занят");
            txtLogin.requestFocus();
        }
        else
        {
            pTop.setVisible(false);
            btnSend.setEnabled(true);
            txtMessage.requestFocus();
        }
    }

    private void addToLog(String message) {
        txtArea.setText(txtArea.getText()+message+"\n");
    }

    public void messageFromServer(String message) {
        addToLog(message);
        if (!ServerWindow.isStarted())
        {
            pTop.setVisible(true);
            btnSend.setEnabled(false);
            login = "";
            txtLogin.requestFocus();
        }
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }
}
