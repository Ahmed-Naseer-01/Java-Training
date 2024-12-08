package employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAO {


    public static void saveEmployee(Employee emp) {
//        PreparedStatement employeeStmt = null;
//        PreparedStatement addressStmt = null;

        // Use try-with-resources for auto-closing connection
        try (Connection connection = DatabaseConnection.getConnection()) {

            // Insert employee details
            String employeeSQL = "INSERT INTO Employee (name, salary, dateOfBirth) VALUES (?, ?, ?)";
            PreparedStatement employeeStmt = connection.prepareStatement(employeeSQL, Statement.RETURN_GENERATED_KEYS);
            employeeStmt.setString(1, emp.getName());
            employeeStmt.setInt(2, emp.getSalary());
            employeeStmt.setString(3, emp.getDateOfBirth());

            int rowsAffected = employeeStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated employee ID
                ResultSet generatedKeys = employeeStmt.getGeneratedKeys();
                int employeeId = 0;
                if (generatedKeys.next()) {
                    employeeId = generatedKeys.getInt(1);
                }

                // Insert employee address details
                String addressSQL = "INSERT INTO Address (street, streetAddress, city, state, zipCode, addressType, employee_id) VALUES (?, ?, ?,?, ?, ?,?)";
                for(Address address : emp.getAddresses()) {
                    PreparedStatement addressStmt = connection.prepareStatement(addressSQL);
                    addressStmt.setString(1, address.getStreet());
                    addressStmt.setString(2, address.getStreetAddress());
                    addressStmt.setString(3, address.getCity());
                    addressStmt.setString(4, address.getState());
                    addressStmt.setString(5, address.getZipCode());
                    addressStmt.setString(6, address.getAddressType().toString());
                    addressStmt.setInt(7, employeeId);
                    addressStmt.executeUpdate();
                }
                // Insert employee contact details
                String contactSQL = "INSERT INTO Employee_schema.Contact (phoneNumber, email, employee_id) VALUES (?, ?, ?)";
                for (Contact contact : emp.getContacts()) {
                    PreparedStatement contactStmt = connection.prepareStatement(contactSQL);
                    contactStmt.setString(1, contact.getPhoneNumber());
                    contactStmt.setString(2, contact.getEmail());
                    contactStmt.setInt(3, employeeId);

                    contactStmt.executeUpdate();
                }
                // Insert into Department table and retrieve generated ID
                String departmentSQL = "INSERT INTO Department (name, description) VALUES (?, ?)";
                String empDptSQL = "INSERT INTO Employee_schema.Employee_Department (employee_id, department_id) VALUES (?, ?)";

                for (Department department : emp.getDepartments()) {
                    // Insert department
                    PreparedStatement departmentStmt = connection.prepareStatement(departmentSQL, Statement.RETURN_GENERATED_KEYS);
                    departmentStmt.setString(1, department.getName());  // Assuming 'department.getName()' is correct
                    departmentStmt.setString(2, department.getDescription());  // Assuming 'department.getDescription()' is correct
                    departmentStmt.executeUpdate();

                    // Get generated department ID
                    ResultSet deptKeys = departmentStmt.getGeneratedKeys();
                    int departmentId = 0;
                    if (deptKeys.next()) {
                        departmentId = deptKeys.getInt(1);
                    }

                    // Insert into Employee_Department table using the generated department ID
                    PreparedStatement empDeptStmt = connection.prepareStatement(empDptSQL);
                    empDeptStmt.setInt(1, employeeId);  // Use the employee ID
                    empDeptStmt.setInt(2, departmentId);  // Use the retrieved department ID
                    empDeptStmt.executeUpdate();
                }

            } else {
                System.out.println("Employee insertion failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.err.println("Error saving employee: " + e.getMessage());
            e.printStackTrace();
        }
    }
//
public static Employee getEmployeeById(int id) {
    String sql = """
        SELECT 
            e.name,
            e.salary,
            e.dateOfBirth,
            c.email,
            c.phoneNumber,
            a.id AS addressId,
            a.street,
            a.streetAddress,
            a.city,
            a.state,
            a.zipCode,
            a.addressType,
            d.Name AS departmentName,
            d.Description AS departmentDescription
        FROM 
            Employee e
        LEFT JOIN 
            Address a 
            ON e.id = a.employee_id
        LEFT JOIN 
            Contact c 
            ON e.id = c.employee_id
        LEFT JOIN 
            Employee_Department ed 
            ON e.id = ed.employee_id
        LEFT JOIN 
            Department d 
            ON ed.department_id = d.id
        WHERE 
            e.id = ?
    """;

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            Employee emp = null;

            // Lists to hold related data
            List<Address> addresses = new ArrayList<>();
            List<Contact> contacts = new ArrayList<>();
            List<Department> departments = new ArrayList<>();

            while (resultSet.next()) {
                // Initialize Employee object once
                if (emp == null) {
                    emp = new Employee();
                    emp.setId(id);
                    emp.setName(resultSet.getString("name"));
                    emp.setSalary(resultSet.getInt("salary"));
                    emp.setDateOfBirth(resultSet.getDate("dateOfBirth").toString());
                }

                // Add Address
                if (resultSet.getString("street") != null) {
                    Address address = new Address();
                    address.setId(resultSet.getInt("addressId"));
                    address.setStreet(resultSet.getString("street"));
                    address.setStreetAddress(resultSet.getString("streetAddress"));
                    address.setCity(resultSet.getString("city"));
                    address.setState(resultSet.getString("state"));
                    address.setZipCode(resultSet.getString("zipCode"));

                    // Convert and set AddressType
                    String addressTypeStr = resultSet.getString("addressType");
                    Constant.AddressType addressType = Constant.AddressType.valueOf(addressTypeStr.toUpperCase());
                    address.setAddressType(addressType);

                    addresses.add(address);
                }

                // Add Contact
                if (resultSet.getString("email") != null) {
                    Contact contact = new Contact();
                    contact.setEmail(resultSet.getString("email"));
                    contact.setPhoneNumber(resultSet.getString("phoneNumber"));
                    contacts.add(contact);
                }

                // Add Department
                if (resultSet.getString("departmentName") != null) {
                    Department department = new Department();
                    department.setName(resultSet.getString("departmentName"));
                    department.setDescription(resultSet.getString("departmentDescription"));
                    departments.add(department);
                }
            }

            // Set the lists in Employee object
            if (emp != null) {
                emp.setAddresses(addresses);
                emp.setContacts(contacts);
                emp.setDepartments(departments);
            }

            return emp;
        }
    } catch (SQLException e) {
        System.err.println("Error fetching employee by ID: " + e.getMessage());
    }
    return null;
}

