package hotelreservationsystem;

public class Reservation {

    private int reservationID;
    private int guestID;
    private int roomID;
    private String checkInDate;
    private String checkOutDate;
    private double totalAmount;
    private double downPayment;
    private String status;

    public Reservation(
            int reservationID,
            int guestID,
            int roomID,
            String checkInDate,
            String checkOutDate,
            double totalAmount,
            double downPayment,
            String status) {

        this.reservationID = reservationID;
        this.guestID = guestID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalAmount = totalAmount;
        this.downPayment = downPayment;
        this.status = status;
    }

    public int getReservationID() {
        return reservationID;
    }

    public int getGuestID() {
        return guestID;
    }

    public int getRoomID() {
        return roomID;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }
}