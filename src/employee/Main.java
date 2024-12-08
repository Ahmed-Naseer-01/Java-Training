package employee;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void intionalizeEmployee(Employee emp, Scanner sc) {
        emp.setName(validateName(sc));
        emp.setDepartments(validateDepartment(sc));
        emp.setContacts(validateContacts(sc));
        emp.setSalary(validateSalary(sc));
        emp.setAddresses(validateAddress(sc));
        emp.setDateOfBirth(validateDateOfBirth(sc));
        EmployeeDAO.saveEmployee(emp);
    }

    public static List<Contact> validateContacts(Scanner sc) {
        List<Contact> contacts = new ArrayList<>();
        boolean addmoreContact = true;
        while (addmoreContact) {
            Contact newContact = new Contact();
            newContact.setPhoneNumber(validatePhone(sc));
            newContact.setEmail(validateMail(sc));
            contacts.add(newContact);
            System.out.println("Do you want to enter More contact (yes/no) : ");
            String choice = sc.nextLine().trim().toLowerCase();
            addmoreContact = choice.equals("yes");
        }
        return contacts;
    }
    public static List<Address> validateAddress(Scanner sc) {
        List<Address> addresses = new ArrayList<>();
        boolean addMoreAddress = true;
        boolean permanentAddressAdded = false;

        while (addMoreAddress) {
            Address newAddress = new Address();

            System.out.println("Select Address Type:");
            System.out.println("1. Temporary");
            System.out.println("2. Permanent");
            int addressTypeChoice = -1;

            while (addressTypeChoice < 1 || addressTypeChoice > 2) {
                System.out.print("Enter 1 or 2 to select address type: ");
                addressTypeChoice = sc.nextInt();
                sc.nextLine();  // Clear the newline character after nextInt()
            }

            // Check if it's a permanent address and enforce the rule
            if (addressTypeChoice == 1) {
                newAddress.setAddressType(Constant.AddressType.TEMPORARY);
            } else if (addressTypeChoice == 2) {
                // If a permanent address is already added, show error and skip adding a new one
                if (permanentAddressAdded) {
                    System.out.println("You can only have one permanent address. Skipping this address.");
                    continue;  // Skip adding this address and ask the user to add another one
                }
                newAddress.setAddressType(Constant.AddressType.PERMANENT);
                permanentAddressAdded = true;  // Mark that a permanent address has been added
            }
            // Collect address details from the user
            System.out.print("Enter Street: ");
            newAddress.setStreet(sc.nextLine());

            System.out.print("Enter Street Address: ");
            newAddress.setStreetAddress(sc.nextLine());

            System.out.print("Enter City: ");
            newAddress.setCity(sc.nextLine());

            System.out.print("Enter State: ");
            newAddress.setState(sc.nextLine());

            System.out.print("Enter Zip Code: ");
            newAddress.setZipCode(sc.nextLine());

            // Add the address to the list
            addresses.add(newAddress);

            // Ask if the user wants to add another address
            System.out.print("Do you want to add another address? (yes/no): ");
            String response = sc.nextLine().trim().toLowerCase();
            addMoreAddress = response.equals("yes");
        }
        return addresses;
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
    public static List<Department> validateDepartment(Scanner sc) {
        List<Department> departments = new ArrayList<>();
        boolean addMoreDepartment = true;

        while (addMoreDepartment) {
            Department newDepartment = new Department();

            // Validate Department Name
            while (true) {
                System.out.println("Enter Department Name: ");
                String department = sc.nextLine().trim();
                if (!Utils.validRegex("^[A-Za-z ]+$", department)) {
                    System.out.println("Incorrect format. Department name should only contain letters and spaces.");
                } else if (departments.stream().anyMatch(dep -> dep.getName().equalsIgnoreCase(department))) {
                    System.out.println("This department already exists. Please enter a different name.");
                } else {
                    newDepartment.setName(department);
                    break; // Valid input; exit loop
                }
            }

            // Validate Department Description
            while (true) {
                System.out.println("Enter Department Description: ");
                String departmentDescription = sc.nextLine().trim();
                if (departmentDescription.isEmpty()) {
                    System.out.println("Description cannot be empty. Please enter a valid description.");
                } else {
                    newDepartment.setDescription(departmentDescription);
                    break; // Valid input; exit loop
                }
            }

            // Add Department to List
            departments.add(newDepartment);

            // Check if user wants to add another department
            System.out.println("Do you want to add another department? (yes/no): ");
            while (true) {
                String response = sc.nextLine().trim().toLowerCase();
                if (response.equals("yes")) {
                    addMoreDepartment = true;
                    break;
                } else if (response.equals("no")) {
                    addMoreDepartment = false;
                    break;
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no': ");
                }
            }
        }

        return departments;
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
        System.out.println("Enter Mail: ");
        String mail = sc.nextLine().trim();

        Properties mailCode = new Properties();
        try (FileReader fis = new FileReader("src/employee/regex.properties")) {
            mailCode.load(fis);
        } catch (IOException e) {
            System.out.println("Error loading country codes: " + e.getMessage());
        }

        if (Utils.validRegex(mailCode.getProperty("mail"), mail)) {
            return mail;
        }
        System.out.println("Invalid Mail format.");
        return validateMail(sc);
    }
    public static void displayDetails(Employee emp) {
        System.out.println("Name: " + emp.getName());
        System.out.println("Salary: " + emp.getSalary());
        System.out.println("Date of Birth: " + emp.getDateOfBirth().toString());
        System.out.println("--------------------Contact--------------------");
        for (Contact contact : emp.getContacts()) {
            System.out.println("email: " + contact.getEmail());
            System.out.println("Phone Number: " + contact.getPhoneNumber());
            System.out.println("---------------------------------");
        }
        System.out.println("--------------------Address--------------------");
        for (Address address : emp.getAddresses()) {
            System.out.println("Address Type: " + address.getAddressType());
            System.out.println("Street : " + address.getStreet());
            System.out.println("City : " + address.getCity());
            System.out.println("State : " + address.getState());
            System.out.println("zipCode : " + address.getZipCode());
            System.out.println("Street Address : " + address.getStreetAddress());
        }
        System.out.println("--------------------Department--------------------");
        for (Department department : emp.getDepartments()) {
            System.out.println("name: " + department.getName());
            System.out.println("description: " + department.getDescription());
        }
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
            logger.info("This is an info message.");

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
//                    displayDetails(newEmp);
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
                        System.out.println("\n--- Select the field(s) to update ---");
                        System.out.println("1. Name");
                        System.out.println("2. Salary");
                        System.out.println("3. Date of Birth");
                        System.out.println("4. Contact Information");
                        System.out.println("5. Address Information");
                        System.out.println("6. Department Information");
                        System.out.println("7. Update All");

                        System.out.println("Enter your choice (comma-separated for multiple, e.g., 1,2,3): ");
                        String[] options = scanner.nextLine().trim().split(",");

                        for (String option : options) {
                            switch (Integer.parseInt(option.trim())) {
                                case 1 -> empToUpdate.setName(validateName(scanner));
                                case 2 -> empToUpdate.setSalary(validateSalary(scanner));
                                case 3 -> empToUpdate.setDateOfBirth(validateDateOfBirth(scanner));
                                case 4 -> empToUpdate.setContacts(validateContacts(scanner));
                                case 5 -> empToUpdate.setAddresses(validateAddress(scanner));
                                case 6 -> empToUpdate.setDepartments(validateDepartment(scanner));
                                case 7 -> intionalizeEmployee(empToUpdate, scanner); // Update all fields
                                default -> System.out.println("Invalid choice: " + choice);
                            }
                        }

                        // Call DAO to update selected fields
                        if (EmployeeDAO.updateEmployee(updateId, empToUpdate)) {
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
