package seminars.s5.task3;

import java.util.concurrent.CountDownLatch;

//В рамках выполнения задачи необходимо:
//- 3 бегуна должны прийти к старту гонки
//- Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
//- Программа должна отсчитать “На старт”, “Внимание”, “Марш”
//- Программа должна завершиться когда все участники закончат гонку.
//- Подумайте об использовании примитива синхронизации
public class Main {
    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(4);
        Thread t1 = new Thread(new Runner("Иван",cdl));
        Thread t2 = new Thread(new Runner("Пётр",cdl));
        Thread t3 = new Thread(new Runner("Семён",cdl));

        t1.start();
        t2.start();
        t3.start();

        while (cdl.getCount() > 1){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            System.out.println("На старт!");
            Thread.sleep(500);
            System.out.println("Внимание!");
            Thread.sleep(500);
            System.out.println("Марш!");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        cdl.countDown();
    }
}
