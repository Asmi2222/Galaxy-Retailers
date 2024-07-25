package model;

public class GalaxyRetailersModel {
	private String userName;
    private String firstName;
    private String lastName; 
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
    private String userRole;

    public GalaxyRetailersModel(String userName, String firstName, String lastName, String address, String email, String phoneNumber, String password, String userRole)
 {
    	this.userName = userName;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userRole = userRole;
    }

    // Getter methods for the fields
    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

   
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
    	return userRole;
    }
  //newcode
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String userRole) {
        this.userRole = userRole;
    }
}

