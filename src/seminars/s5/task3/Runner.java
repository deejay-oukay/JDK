package seminars.s5.task3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable {
    private String name;
    private CountDownLatch cdl;

    public Runner(String name, CountDownLatch cdl) {
        this.name = name;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            goToStart();
            cdl.await();
            running();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name + " идёт к старту");
        Thread.sleep(1000 + new Random().nextInt(1000,2000));
        System.out.println(name + " подошёл к старту");
        cdl.countDown();
    }

    private void running() throws InterruptedException {
        System.out.println(name + " стартовал");
        Thread.sleep(1000 + new Random().nextInt(1000,2000));
        System.out.println(name + " финишировал");
    }
}
