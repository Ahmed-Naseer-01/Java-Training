package employee;

public class Employee {

        private int id;
        private String name;
        private String department;
        private String contactNumber;
        private int salary;
        private String dateOfBirth;
        private String mail;
        Adress adress = new Adress();


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
        public void setDepartment(String department) {
            this.department = department;
        }
        public void setContactNumber(String contactNumber) {
            System.out.println(contactNumber);
            this.contactNumber = contactNumber;
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
        public String getDepartment() {
            return department;
        }
        public int getSalary() {
            return salary;
        }
        public String getTemporaryAddress(){ return this.adress.getTemporaryAddress();}
        public String getPermanentAddress(){return this.adress.getPermanentAddress();}
        public void setPermanentAddress(String address)
        {
            adress.setPermanentAddress(address);
        }
        public void setTemporaryAddress(String address) { adress.setTemporaryAddress(address); }

}

