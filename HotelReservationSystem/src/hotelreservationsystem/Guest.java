/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservationsystem;

/**
 *
 * @author User
 */
public class Guest {
    private int guestID;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public Guest(int guestID, String firstName, String lastName, String contactNumber) {
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public int getGuestID() { return guestID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getContactNumber() { return contactNumber; }

    public void setGuestID(int guestID) { this.guestID = guestID; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}