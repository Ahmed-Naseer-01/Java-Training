package employee;
import java.util.List;

public class Department {
    private String Name;
    private int id;
    private String Description;
    private List<Employee> employees;

    public void setEmployees (List<Employee> employees) {
        this.employees = employees;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public String getName() {
        return Name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }
}
