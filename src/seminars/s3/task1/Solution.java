package seminars.s3.task1;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;

//Создать обобщенный класс с тремя параметрами (T, V, K).
//Класс содержит три переменные типа (T, V, K),
//конструктор, принимающий на вход параметры типа (T, V, K),
//методы возвращающие значения трех переменных.
//
//Создать метод, выводящий на консоль имена классов для трех переменных класса.
//Наложить ограничения на параметры типа:
//T должен реализовать интерфейс Comparable,
//V должен реализовать интерфейс DataInput и расширять класс InputStream,
//K должен расширять класс Number.

public class Solution <K,T extends Comparable<T>,V extends InputStream & DataInput>{
    private K k;
    private T t;
    private V v;

    public Solution(K k, T t, V v) {
        this.k = k;
        this.t = t;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public void getClassName() {
        System.out.printf("k = %s, t = %s, v = %s", k.getClass().getSimpleName(),t.getClass().getSimpleName(),v.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        Solution<Integer,String, DataInputStream> solution = new Solution<>(1,":",new DataInputStream(System.in));
    }
}
