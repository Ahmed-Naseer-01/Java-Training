package employee;

public class Adress {
 String permanentAddress;
 String temporaryAddress;

 public String getPermanentAddress()
 {
     return permanentAddress;
 }
 public String getTemporaryAddress(){ return temporaryAddress; }
 public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }
 public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }
}
