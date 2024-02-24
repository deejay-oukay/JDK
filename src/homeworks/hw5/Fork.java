package homeworks.hw5;

public class Fork implements Runnable {
    private final int id;
    private volatile Philosopher owner = null;

    public Fork(int id) {
        this.id = id;
        System.out.printf("В%d. Вилка лежит на столе...\n",id);
    }

    public void take(Philosopher newOwner){
        owner = newOwner;
        System.out.printf("В%d. Вилку попытался взять Ф%d - %s...\n",id,owner.getId(),owner.getName());
    }

    public void put(){
        if (owner != null)
            System.out.printf("В%d. Вилку вернул на стол Ф%d - %s...\n",id,owner.getId(),owner.getName());
        owner = null;
    }

    public Philosopher getOwner() {
        return owner;
    }

    @Override
    public void run() {

    }
}
