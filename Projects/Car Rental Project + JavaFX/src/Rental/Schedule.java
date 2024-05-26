package Rental;

public class Schedule{     //this class will be used to keep track of the schedules of the vehicles that are booked

    private int bookingID;  //each schedule set is assigned a unique booking id
    private String plateNo;     //the plate no is also mentioned
    private java.sql.Date pickUpDate;   //date when the customer will pick up the vehicle
    private java.sql.Date dropOffDate;  //ending date of booking

    public Schedule(int bookingID, String plateNo, java.sql.Date pickUpDate, java.sql.Date dropOffDate){    //constructor for schedule
        this.bookingID = bookingID;     //all details required for a schedule will be passed in the constructor
        this.plateNo = plateNo;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
    }

    public java.util.Date getPickUpDate() {
        return pickUpDate;
    }     //returns the pick up date for reference purposes

    public java.util.Date getDropOffDate() {
        return dropOffDate;
    }   //returns the drop off date for reference purposes

    public String getPlateNo() {
        return plateNo;
    }                   //returns the plate no

    public int getBookingID() {
        return bookingID;
    }                   //returns the booking id

    @Override
    public String toString() {      //prints out object with details when required to
        return "Schedule { " +
                " bookingID = '" + bookingID + '\'' +
                ", plateNo = '" + plateNo + '\'' +
                ", pickUpDate = '" + pickUpDate + '\'' +
                ", dropOffDate = '" + dropOffDate + '\'' +
                '}';
    }
}