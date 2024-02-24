package seminars.s5.task1;
//В рамках выполнения задачи необходимо:
//- Создать два класс ObjectA, ObjectB
//- Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
public class Main {

    public static void main(String[] args) {
        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();
        Thread firstThread = new Thread(() -> {
            synchronized (objectA) {
                System.out.println("Объект А заблокирован, ожидает объект Б");
                synchronized (objectB) {
                    System.out.println("Объект Б заблокирован");
                }
            }
        });
        Thread secondThread = new Thread(() -> {
            synchronized (objectB) {
                System.out.println("Объект Б заблокирован, ожидает объект А");
                synchronized (objectA) {
                    System.out.println("Объект А заблокирован");
                }
            }
        });
        firstThread.start();
        secondThread.start();
    }
}
