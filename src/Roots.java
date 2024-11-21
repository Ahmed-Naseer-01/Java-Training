import  java.util.*;
public class Roots {
    public static double[] find_roots()
    {
        System.out.println("Enter values of a, b, & c ");
        Scanner sc = new Scanner(System.in);
        int a, b, c;
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        double decreminator = b*b-(4*a*c);
        if (decreminator < 0)
        {
            System.out.println("The equation has no real roots.");
            return null;
        }
        double r1 = (-b + Math.sqrt(decreminator))/(2*a);
        double r2 = (-b - Math.sqrt(decreminator))/(2*a);
        return new double []{r1,r2};
    }
}
