package employee;

import java.util.*;
import java.io.*;
import java.util.regex.*;
public class PhoneNumberValidator {

    private static Properties countryCodes = new Properties();
    public static void loadCountryCodes() {
        try (FileReader fis = new FileReader("src/employee/regex.properties")) {
            countryCodes.load(fis);
//            System.out.println("codes loaded successfully");
        } catch (IOException e) {
            System.out.println("Error loading country codes: " + e.getMessage());
        }

    }
    public  static boolean validateCountryCode(String countryCode) {
        String regex = countryCodes.getProperty(countryCode);

        if (regex == null) {
            System.out.println("No phone number validation pattern found for country code: " + countryCode);
            return false;
        }
        return  true;
    }
    public static boolean validatePhoneNumber(String countryCode, String phoneNumber) {
        Scanner sc = new Scanner(System.in);
        String regex = countryCodes.getProperty(countryCode);
        Pattern phonePattern = Pattern.compile(regex);
        Matcher matcher = phonePattern.matcher(phoneNumber);
        if (matcher.matches()) return true;
        else return false;
    }
    }
