package homeworks.hw2.server;

import homeworks.hw2.client.Client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Бакэнд сервера, который ничего не знает о фронтэнде
public class Server {
    private ServerView view;
    private Client client;
    private static boolean isStarted = false;
    private static final ArrayList<Client> users = new ArrayList<>();
    private static final File log = new File("./history.log");

    public static boolean isStarted() {
        return isStarted;
    }
    public void start() {
        isStarted = true;
    }

    public void stop() {
        isStarted = false;
        users.clear();
    }

    public static boolean isLoginCorrect(Client newUser) {
        for (Client user: users){
            if (user.getName().equals(newUser.getName()))
                return false;
        }
        users.add(newUser);
        return true;
    }

    public static ArrayList<Client> getUsers() {
        return users;
    }

    public static void logSaveToFile(String message) throws IOException {
        try (FileWriter fw = new FileWriter(log,true)) {
            fw.write(message+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String historyRestore() throws FileNotFoundException {
        try (Scanner scanner = new Scanner(log)) {
            scanner.useDelimiter("\\Z");
            return(scanner.next());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("history.log (Не удается найти указанный файл)");
        }
    }

    public void getMessage(Client user, String message) {
        view.getMessage(user, message);
    }

    public boolean userIsConnected(Client cUser) {
        if (!isStarted)
            return false;
        for (Client user: users){
            if (user.getName().equals(cUser.getName()))
                return true;
        }
        return false;
    }

}
