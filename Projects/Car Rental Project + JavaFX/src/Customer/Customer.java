package Customer;

import java.io.Serializable;

public class Customer implements Serializable {     //this class is for my customer details
    private int bookingID;      //each customer is provided a booking id
    private String plateNo;     //each customer is provided with the plate no
    private String name;        //each customer will have a name, contact number and nic no
    private String contact;
    private String nic;

    Customer(int bookingID, String plateNo, String name, String contact, String nic){   //constructor for class that takes in 5 parameters
        this.bookingID = bookingID;     //assigns parameter values to the attributes
        this.plateNo = plateNo;
        this.name = name;
        this.contact = contact;
        this.nic = nic;
    }
    //getters and setters for the attributes
    String getName() {
        return name;
    }

    String getContact() {
        return contact;
    }

    String getNic() {
        return nic;
    }

    public String getPlateNo() {
        return plateNo;
    }

    int getBookingID() {
        return bookingID;
    }

    @Override
    public String toString() {      //toString method when writing objects to report from arrayList
        return "Customer{" +
                "bookingID=" + bookingID +
                ", plateNo='" + plateNo + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
}
