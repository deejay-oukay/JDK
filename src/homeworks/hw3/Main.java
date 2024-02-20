package homeworks.hw3;

public class Main {
    public static void main(String[] args) {
//1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
//   Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
        System.out.println("Задание 1:");
        Number calcRes;
        calcRes = Calculator.sum(1, 2.3);
        System.out.println(calcRes);
        calcRes = Calculator.multiply(4, -5.6f);
        System.out.println(calcRes);
        calcRes = Calculator.divide(8.9, 10);
        System.out.println(calcRes);
        calcRes = Calculator.subtract(11.12f, -13);
        System.out.println(calcRes);
//2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые, и false в противном случае.
//   Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
//   То есть тип элемента в первом массиве под нулевым индексом такой же как тип элемента во втором массиве под нулевым индексом (и под всеми остальными индексами аналогично)
        System.out.println("Задание 2:");
        System.out.println(Comparator.compareArrays(new String[]{"1","2","3"},new Integer[]{1,2,3}));
        System.out.println(Comparator.compareArrays(new Float[]{1f,2f,3f},new Integer[]{1,2,3}));
        System.out.println(Comparator.compareArrays(new Object[]{1,2,3},new Integer[]{1,2,3}));
        System.out.println(Comparator.compareArrays(new Object[]{1,2,3},new Object[]{1,"2",3}));
        System.out.println(Comparator.compareArrays(new Object[]{1,2,3},new Number[]{1,2f,3}));
        System.out.println(Comparator.compareArrays(new Number[]{1,2f,3,4f},new Number[]{1,2f,3}));
        System.out.println(Comparator.compareArrays(new Number[]{1,2f,3,4f},new Number[]{1f,2,3f,4}));
        System.out.println(Comparator.compareArrays(new Number[]{1,2,3,4},new Number[]{1,2,3,4}));
//3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
//   Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих пары, а также переопределение метода toString(),
//   возвращающее строковое представление пары. Работу сдать в виде ссылки на гит репозиторий
        System.out.println("Задание 3:");
        System.out.println(new Pair<>("14.15f","Просто тест"));
    }
}
