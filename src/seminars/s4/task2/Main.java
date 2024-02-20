package seminars.s4.task2;

import java.util.*;
import java.util.stream.Collectors;

//В рамках выполнения задачи необходимо:
//1. Создайте коллекцию мужских и женских имен с помощью интерфейса List - добавьте повторяющиеся значения
//2. Получите уникальный список Set на основании List
//3. Определите наименьший элемент (алфавитный порядок)
//4. Определите наибольший элемент (по количеству букв в слове но в обратном порядке)
//5. Удалите все элементы содержащие букву ‘A’
public class Main {
    public static void main(String[] args) {
        List<String> nameList = createList();
        System.out.println(nameList);

        Set<String> nameSet = getAsSet(nameList);
        System.out.println(nameSet);

        String firstName = getFirst(nameSet);
        System.out.println(firstName);

        String longestName = getLongest(nameSet);
        System.out.println(longestName);

        Set<String> cuttedSet = cut(nameSet);
        System.out.println(cuttedSet);
    }

    private static Set<String> cut(Set<String> nameSet) {
        //return nameSet.stream().filter(s -> !s.toLowerCase().contains("а")).collect(Collectors.toSet());
        nameSet.removeIf(s -> s.toLowerCase().contains("а"));
        return nameSet;
    }

    private static String getLongest(Set<String> nameSet) {
        //return nameSet.stream().max(Comparator.comparingInt(String::length)).orElse(null);
        return nameSet.stream().max((o1,o2) -> o1.length() - o2.length()).orElse(null);
    }

    private static String getFirst(Set<String> nameSet) {
        TreeSet<String> tree = new TreeSet<>(nameSet);
        return tree.first();
        //return nameSet.stream().min(String::compareTo).orElse(null);
    }

    private static Set<String> getAsSet(List<String> list) {
        return new HashSet<>(list);
    }

    static List<String> createList(){
        List<String> list = new ArrayList<>();
        list.add("Костя");
        list.add("Мария");
        list.add("Мария");
        list.add("Святослав");
        list.add("Кирилл");
        list.add("Кирилл");
        list.add("Семён");
        list.add("Светлана");
        list.add("Светлана");
        return list;
    }}
