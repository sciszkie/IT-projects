package lab7;

public class Student extends Person {
    private int age;

    Student(String name, String surname, int age) {
       super(name, surname);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public static void main(String[] args) {
        Student student = new Student("jan", "kowalski", 15);
        System.out.println(student.getAge());
        student.setAge(17);
        System.out.println(student.getAge());
    }
}

