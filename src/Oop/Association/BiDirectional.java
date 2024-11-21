package Oop.Association;
import java.util.ArrayList;
import java.util.List;

class Teacher {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Department {
    private String name;
    private List<Teacher> teachers;

    public Department(String name) {
        this.name = name;
        this.teachers = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void displayTeachers() {
        System.out.println("Teachers in " + name + " Department:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher.getName());
        }
    }
}

public class BiDirectional {

    public static void main(String[] args) {
        Department department = new Department("Computer Science");
        Teacher teacher1 = new Teacher("John");
        Teacher teacher2 = new Teacher("Alice");

        department.addTeacher(teacher1);
        department.addTeacher(teacher2);

        department.displayTeachers();
    }
}
