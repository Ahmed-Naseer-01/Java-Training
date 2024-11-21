package employee;

import java.util.Properties;
import Array.Person;
import java.util.regex.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Employee emp1 = new Employee();
        PhoneNumberValidator.loadCountryCodes();
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter your name : ");
//        String name = sc.nextLine();
//        if (name != null) emp1.setName(name);
//        while (true) {
//            System.out.println("Enter your Salary : ");
//
//            String sal = sc.nextLine();
//            try {
//
//                int salary = Integer.parseInt(sal);
//                if (salary > 0) {
//                    emp1.setSalary(salary);
//                    break;
//                } else System.out.println("Invalid  Salary format");
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter correct salary format( Integer) ");
//
//            }
//
//        }
//
        while (true) {
            System.out.println("Enter your country code ");
            String countryCode = sc.nextLine();
           try {
               if (PhoneNumberValidator.validateCountryCode(countryCode)) {
                   System.out.println("Enter your Phone number start with your country code like (+92, +1 etc..)" );
                   String phoneNumber = sc.nextLine();
                   if (PhoneNumberValidator.validatePhoneNumber(countryCode, phoneNumber)) {
                       emp1.setContactNumber(phoneNumber);
                       System.out.println("Phone number set successfully !!");
                       break;
                   } else System.out.println("Invalid  Contact Number format");
               }
           }
           catch (NumberFormatException e) {
               System.out.println("Please enter correct Contact Number format");
           }
//                int contactNumber = Integer.parseInt(cNum);

        }
        String myNumm = emp1.getContactNumber();
        System.out.println(myNumm);
//
//        // mail
//
//        while (true) {
//            Pattern mailPattern = Pattern.compile("[a-z 0-9]+@[a-z]+.com");
//            String mail = sc.nextLine();
//            Matcher matcher = mailPattern.matcher(mail);
//            boolean matches = matcher.matches();
//            try {
////                int contactNumber = Integer.parseInt(cNum);
//                if (matches) {
//                    emp1.setContactNumber(mail);
//                    break;
//                } else System.out.println("Invalid  Contact Number format");
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter correct Contact Number format");
//            }
//        }
        // load country codes
//        PhoneNumberValidator.loadCountryCodes();

        }
    }
