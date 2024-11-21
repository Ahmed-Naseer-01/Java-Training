package regex;
import java.util.Scanner;
import java.util.regex.*;

public class RegularExp {

    public static void regExpMatcher(String re, String str) {
        // Trim the input string to remove leading/trailing spaces
        str = str.trim();
        System.out.println("Input string after trimming: '" + str + "'");
        System.out.println("exp"+re);
        System.out.println("str"+str);
        try {
            // Compile and match the regular expression
            Pattern p = Pattern.compile(re);
            Matcher m = p.matcher(str);
            boolean matches = m.matches();
            System.out.println(matches);  // Output the result (true or false)
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid regular expression syntax: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your exp ");
        String exp = sc.nextLine();  // Read regular expression from user

        // Infinite loop for testing matching strings
        while (true) {
            System.out.println("Enter your string (or type 'exit' to quit): ");
            String str = sc.nextLine();  // Read the input string from user

            // Exit condition for the loop
            if (str.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the program...");
                break;
            }

//            regExpMatcher("^\\+[1-9]{2}[0-9]{10}$","+923297224415");  // Check if the input string matches the regex
       regExpMatcher(exp, str);
        }

        sc.close(); // Close the scanner to avoid resource leak
    }
}
