package hotelreservationsystem;

public class Admin {

    private int adminID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;

    public Admin(
            int adminID,
            String username,
            String password,
            String firstName,
            String lastName) {

        this.adminID = adminID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // GETTERS

    public int getAdminID() {
        return adminID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    // SETTERS

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}