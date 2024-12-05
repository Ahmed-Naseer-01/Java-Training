package employee;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {

    public static void intionalizeEmployee(Employee emp, Scanner sc) {
        emp.setName(validateName(sc));
        emp.setDepartment(validateDepartment(sc));
        emp.setContactNumber(validatePhone(sc));
        emp.setSalary(validateSalary(sc));
        emp.setMail(validateMail(sc));
        emp.setPermanentAddress(validateAddress(sc, Constant.PERMADDRESS));
        emp.setTemporaryAddress(validateAddress(sc, Constant.TEMPADDRESS));
        emp.setDateOfBirth(validateDateOfBirth(sc));
        System.out.println(emp.getDateOfBirth());
        EmployeeDAO.saveEmployee(emp);
    }

    public static String validateDateOfBirth(Scanner sc) {
        System.out.println("Enter Date of Birth (dd-MM-yyyy): ");
        String dateOfBirth = sc.nextLine();

        if (dateOfBirth == null || dateOfBirth.isEmpty()) {
            System.out.println("Invalid date of birth. Please try again.");
            return validateDateOfBirth(sc);
        }

        Properties datePattern = new Properties();
        try (FileReader fis = new FileReader("src/employee/regex.properties")) {
            datePattern.load(fis);
            System.out.println("Regex pattern loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading regex pattern: " + e.getMessage());
            return "Invalid date of birth format";
        }

        String regex = datePattern.getProperty("datePattern");
        if (regex == null || !Utils.validRegex(regex, dateOfBirth)) {
            System.out.println("Invalid date of birth format.");
            return validateDateOfBirth(sc);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_FORMAT); // dd-MM-yyyy
        try {
            LocalDate birthDate = LocalDate.parse(dateOfBirth, formatter);

            if (birthDate.isAfter(LocalDate.now())) {
                System.out.println("Date of birth cannot be in the future.");
                return validateDateOfBirth(sc);
            }

            // Convert to yyyy-MM-dd format to be compatible with MySQL DATE
            return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date value. Please enter a valid date (e.g., 31-12-1990).");
            return validateDateOfBirth(sc);
        }
    }


    public static String validateName(Scanner sc) {
        System.out.println("Enter your name: ");
        String name = sc.nextLine().trim();
        if (!Utils.validRegex("[A-Z a-z]+\\s?[A-Z a-z]*\\s?[A-Z a-z]*?", name)) {
            System.out.println("Incorrect Format.");
            return validateName(sc);
        }
        return name;
    }

    public static String validateDepartment(Scanner sc) {
        System.out.println("Enter your Department: ");
        String department = sc.nextLine().trim();
        if (!Utils.validRegex("[A-Z a-z]+\\s?[A-Z a-z]*\\s?[A-Z a-z]*?", department)) {
            System.out.println("Incorrect Format.");
            return validateDepartment(sc);
        }
        return department;
    }

    public static String validatePhone(Scanner sc) {
        System.out.println("Enter your country code (e.g., PK, US, etc.): ");
        String countryCode = sc.nextLine().trim();

        try {
            if (PhoneNumberValidator.validateCountryCode(countryCode)) {
                System.out.println("Enter your phone number, starting with your country code (e.g., +92, +1, etc.): ");
                String phoneNumber = sc.nextLine().trim();

                if (PhoneNumberValidator.validatePhoneNumber(countryCode, phoneNumber)) {
                    return phoneNumber;
                } else {
                    System.out.println("Invalid contact number format. Please try again.");
                    return validatePhone(sc);
                }
            } else {
                System.out.println("Invalid country code. Please try again.");
                return validatePhone(sc);
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
            return validatePhone(sc);
        }
    }

    public static int validateSalary(Scanner sc) {
        System.out.println("Enter your Salary: ");
        String sal = sc.nextLine();
        try {
            int salary = Integer.parseInt(sal);
            if (salary > 0) {
                return salary;
            } else {
                System.out.println("Invalid Salary format.");
                return validateSalary(sc);
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid salary (integer).");
            return validateSalary(sc);
        }
    }

    public static String validateMail(Scanner sc) {
        System.out.println("Enter mail: ");
        String mail = sc.nextLine().trim();

        Properties mailCode = new Properties();
        try (FileReader fis = new FileReader("src/employee/regex.properties")) {
            mailCode.load(fis);
            System.out.println("Codes loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading country codes: " + e.getMessage());
        }

        if (Utils.validRegex(mailCode.getProperty("mail"), mail)) {
            return mail;
        }
        System.out.println("Invalid Mail format.");
        return validateMail(sc);
    }

    public static String validateAddress(Scanner sc, String addressType) {
        System.out.println("Enter your " + addressType + " Address: ");
        String address = sc.nextLine();
        if (address.isEmpty()) {
            return validateAddress(sc, addressType);
        }
        return address;
    }

    public static void displayDetails(Employee emp) {
        System.out.println("Name: " + emp.getName());
        System.out.println("Mail: " + emp.getMail());
        System.out.println("Phone Number: " + emp.getContactNumber());
        System.out.println("Department: " + emp.getDepartment());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Permanent Address: " + emp.getPermanentAddress());
        System.out.println("Temporary Address: " + emp.getTemporaryAddress());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneNumberValidator.loadCountryCodes();

        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    Employee newEmp = new Employee();
                    intionalizeEmployee(newEmp, scanner);
                }
                case 2 -> {
                    System.out.println("Enter Employee ID to view: ");
                    int viewId = Integer.parseInt(scanner.nextLine().trim());
                    Employee emp = EmployeeDAO.getEmployeeById(viewId);
                    if (emp != null) {
                        displayDetails(emp);
                    } else {
                        System.out.println("Employee not found.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter Employee ID to update: ");
                    int updateId = Integer.parseInt(scanner.nextLine().trim());
                    Employee empToUpdate = EmployeeDAO.getEmployeeById(updateId);

                    if (empToUpdate != null) {
                        System.out.println("\n--- Which field(s) do you want to update? ---");
                        System.out.println("1. Name");
                        System.out.println("2. Department");
                        System.out.println("3. Contact Number");
                        System.out.println("4. Salary");
                        System.out.println("5. date of birth");
                        System.out.println("6. Mail");
                        System.out.println("7. Permanent Address");
                        System.out.println("8. Temporary Address");
//                        System.out.println("8. Update All Fields");

                        System.out.println("Enter your choice (e.g., 1 for Name, 2 for Department, etc.): ");
                        int fieldChoice = Integer.parseInt(scanner.nextLine().trim());

                        switch (fieldChoice) {
                            case 1 -> empToUpdate.setName(validateName(scanner));
                            case 2 -> empToUpdate.setDepartment(validateDepartment(scanner));
                            case 3 -> empToUpdate.setContactNumber(validatePhone(scanner));
                            case 4 -> empToUpdate.setSalary(validateSalary(scanner));
                            case 5 -> empToUpdate.setDateOfBirth(validateDateOfBirth(scanner));
                            case 6 -> empToUpdate.setMail(validateMail(scanner));
                            case 7 -> empToUpdate.setPermanentAddress(validateAddress(scanner, Constant.PERMADDRESS));
                            case 8 -> empToUpdate.setTemporaryAddress(validateAddress(scanner, Constant.TEMPADDRESS));
                            case 9 -> intionalizeEmployee(empToUpdate, scanner); // Reinitialize all fields
                            default -> {
                                System.out.println("Invalid choice. Please try again.");
                                return;
                            }
                        }

                        // Call DAO to update only the selected fields or all fields if case 8
                        if (EmployeeDAO.updateEmployee(fieldChoice, updateId, empToUpdate)) {
                            System.out.println("Employee updated successfully.");
                        } else {
                            System.out.println("Failed to update employee.");
                        }
                    } else {
                        System.out.println("Employee not found.");
                    }
                }

                case 4 -> {
                    System.out.println("Enter Employee ID to delete: ");
                    int deleteId = Integer.parseInt(scanner.nextLine().trim());
                    if (EmployeeDAO.deleteEmployee(deleteId)) {
                        System.out.println("Employee deleted successfully.");
                    } else {
                        System.out.println("Failed to delete employee.");
                    }
                }
                case 5 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
