package employee;

import java.util.*;
import java.io.*;
import java.util.regex.*;
public class PhoneNumberValidator {

    private static Properties countryCodes = new Properties();

    // Load the country codes and regex patterns from the properties file
    public static void loadCountryCodes() {
        try (FileReader fis = new FileReader("src/employee/country_codes.properties")) {
            countryCodes.load(fis);
            System.out.println("codes loaded successfully");
        } catch (IOException e) {
            System.out.println("Error loading country codes: " + e.getMessage());
        }

    }
    public  static boolean validateCountryCode(String countryCode)
    {
//        loadCountryCodes();
        String regex = countryCodes.getProperty(countryCode);
//        System.out.println("Regex for country code " + countryCode + ": " + regex.substring());

        if (regex == null) {
            System.out.println("No phone number validation pattern found for country code: " + countryCode);
            return false;
        }
        return  true;
    }
    public static boolean validatePhoneNumber(String countryCode, String phoneNumber) {
        // Retrieve the regex pattern for the given country code
        Scanner sc = new Scanner(System.in);
        String regex = countryCodes.getProperty(countryCode);
//
//        if (regex == null) {
//            System.out.println("No phone number validation pattern found for country code: " + countryCode);
//            return false;
//        }
        // System.out.println("Enter your Phone number start with your country code " + countryCode);
        Pattern phonePattern = Pattern.compile(regex);
       //String cNum = sc.nextLine();
        Matcher matcher = phonePattern.matcher(phoneNumber);
        if (matcher.matches()) return true;
        else return false;
    }
    }

