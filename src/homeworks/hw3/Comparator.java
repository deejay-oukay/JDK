package homeworks.hw3;

public class Comparator {
//2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если они одинаковые, и false в противном случае.
//   Массивы могут быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа по парно по индексам.
//   То есть тип элемента в первом массиве под нулевым индексом такой же как тип элемента во втором массиве под нулевым индексом (и под всеми остальными индексами аналогично)

    public static <T1,T2> boolean compareArrays(T1[] array1, T2[] array2) {
        if (!array1.getClass().equals(array2.getClass())) {
            System.out.printf("Разные типы данных массивов: %s и %s\n",array1.getClass().getSimpleName(),array2.getClass().getSimpleName());
            return false;
        }
        if (array1.length != array2.length) {
            System.out.printf("Разные длины массивов: %s и %s\n",array1.length,array2.length);
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].getClass().equals(array2[i].getClass())) {
                System.out.printf("Разные типы данных %d-ых элементов: %s и %s\n",i,array1[i].getClass().getSimpleName(),array2[i].getClass().getSimpleName());
                return false;
            }
            if (!array1[i].equals(array2[i])) {
                System.out.printf("Разные значения %d-ых элементов: %s и %s\n",i,array1[i],array2[i]);
                return false;
            }
        }
        System.out.println("Массивы равны");
        return true;
    }
}