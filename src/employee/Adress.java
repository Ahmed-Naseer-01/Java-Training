package employee;

public class Adress {
    private int id;
    String permanentAddress;
 String temporaryAddress;

public int getId() {
        return id;
    }
public void setId(int id) {
        this.id = id;
    }
public String getPermanentAddress(){
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