//
public static boolean updateEmployee(int employeeId, Employee emp) {
    List<String> updateQueries = new ArrayList<>();
    List<Object> params = new ArrayList<>();

    // Prepare the update queries for the Employee table
    if (emp.getName() != null) {
        updateQueries.add("name = ?");
        params.add(emp.getName());
    }
    if (emp.getSalary() != 0) {
        updateQueries.add("salary = ?");
        params.add(emp.getSalary());
    }
    if (emp.getDateOfBirth() != null) {
        updateQueries.add("dateOfBirth = ?");
        params.add(emp.getDateOfBirth());
    }

    // Check if contacts exist and need to be updated
    if (emp.getContacts() != null && !emp.getContacts().isEmpty()) {
        for (Contact contact : emp.getContacts()) {
            updateContact(employeeId, contact); // Assuming a helper function
        }
    }

    // Check if addresses exist and need to be updated
    if (emp.getAddresses() != null && !emp.getAddresses().isEmpty()) {
        for (Address address : emp.getAddresses()) {
            updateAddress(employeeId, address); // Assuming a helper function
        }
    }

    // Check if departments need to be updated (using your previous method)
    if (emp.getDepartments() != null && !emp.getDepartments().isEmpty()) {
        updateDepartments(employeeId, emp.getDepartments()); // Assuming this method exists
    }

    // Construct the main employee update query
    if (!updateQueries.isEmpty()) {
        String query = "UPDATE Employee SET " + String.join(", ", updateQueries) + " WHERE id = ?";
        params.add(employeeId); // Add the employeeId for the WHERE clause

        // Perform the update within a transaction
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);  // Start the transaction
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                // Set the parameters for the employee update
                for (int i = 0; i < params.size(); i++) {
                    stmt.setObject(i + 1, params.get(i));
                }

                // Execute the employee update
                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    conn.commit();  // Commit the transaction if everything was successful
                    return true;
                } else {
                    conn.rollback();  // Rollback if no rows were updated
                    return false;
                }
            } catch (SQLException e) {
                conn.rollback();  // Rollback on error
                System.err.println("Error updating employee: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            return false;
        }
    }

    return true; // No updates to perform, so return true
}


    // Example Helper Function for Contacts
    private static void updateContact(int employeeId, Contact contact) {
        String query = "UPDATE Contact SET phoneNumber = ?, email = ? WHERE employee_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, contact.getPhoneNumber());
            stmt.setString(2, contact.getEmail());
            stmt.setInt(3, employeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating contact: " + e.getMessage());
        }
    }

    // Example Helper Function for Addresses
    private static void updateAddress(int employeeId, Address address) {
        String query = "UPDATE Address SET street = ?, city = ?, state = ?, zipCode = ?, addressType = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getZipCode());
            stmt.setString(5, address.getAddressType().toString());
            stmt.setInt(6, address.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating address: " + e.getMessage());
        }
    }
    private static void updateDepartments(int employeeId, List<Department> departments) {
        String deleteQuery = "DELETE FROM employee_departments WHERE employee_id = ?";
        String insertQuery = "INSERT INTO employee_departments (employee_id, department_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Remove existing departments for the employee
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                deleteStmt.setInt(1, employeeId);
                deleteStmt.executeUpdate();
            }

            // Add new departments
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                for (Department department : departments) {
                    insertStmt.setInt(1, employeeId);
                    insertStmt.setInt(2, department.getId()); // Ensure you insert the correct department_id
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            }
        } catch (SQLException e) {
            System.err.println("Error updating departments for employee " + employeeId + ": " + e.getMessage());
            // Optional: Rethrow or handle the exception based on your requirements
            throw new RuntimeException("Error updating departments for employee " + employeeId, e);
        }
    }



    public static boolean deleteEmployee(int employeeId) {
        String query = "DELETE FROM Employee WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, employeeId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println("rowsAffected "+ rowsAffected);
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
                return true;
            } else {
                System.out.println("Employee not found.");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
        return false;
    }

}
