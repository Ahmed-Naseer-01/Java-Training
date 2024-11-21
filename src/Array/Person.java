package Array;
import java.util.*;
public class Person {


    static class Adress {
       private String permanentAdress;
        String temporaryAdress;
        int postalCode;

    }

    static class Employee {
        private String name;
        private String department;
        private long contactNumber;
        private int salary;
        Adress emp1 = new Adress();
        public void setName(String name) {
            this.name = name;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public void setContactNumber(long contactNumber) {
            this.contactNumber = contactNumber;
        }

        public long getContactNumber() {
            return contactNumber;
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

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Employee emp1 = new Employee();
            boolean sal_flag = true;
            boolean num_flag = true;
            System.out.println("Enter your name : ");
            String name = sc.nextLine();
            if(name!= null) emp1.setName(name);
            while(sal_flag) {
                System.out.println("Enter your Salary : ");

                String sal = sc.nextLine();
                try {

                    int salary = Integer.parseInt(sal);
                    if (salary > 0)
                    {
                        emp1.setSalary(salary);
                        sal_flag = false;
                    }
                   else System.out.println("Invalid  Salary format");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter correct salary format( Integer) ");

                }

            }

            while(num_flag) {
                System.out.println("Enter your Number : ");

                String cNum = sc.nextLine();
                try {
                    int contactNumber = Integer.parseInt(cNum);
                    if (contactNumber > 0)
                    {
                        emp1.setContactNumber(contactNumber);
                        num_flag = false;
                    }
                    else System.out.println("Invalid  Contact Number format");
                } catch (NumberFormatException e) {
                    System.out.println("Please enter correct Contact Number format");

                }

            }





        }
    }
}
