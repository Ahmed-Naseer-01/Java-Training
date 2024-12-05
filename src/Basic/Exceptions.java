package Basic;
import java.util.*;


public class Exceptions {


public static int sumFinder() {
    Scanner sc = new Scanner(System.in);
    int sum = 0;
    int counter = 1;
    while (counter <= 5) {
        System.out.println("Enter num # " + counter+"\n");
        String number = sc.nextLine();
        try {

            int num = Integer.parseInt(number);
            if (num >= 0) {
                sum += num;
                counter++;
            } else System.out.println("Invalid number Integer");
        }
        catch (InputMismatchException badNumber)
        {
            System.out.println("Please enter valid Integer without character \n");
        }
    }
    return sum;
}
    public static void main(String[] args) {
int sum = sumFinder();
        System.out.println("here is :  " + sum );
    }
}
