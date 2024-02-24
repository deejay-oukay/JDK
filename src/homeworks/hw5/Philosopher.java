package homeworks.hw5;

import java.util.Random;

public class Philosopher implements Runnable {
    private static final int max = 5;
    private final int id;
    private final String name;
    private final int[] forks = new int[2];
    private static final String[] names = {"Аристотель","Платон","Пифагор","Евклид","Гиппократ"};
    private static final String[][] ideas = {
            {"Надежда - это сон наяву","Все льстецы - прихвостни","Город - единство непохожих"},
            {"Бог в нас самих","Книга — немой учитель","Сколько рабов, столько врагов"},
            {"Уходя на чужбину, не оборачивайся","Не поднимайте пыли на жизненном пути","Начало — половина целого"},
            {"Что и требовалось доказать","Линия - длина без ширины","Нет царского пути в геометрии"},
            {"Беспричинная усталость предвещает болезнь","Врач должен лечить не болезнь, а больного","Душа человека развивается до самой смерти"},
    };

    private volatile int status = 0;
    private volatile int ateCount = 0;
    private static final String[] statuses = {
            "садится за стол",                      //0
            "хочет есть",                           //1
            "ждёт, когда освободятся вилки",        //2
            "взял вилки",                           //3
            "ест",                                  //4
            "поел",                                 //5
            "возвращает на место вилки",            //6
            "размышляет",                           //7
            "говорит",                              //8
            "провёл день в хорошей компании"        //9
    };

    @Override
    public void run() {
        do {
            switch (status) {
                case 1, 7, 9:
                    System.out.printf("Ф%d. %s %s\n",id,name,statuses[status]);
                    status++;
                    randomPause();
                    break;
                case 2:
                    System.out.printf("Ф%d. %s %s В%d и В%d\n",id,name,statuses[status],forks[0],forks[1]);
                    //здесь возможна конкуренция потоков, и может быть взята только одна (или ни одной)
                    if (tryToTakeForks())
                        status++;
                    randomPause();
                    break;
                case 3:
                    //если успел взять только одну, но не успел взять вторую, то возвращает ту, что есть (попробуеут в следующий раз)
                    if (checkForks())
                    {
                        System.out.printf("Ф%d. %s %s В%d и В%d\n",id,name,statuses[status],forks[0],forks[1]);
                        status++;
                    }
                    else
                    {
                        System.out.printf("Ф%d. %s не смог одновременно взять вилки В%d и В%d\n",id,name,forks[0],forks[1]);
                        returnForks();
                        status--;
                    }
                    randomPause();
                    break;
                case 4:
                    System.out.printf("Ф%d. %s %s\n",id,name,statuses[status]);
                    status++;
                    ateCount++;
                    randomPause();
                    break;
                case 5:
                    System.out.printf("Ф%d. %s %s в %d-й раз\n",id,name,statuses[status],ateCount);
                    status++;
                    randomPause();
                    break;
                case 6:
                    System.out.printf("Ф%d. %s %s В%d и В%d\n",id,name,statuses[status],forks[0],forks[1]);
                    returnForks();
                    status++;
                    randomPause();
                    break;
                case 8:
                    System.out.printf("Ф%d. %s %s: \"%s\"\n",id,name,statuses[status],ideas[id][ateCount-1]);
                    if (ateCount < ideas[id].length)
                        //сможет ещё что-нибудь изречь, когда снова поест
                        status = 1;
                    else
                        //пора и честь знать
                        status++;
                    randomPause();
                    break;
            }
        }
        while (status < statuses.length);
    }

    private boolean tryToTakeForks(){
        if ((Main.forks[forks[0]].getOwner() == null) && (Main.forks[forks[1]].getOwner() == null))
        {
            Main.forks[forks[0]].take(this);
            Main.forks[forks[1]].take(this);
            return true;
        }
        return false;
    }

    private boolean checkForks(){
        if ((Main.forks[forks[0]].getOwner() == this) && (Main.forks[forks[1]].getOwner() != this))
        {
            Main.forks[forks[0]].put();
            return false;
        }
        if ((Main.forks[forks[0]].getOwner() != this) && (Main.forks[forks[1]].getOwner() == this))
        {
            Main.forks[forks[1]].put();
            return false;
        }
        return true;
    }

    private void returnForks(){
        Main.forks[forks[0]].put();
        Main.forks[forks[1]].put();
    }

    private void randomPause(){
        try {
            Thread.sleep(new Random().nextInt(1000,3000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Philosopher(int id) {
        this.id = id;
        name = names[id];
        forks[0] = id;
        forks[1] = id+1;
        if (forks[1] == max)
            forks[1] = 0;
        System.out.printf("Ф%d. %s %s между вилками %d и %d\n",id,name,statuses[status],forks[0],forks[1]);
        status++;
        randomPause();
    }

    public static int getMax() {
        return max;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
