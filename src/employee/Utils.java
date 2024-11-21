package employee;

import java.util.regex.*;


public class Utils {
    public static boolean validRegex(String regex, String str ) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
