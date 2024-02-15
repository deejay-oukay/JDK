package homeworks.hw2;

import homeworks.hw2.client.ClientGUI;
import homeworks.hw2.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientGUI(serverWindow);
        new ClientGUI(serverWindow);
    }
}
