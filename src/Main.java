//import  java.util.*;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//
//public class Main {
//    public  static void find_triangle_area()
//    {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter base ");
//        float base = sc.nextFloat();
//        System.out.println("Enter height");
//        float height = sc.nextFloat();
//        float triangle_area = (base * height)/2;
//        System.out.println(triangle_area);
//    }
//    public static double find_area()
//    {
//        Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        int b = sc.nextInt();
//        int c = sc.nextInt();
//        float s = (a + b + c) / 2f;
//        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
//        return area;
//
//    }
//
//
//    public static void main(String[] args) {
//
////       Roots r = new Roots();
////       double []roots = r.find_roots();
////       if(roots != null && roots.length == 2)
////       {
////           System.out.println("here is the value of r1 " + roots[0]);
////           System.out.println("here is the value of r2 " + roots[1]);
////       }
////Cube c = new Cube(34, 67, 89);
////double cube_area  = c.area();
////        System.out.println("here is the area of cube " + cube_area);
//
////double area = find_area();
////        System.out.println("here is area :"+area);
//
//
// // bit manupulation
//        int x = 0b0110;
//        int y = 0b0101;
//        int a = 20;
//        int  q = -4;
////        System.out.println(x&y);
//        // right shift
////        System.out.println(a<<1);
////        System.out.println(a>>1);
////        System.out.println(q<<1);
////        System.out.println(q>>>1);
////        System.out.println(a>>1); // left shift
////        System.out.println(String.format("%32s", Integer.toBinaryString(a)));
//
//
//// bit merging and masking
////        write a program in which you need to swap two numbers
//
////int a = 10;
////int b = 20;
////a = a^b;
////b = a^b;
////a = a^b;
////        System.out.println(b);
////        System.out.println(a);
////-------------------------------------
//// Topic String
//
//String myStr  = "  Lahore ";
//        String myStr1 = "Hello";
//        String myStr2 = "hello";
//        System.out.println(myStr.charAt(0));
//         System.out.println(myStr1.equalsIgnoreCase(myStr2));
//        System.out.println(myStr1.compareTo(myStr2));
//        System.out.println(myStr.trim()); // remove blank spaces
//        System.out.println(myStr.indexOf("q")); // if it does not found char  in the string it will return -ve value
//        String str = new String("hello");
//        System.out.println(myStr1.equals(myStr));
//        System.out.println(myStr.matches("[^ab]"));
//    }
//}
//class Animal {
//    String name;
//
//    // Parent class constructor
//    Animal(String name) {
//        this.name = name;
//        System.out.println("Animal class constructor called: " + name);
//    }
//}
//
//class Dog extends Animal {
//    String breed;
//
//    // Child class constructor
//    Dog(String name, String breed) {
//        super(name);  // Call parent constructor
//        this.breed = breed;
//        System.out.println("Dog class constructor called: " + breed);
//    }
//
//    // Constructor chaining
//    Dog(String name) {
//        this(name, "Unknown Breed");  // Chain to the other constructor
//        System.out.println("Dog class constructor (with default breed) called.");
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        Dog dog1 = new Dog("Bulldog", "French");
//        System.out.println();
//        Dog dog2 = new Dog("Labrador");
//    }
//}
import testing.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Person {
    String name;
    int age;

    // Constructor with no arguments
    Person() {
        this.name = "Unknown";
        this.age = 0;
        System.out.println("Default constructor called");
    }

    // Constructor with one argument
    Person(String name) {
        this.name = name;
        this.age = 30;  // Default age
        System.out.println("Constructor with one parameter called");
    }

    // Constructor with two arguments
    Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Constructor with two parameters called");
    }

    // Display details
    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
class Dog extends Animal{
    public void display()
    {
        System.out.println(flag);
    }
}
public class Main {
    public static void main(String[] args) {

        String str = "qwerr";
        String str2 = new String("zxv");

        // string is immutable value cannot change
        //but
        str = "ahmed" ;
        System.out.println(str);

        // when are assign another value to string it cannot create new object
        // old value remain in the memory and will remove by garbage collector




        int a =1;
        Integer b = 1;

        if(b!=null){
            System.out.println("gooog");
        }


//        Person p1 = new Person();
//        p1.display();
//
//        Person p2 = new Person("Alice");
//        p2.display();
//
//        Person p3 = new Person("Bob", 25);
//        p3.display();
        Animal animal = new Animal();
//        int head = animal.check;
//        System.out.println(head);
        Dog dog = new Dog();
        dog.display();
    }

    public int findLengthOfShortestSubarray(int[] arr) {

        int count=0;
        List<Integer> myArray = new ArrayList();
        for(int i=0;i<arr.length; i++)
        {
            if(i<arr.length-1 &&  arr[i+1]<arr[i])
            {
                count++;
            }else
            {
                myArray.add(arr[i]);
            }
        }


        return count;
    }

}
