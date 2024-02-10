package homeworks.hw1;

import javax.swing.*;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    ClientGUI(ServerWindow serverWindow) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        setTitle("Chat client");
        setResizable(false);

        setVisible(true);
    }
}
