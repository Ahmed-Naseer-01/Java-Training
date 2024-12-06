package employee;
import java.util.ArrayList;
import java.util.List;

public class Employee {

        private int id;
        private String name;
        private String contactNumber;
        private int salary;
        private String dateOfBirth;
        private String mail;
        private List<Address> addresses;
        private List<Contact> contacts;
        private List<Department> departments;

        public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
        public void setContacts(List<Contact> contacts) {
            this.contacts = contacts;
        }
        public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;

    }
        public void setId(int id) {
        this.id = id;
    }
        public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
        public void setMail(String mail) {
        this.mail = mail;
    }
        public String getMail() {
        return mail;
    }
        public void setName(String name) {
            this.name = name;
        }
        public void setSalary(int salary) {
            this.salary = salary;
        }
        public void setContactNumber(String contactNumber) {
            System.out.println(contactNumber);
            this.contactNumber = contactNumber;
        }

        public List<Department> getDepartments() {
        return departments;
    }
        public List<Contact> getContacts() {
        return contacts;
    }
        public List<Address> getAddresses() {
        return addresses;
    }
        public int getId() {
        return id;
    }
        public String getDateOfBirth() {
        return dateOfBirth;
    }
        public String getContactNumber() {
            return this.contactNumber;
        }
        public String getName() {
            return name;
        }
        public int getSalary() {
            return salary;
        }


}

