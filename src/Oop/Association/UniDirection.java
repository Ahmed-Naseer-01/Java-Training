package Oop.Association;

class Library {
    private String name;

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Student {
    private String name;
    private Library library;

    public Student(String name, Library library) {
        this.name = name;
        this.library = library;
    }

    public void display() {
        System.out.println(name + " is associated with " + library.getName());
    }
}
public class UniDirection {


    public static void main(String[] args) {

            Library library = new Library("Central Library");
            Student student = new Student("Ahmed", library);
            student.display();
        }

    }
