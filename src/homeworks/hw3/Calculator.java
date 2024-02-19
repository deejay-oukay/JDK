package homeworks.hw3;

//1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы: sum(), multiply(), divide(), subtract().
//   Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.

public class Calculator {
    public static <T1 extends Number, T2 extends Number> double sum(T1 t1, T2 t2) {
        return t1.doubleValue() + t2.doubleValue();
    }
    public static <T1 extends Number, T2 extends Number> double multiply(T1 t1, T2 t2) {
        return t1.doubleValue() * t2.doubleValue();
    }
    public static <T1 extends Number, T2 extends Number> double divide(T1 t1, T2 t2) {
        return t1.doubleValue() / t2.doubleValue();
    }
    public static <T1 extends Number, T2 extends Number> double subtract(T1 t1, T2 t2) {
        return t1.doubleValue() - t2.doubleValue();
    }
}
