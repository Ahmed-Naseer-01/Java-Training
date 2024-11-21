package Array;
import  java.util.*;

public class ArrayPractice {
//public static void tablePrinter(int num) {
//    System.out.println("Table of "+ num);
//    for (int i = 1; i <= 10; i++) {
//        System.out.println(num + " * " + i +" = "+ i*num);
//    }
//}
static class A
    {
        public void display()
        {
            System.out.println("hello from base class");
        }
    }
    static class B extends A
    {
        int a, b;

        public void output()
        {
            System.out.println("hello from non-parame B");
        }
//        public void output(int a, int b)
//        {
//            this.a = a;
//            this.b = b;
//            System.out.println("hello from two parameterized func ");
//            System.out.println("here is output : "+ a*b);
//        }

        public void output(int a, Integer b)
        {
            this.a = a;
            this.b = b;
            System.out.println("hello from two parameterized func with Integer");
            System.out.println("here is output : "+ a*b);

        }
        public void output(int a, int b,Integer  c)
        {
            this.a = a;
            this.b = b;
            System.out.println("hello from object parameterized func ");
            System.out.println("here is output : "+ c);

        }
        public void output(int a)
        {
            this.a = a;
            System.out.println("hello from single parameterized func ");
            System.out.println("here is output : "+ a);
        }

        @Override
        public void display()
        {
            System.out.println("hello from base class");
        }
    }





    public static void main(String[] agrs)
    {

        B obj = new B();
Integer num = new Integer(7);
//        obj.display();
//        obj.output();
//        obj.output(5);
        obj.output(7,null);
//        obj.output(7,9, 10);


//        System.out.println("Enter the number : ");
//        Scanner sc = new Scanner(System.in);
//       int  num = sc.nextInt();
//
//        tablePrinter(num);
//

        String str = "welcome";
        String str2 = "";
        for(int i = (str.length()-1); i>=0 ; i--)
        {
            str2 += str.charAt(i);
        }
//        System.out.println(str2);


//       for(int i = 1 ; i<=100; i++)
//       {
//           System.out.print(i +"\t");
//       }
//      int j =1;
//        System.out.println();
//while(j<=100)
//{
//    System.out.print(j+"\t");
//    j++;
//}
//        System.out.println();
//int x = 1;
//do{
//    System.out.print(x+"\t");
//        x++;
//}while(x<=100);
    }
}
