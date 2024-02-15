package homeworks.hw2.server;

import homeworks.hw2.client.Client;
import homeworks.hw2.client.ClientGUI;

//Интерфейс сервера для общения с клиентами
public interface ServerView {
    void sendToClients(String message);
    void sendToClient(Client user, String message);
    void getMessage(Client user, String message);
    boolean newUserConnection(Client client);
}
