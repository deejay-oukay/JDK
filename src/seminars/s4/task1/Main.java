package seminars.s4.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//В рамках выполнения задачи необходимо:
//1. Создайте коллекцию мужских и женских имен с помощью интерфейса List
//2. Отсортируйте коллекцию в алфавитном порядке
//3. Отсортируйте коллекцию по количеству букв в слове
//4. Разверните коллекцию
public class Main {
    public static void main(String[] args) {
        List<String> list = createList();
        System.out.println(sotyByAlphabet(list));
        System.out.println(sortByLength(list));
        System.out.println(reverse(list));
    }

    private static List<String> reverse(List<String> list) {
        return list.stream().sorted(Comparator.reverseOrder()).toList();
    }

    private static List<String> sortByLength(List<String> list) {
        //return list.stream().sorted((e1,e2) -> e1.length() - e2.length()).toList();
        //return list.stream().sorted((e1,e2) -> Integer.compare(e1.length(), e2.length())).toList();
        return list.stream().sorted(Comparator.comparingInt(String::length)).toList();
    }

    private static List<String> sotyByAlphabet(List<String> list) {
        list.sort((e1, e2) -> e1.toLowerCase().compareTo(e2.toLowerCase()));
        //list.sort(Comparator.comparing(String::toLowerCase));
        return list;
    }

    static List<String> createList(){
        List<String> list = new ArrayList<>();
        list.add("Костя");
        list.add("Мария");
        list.add("Святослав");
        list.add("Кирилл");
        list.add("Семён");
        list.add("Светлана");
        return list;
    }
}
