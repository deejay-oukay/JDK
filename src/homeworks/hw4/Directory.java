package homeworks.hw4;

import java.util.ArrayList;
import java.util.List;

//Справочник сотрудников
public class Directory {
    private static List<Employee> list = new ArrayList<>();
    //Добавить метод добавления нового сотрудника в справочник
    public static void add(int tabNumber, int phoneNumber, String name, int experience) {
        if (!tabNumberExists(tabNumber))
            list.add(new Employee(tabNumber, phoneNumber, name, experience));
        else
            System.out.printf("Сотрудник с табельным номером %d уже существует - не может быть добавлен!\n",tabNumber);
    }

    private static boolean tabNumberExists(int tabNumber) {
        for (Employee employee : list) {
            if (employee.getTabNumber() == tabNumber)
                return true;
        }
        return false;
    }

    //Добавить метод, который ищет сотрудника по стажу (может быть список)
    public static List<Employee> filterByExperience(int experience) {
        List<Employee> result = new ArrayList<>(list);
        return result.stream().filter(e -> e.getExperience()==experience).toList();
    }

    //Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
    public static List<Integer> phonesByName(String name){
        List<Employee> result = list.stream().filter(e -> e.getName().equals(name)).toList();
        return result.stream().map(Employee::getPhoneNumber).toList();
    }

    //Добавить метод, который ищет сотрудника по табельному номеру
    public static Employee searchByTabNumber(int tabNumber) {
        for (Employee employee : list) {
            if (employee.getTabNumber() == tabNumber)
                return employee;
        }
        System.out.printf("Не найден\n",tabNumber);
        return null;
    }



}
