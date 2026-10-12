/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservationsystem;

/**
 *
 * @author User
 */
public class Room {
        private int roomID;
    private String roomNumber;
    private String roomType;
    private double price;
    private String roomStatus;
    private String amenities;
    private int maxPax;

    public Room(int roomID, String roomNumber, String roomType,
                double price, String roomStatus,
                String amenities, int maxPax) {

        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.roomStatus = roomStatus;
        this.amenities = amenities;
        this.maxPax = maxPax;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public String getAmenities() {
        return amenities;
    }

    public int getMaxPax() {
        return maxPax;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public void setMaxPax(int maxPax) {
        this.maxPax = maxPax;
    }

    public void setStatus(String status) {
    this.roomStatus = status;
}

public int getCapacity() {
    return maxPax;
}
}