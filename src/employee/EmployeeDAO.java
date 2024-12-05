package employee;

import java.sql.*;

public class EmployeeDAO {


    public static void saveEmployee(Employee emp) {
//        PreparedStatement employeeStmt = null;
//        PreparedStatement addressStmt = null;

        // Use try-with-resources for auto-closing connection
        try (Connection connection = DatabaseConnection.getConnection()) {

            // Insert employee details
            String employeeSQL = "INSERT INTO employees (name, department, contact_number, salary, date_of_birth, mail) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement employeeStmt = connection.prepareStatement(employeeSQL, Statement.RETURN_GENERATED_KEYS);
            employeeStmt.setString(1, emp.getName());
            employeeStmt.setString(2, emp.getDepartment());
            employeeStmt.setString(3, emp.getContactNumber());
            employeeStmt.setInt(4, emp.getSalary());
            employeeStmt.setString(5, emp.getDateOfBirth());
            employeeStmt.setString(6, emp.getMail());

            int rowsAffected = employeeStmt.executeUpdate();

            if (rowsAffected > 0) {
                // Get the generated employee ID
                ResultSet generatedKeys = employeeStmt.getGeneratedKeys();
                int employeeId = 0;
                if (generatedKeys.next()) {
                    employeeId = generatedKeys.getInt(1);
                }

                // Insert employee address details
                String addressSQL = "INSERT INTO addresses (permanent_address, temporary_address, employee_id) VALUES (?, ?, ?)";
                PreparedStatement addressStmt = connection.prepareStatement(addressSQL);
                addressStmt.setString(1, emp.getPermanentAddress());
                addressStmt.setString(2, emp.getTemporaryAddress());
                addressStmt.setInt(3, employeeId);

                // Execute the address insert query
                addressStmt.executeUpdate();
            } else {
                System.out.println("Employee insertion failed, no rows affected.");
            }

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.err.println("Error saving employee: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Employee getEmployeeById(int id) {
        String sql = """
        SELECT e.id, e.name, e.department, e.contact_number, e.salary, e.mail, e.date_of_birth, 
               a.permanent_address, a.temporary_address
        FROM employees e
        JOIN addresses a ON e.id = a.employee_id
        WHERE e.id = ?
    """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Employee emp = new Employee();
                    emp.setId(resultSet.getInt("id"));
                    emp.setName(resultSet.getString("name"));
                    emp.setDepartment(resultSet.getString("department"));
                    emp.setContactNumber(resultSet.getString("contact_number"));
                    emp.setSalary(resultSet.getInt("salary"));
                    emp.setMail(resultSet.getString("mail"));
                    emp.setPermanentAddress(resultSet.getString("permanent_address"));
                    emp.setTemporaryAddress(resultSet.getString("temporary_address"));
                    return emp;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching employee by ID: " + e.getMessage());
        }
        return null;
    }

    public static boolean updateEmployee(int fieldChoice, int employeeId, Employee emp) {
        String updateQuery = null;
        String updateValue = null;

        // Dynamically determine which field to update based on fieldChoice
        switch (fieldChoice) {
            case 1 -> {
                updateQuery = "UPDATE employees SET name = ? WHERE id = ?";
                updateValue = emp.getName();
            }
            case 2 -> {
                updateQuery = "UPDATE employees SET department = ? WHERE id = ?";
                updateValue = emp.getDepartment();
            }
            case 3 -> {
                updateQuery = "UPDATE employees SET contact_number = ? WHERE id = ?";
                updateValue = emp.getContactNumber();
            }
            case 4 -> {
                updateQuery = "UPDATE employees SET salary = ? WHERE id = ?";
                updateValue = String.valueOf(emp.getSalary());
            }
            case 5 -> {
                updateQuery = "UPDATE employees SET date_of_birth = ? WHERE id = ?";
                updateValue = emp.getDateOfBirth();
            }
            case 6 -> {
                updateQuery = "UPDATE employees SET mail = ? WHERE id = ?";
                updateValue = emp.getMail();
            }
            case 7 -> {
                updateQuery = "UPDATE addresses SET permanent_address = ? WHERE employee_id = ?";
                updateValue = emp.getPermanentAddress();
            }
            case 8 -> {
                updateQuery = "UPDATE addresses SET temporary_address = ? WHERE employee_id = ?";
                updateValue = emp.getTemporaryAddress();
            }
            default -> {
                System.err.println("Invalid field choice.");
                return false;
            }
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, updateValue); // Set the field value
            stmt.setInt(2, employeeId);    // Set the employee ID
            stmt.executeUpdate();

            System.out.println("Employee updated successfully!");
            return true;
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteEmployee(int employeeId) {
        String query = "DELETE FROM employees WHERE id = ?";
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
