package employee;

public class Employee {

        private String name;
        private String department;
        private String contactNumber;
        private int salary;
        private String mail;
        Adress adress;

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

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setContactNumber(String contactNumber) {
            System.out.println(contactNumber);
            this.contactNumber = contactNumber;
        }

        public String getContactNumber() {
            return this.contactNumber;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public long getSalary() {
            return salary;
        }
        public void setPermanentAddress(String address)
        {
            adress.setPermanentAddress(address);
        }
        public void setTemporaryAddress(String address)
    {
        adress.setTemporaryAddress(address);
    }

}

