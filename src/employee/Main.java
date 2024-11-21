package employee;

import java.util.Properties;
import Array.Person;
import java.util.regex.*;
import java.util.Scanner;


public class Main {
public static void intionalizeEmployee(Employee emp, Scanner sc) {
    emp.setName(validateName(sc));
    emp.setDepartment(validateDepartment(sc));
    emp.setContactNumber(validatePhone(sc));
    emp.setSalary(validateSalary(sc));
    emp.setMail(validateMail(sc));
    emp.setPermanentAddress(validateAddress(sc, "Permanent"));
    emp.setTemporaryAddress(validateAddress(sc, "Temporary"));
}
public static String validateName(Scanner sc) {
    System.out.println("Enter your name : ");
    String name = sc.nextLine().trim();
    if(!(Utils.validRegex("[A-Z a-z]+\\s?[A-Z a-z]*\\s?[A-Z a-z]*?", name))){
        System.out.println("Incorrect Format : ");
        validateName(sc);
    }
    return name;
}
public static String validateDepartment(Scanner sc) {
        System.out.println("Enter your Department : ");
        String department = sc.nextLine().trim();
        if(!(Utils.validRegex("[A-Z a-z]+\\s?[A-Z a-z]*\\s?[A-Z a-z]*?", department))){
            System.out.println("Incorrect Format : ");
            validateName(sc);
    }
    return department;
}
public static String validatePhone(Scanner sc) {
        System.out.println("Enter your country code (e.g., PK, US, etc.): ");
        String countryCode = sc.nextLine().trim();

        try {
            // Check if the country code is valid
            if (PhoneNumberValidator.validateCountryCode(countryCode)) {
                System.out.println("Enter your phone number, starting with your country code (e.g., +92, +1, etc.): ");
                String phoneNumber = sc.nextLine().trim();

                // Validate phone number format
                if (PhoneNumberValidator.validatePhoneNumber(countryCode, phoneNumber)) {
                    return phoneNumber; // Return the valid phone number
                } else {
                    System.out.println("Invalid contact number format. Please try again.");
                    return validatePhone(sc); // Recursively call and return the result
                }
            }
            else {
                System.out.println("Invalid country code. Please try again.");
                return validatePhone(sc); // Recursively call if country code is invalid
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            return validatePhone(sc); // Recursively call on exception
        }
    }
public static int validateSalary(Scanner sc) {
    System.out.println("Enter your Salary : ");
    String sal = sc.nextLine();
    try {

        int salary = Integer.parseInt(sal);
        if (salary > 0) {
            return salary;
        } else {
            System.out.println("Invalid  Salary format");
            return validateSalary(sc);
        }
    } catch (NumberFormatException e) {
        System.out.println("Please enter correct salary format( Integer) ");
        return validateSalary(sc);
    }
}
public static String validateMail(Scanner sc) {
    System.out.println("Enter mail : ");
    String mail = sc.nextLine().trim();
    if (Utils.validRegex("[a-z 0-9]+@[a-z]+.com", mail)) return mail;
    System.out.println("Invalid  Mail format");
    return validateMail(sc);
}
public static String validateAddress(Scanner sc, String addressType){
    System.out.println("Enter your " + addressType + "Address ");
    String address =  sc.nextLine();
    if(address.isEmpty()) return validateAddress(sc, addressType);
    return address;
}
public static void displayDetails(Employee emp) {
    System.out.println("Name : " + emp.getName());
    System.out.println("Mail : " + emp.getMail());
    System.out.println("Phone Number : " + emp.getContactNumber());
    System.out.println("Department : " + emp.getDepartment());
    System.out.println("Salary : " + emp.getSalary());
    System.out.println("Permanent Address : " + emp.getPermanentAddress());
    System.out.println("Temporary Address : " + emp.getTemporaryAddress());

}
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Employee emp1 = new Employee();
    PhoneNumberValidator.loadCountryCodes();
    intionalizeEmployee(emp1, scanner);
    displayDetails(emp1);
        }
    }
