package seminars.s3.task2;

import java.util.Arrays;
import java.util.Iterator;

// Описать собственную коллекцию – список на основе массива.
// Коллекция должна иметь возможность хранить любые типы данных, иметь методы добавления и удаления элементов.
public class Main {
    public static void main(String[] args) {

        MyCollection<String> myCollection = new MyCollection<>();
        myCollection.add("one");
        myCollection.add("two");
        myCollection.add("three");
        System.out.println(myCollection);
// System.out.println(myCollection.get(1));
// myCollection.delete(1);
// System.out.println(myCollection);
        for (String s: myCollection) {
            System.out.println(s);
        }

    }
}

class MyCollection<T> implements Iterable<T>{

    private int count = 0;

    private Object[] arr;

    public MyCollection(int initialSize) {
        arr = new Object[initialSize];
    }

    public MyCollection() {
        this(10);
    }

    public void add(T t) {
        if (count == arr.length) {
            arr = Arrays.copyOf(arr, 2 * arr.length);
        }
        arr[count++] = t;
    }

    public T get(int pos) {
        if (pos > count || pos < 0) return null;
        return (T) arr[pos];
    }

    public void delete(int pos) {
        for (int i = pos; i < count; i++) {
            arr[i] = arr[i+1];
        }
        count--;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("MyCollection(");
        for (int i = 0; i < count; i++) {
            stringBuilder.append(arr[i]).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(")");
        return stringBuilder.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < count;
            }

            @Override
            public T next() {
                return (T) arr[pos++];
            }
        };
    }
}