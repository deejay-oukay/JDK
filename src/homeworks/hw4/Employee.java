package homeworks.hw4;

public class Employee {
//каждый сотрудник должен иметь следующие атрибуты:
//-Табельный номер
//-Номер телефона
//-Имя
//-Стаж
    private int tabNumber;
    private int phoneNumber;
    private String name;
    private int experience;

    public Employee(int tabNumber, int phoneNumber, String name, int experience) {
        this.tabNumber = tabNumber;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    public int getTabNumber() {
        return tabNumber;
    }

    public int getExperience() {
        return experience;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Табельный номер: " + tabNumber + ", Телефон: " + phoneNumber + ", Имя: " + name + ", Стаж: " + experience + ". ";
    }
}
