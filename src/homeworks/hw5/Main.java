package homeworks.hw5;

//Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
//Вилки лежат на столе между каждой парой ближайших философов.
//Каждый философ может либо есть, либо размышлять.
//Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
//Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать).
//Можно брать только две вилки одновременно.
//Каждый философ должен поесть три раза.
public class Main {
    static Fork[] forks = new Fork[Philosopher.getMax()];
    static Thread[] fTreads = new Thread[forks.length];
    static Thread[] pThreads = new Thread[Philosopher.getMax()];
    public static void main(String[] args) {
        //раскладываем вилки
        for (int i = 0; i < fTreads.length; i++) {
            forks[i] = new Fork(i);
            fTreads[i] = new Thread(forks[i]);
            fTreads[i].start();
        }
        //зовём гостей
        for (int i = 0; i < pThreads.length; i++) {
            pThreads[i] = new Thread(new Philosopher(i));
            pThreads[i].start();
        }
    }
}
