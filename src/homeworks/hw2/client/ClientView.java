package homeworks.hw2.client;

//Интерфейс клиента для общения с сервером
public interface ClientView {
    void sendMessage(String message);
    void connectToServer();
    boolean connectedToServer();
    void messageFromServer(String message);

}
