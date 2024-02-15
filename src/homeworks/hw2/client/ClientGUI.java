package homeworks.hw2.client;

import homeworks.hw2.server.ServerWindow;

import javax.swing.*;
import java.awt.*;

//Фронтэнд клиента, который всё знает о бакэнде
public class ClientGUI extends JFrame implements ClientView {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JButton btnSend;
    private final JTextField txtMessage;
    private final JTextField txtLogin;
    private final JPasswordField txtPassword;
    private final JTextArea txtArea;
    private final JPanel pTop;
    private ClientView view;
    private Client client;

    public ClientGUI(ServerWindow serverWindow) {
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
        txtLogin.addActionListener(e -> connectToServer());
        txtPassword = new JPasswordField("12345");
        txtPassword.addActionListener(e -> connectToServer());
        JButton btnLogin = new JButton("login");
        btnLogin.addActionListener(e -> connectToServer());
        JPanel pUserData = new JPanel(new GridLayout(1, 3));
        pUserData.add(txtLogin,BorderLayout.WEST);
        pUserData.add(txtPassword);
        pUserData.add(btnLogin,BorderLayout.EAST);

        pTop = new JPanel(new GridLayout(2, 3));
        pTop.add(pSettings,BorderLayout.NORTH);
        pTop.add(pUserData,BorderLayout.SOUTH);
        add(pTop,BorderLayout.NORTH);

        txtMessage = new JTextField();
        txtMessage.addActionListener(e -> sendMessage(txtMessage.getText()));
        btnSend = new JButton("send");
        btnSend.addActionListener(e -> sendMessage(txtMessage.getText()));
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
        client = new Client(this, serverWindow.server);
    }

    @Override
    public void connectToServer() {
        int test = client.connectToSever(txtLogin.getText(),txtPassword.getText());
        switch (test) {
            case 1:
                addToLog("Логин не указан");
                txtLogin.requestFocus();
                break;
            case 2:
                addToLog("Пароль не указан");
                txtPassword.requestFocus();
                break;
            case 3:
                addToLog("Сервер остановлен");
                break;
            case 4:
                addToLog("Такой логин уже кем-то занят");
                txtLogin.requestFocus();
            default:
                pTop.setVisible(false);
                btnSend.setEnabled(true);
                txtMessage.requestFocus();
                client.setConnected(true);
                break;
        }
    }

    @Override
    public boolean connectedToServer() {
        return client.isConnected();
    }

    private void addToLog(String message) {
        txtArea.setText(txtArea.getText()+message+"\n");
    }

    public void messageFromServer(String message) {
        addToLog(message);
        if (!connectedToServer())
        {
            pTop.setVisible(true);
            btnSend.setEnabled(false);
            client.setName("");
            txtLogin.requestFocus();
        }
    }

    @Override
    public void sendMessage(String message) {
        if (!"".equals(message))
            client.sendMessage(txtMessage.getText());
        else
            txtMessage.requestFocus();
        txtMessage.setText("");
    }
}
