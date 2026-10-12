package hotelreservationsystem;

public class Payment {

    private int paymentID;
    private int reservationID;
    private double amountPaid;
    private String paymentMethod;
    private String status;

    public Payment(int paymentID, int reservationID,
                   double amountPaid, String paymentMethod,
                   String status) {

        this.paymentID = paymentID;
        this.reservationID = reservationID;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}