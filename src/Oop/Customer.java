package Oop;

class Parent {
    public void display() {
        System.out.println("Static method in Parent");
    }
    public void display2() {
        System.out.println("display2 Parent method");
    }
}

class Child1 extends Parent {
    public void display() {
        System.out.println(" method in Child1");
    }
}

class Child2 extends Parent {

}

public class Customer {
    public static void main(String[] args) {
        Child1 obj= new Child1();

     obj.display(); // Outputs: Static method in Parent
     obj.display2(); // Outputs: Static method in Parent
//      Customer c = new Customer();
//      c.temp(obj);
    }
//    public void temp(Parent p)
//    {
//        p.display();
//    }
//    public void temp(Child c)
//    {
//        c.display();
//    }

}
