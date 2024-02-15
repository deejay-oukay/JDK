package homeworks.hw2.client;

import homeworks.hw2.server.Server;
import homeworks.hw2.server.ServerWindow;

//Бакэнд клиента, который ничего не знает о фронтэнде
public class Client {
    private ClientView view;
    private String name;
    private final Server server;
    private boolean connected = false;

    public Client(ClientView view, Server server) {
        this.view = view;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int connectToSever(String login, String password) {
        setName(login);
        if ("".equals(login))
            return 1;
        else if ("".equals(password))
            return 2;
        else if (!server.isStarted())
            return 3;
        else if (!server.isLoginCorrect(this))
            return 4;
        else
            return 0;
    }

    public void sendMessage(String message) {
        server.getMessage(this,message);
    }

    public boolean isConnected() {
        connected = server.userIsConnected(this);
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public void messageFromServer(String message) {
        view.messageFromServer(message);
    }

}
