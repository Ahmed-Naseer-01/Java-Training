package employee;

public class Address {
    private int id;
    private String street;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private Constant.AddressType addressType;  // Store the address type



    public void setAddressType(Constant.AddressType addressType) {
        this.addressType = addressType;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Constant.AddressType getAddressType() {
        return addressType;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }
}
