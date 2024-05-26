package Customer;

import Rental.Schedule;
import Rental.VehicleController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CustomerController {           //this controller will be used for the Customer.fxml file for the 2nd window
    private VehicleController vc = new VehicleController();         //creates and obj of Vehicle Controller class to reference required methods
    private static ArrayList<Customer> customers = new ArrayList<Customer>();   //creates a static arrayList of customers so that the same list with the same objects are available
    private static ArrayList<Schedule> schedules = new ArrayList<>();      //creates a list of schedules as well to store the schedule for each plate number
    private static int bookingNo;       //assigns a booking number for each booking

    public ArrayList<Customer> getCustomers() {
        return customers; }     //used in other classes to access the list of customers

    public CustomerController(){
        super();
    }   //generates default constructor for general purpose of creating objects to access methods

    @FXML
    private TextField name;     //reference to textField in fxml
    @FXML
    private TextField number;   //reference to textField in fxml
    @FXML
    private TextField nic;      //reference to textField in fxml
    @FXML
    private Label bookedState;  //reference to label in fxml
    @FXML
    private Label bookingID;    //reference to label in fxml

    //getters for the references
    private String getName() {
        return name.getText();
    }

    private String getNumber() {
        return number.getText();
    }

    private String getNic() {
        return nic.getText();
    }

    private int getBookingNo(){
        return bookingNo;
    }

    public void assignDetails(){    //this method is used to assign all the details provided in the GUI to the objects of respective classes
        String name = getName();    //assign the name to a string
        String number = getNumber();    //assigns the contact details to a string
        String nic = getNic();         //assigns the nic no to a string
        String plateNo = vc.getPlateNo();   //gets the plate number searched for in the vehicle controller class
        Random rand = new Random();     //random number generator for bookingId
        bookingNo = rand.nextInt(150);

        Customer customerObj = new Customer(getBookingNo(), plateNo, name, number, nic);    //creates new Customer obj and adds relevant data to constructor
        customers.add(customerObj);         //adds customer obj to arrayList of objects
        writeCustomerReport();              //calls for the report to be generated

        LocalDate pickUpDate = vc.getPickUpDate();      //assigns the pickUpDate to a local date
        LocalDate dropOffDate = vc.getDropOffDate();    //assigns the dropOffDate to a local date
        Date pickUp = Date.valueOf(pickUpDate);         //converts the local date to a mysql date
        Date dropOff = Date.valueOf(dropOffDate);       //converts the local date to a mysql date
        Schedule schedule = new Schedule(getBookingNo(), plateNo, pickUp, dropOff);     //creates new schedule obj and adds relevant attributes
        schedules.add(schedule);            //adds the schedule obj to a arrayList of schedules
        writeScheduleReport();              //calls for the schedule list to be generated

        bookedState.setText("Vehicle has been booked!");            //vehicle is booked is printed when submit is pressed
        bookingID.setText("Your booking ID is " + getBookingNo());  //displays the booking ID assigned to customer
        addToDatabase(getBookingNo(), name, number, nic, plateNo, pickUp, dropOff);     //calls for method that adds the objects into the respective tables in db
        bookingNo++;
    }

    private void writeCustomerReport(){         //writes a report with the customer details
        File report = new File("Customer Report.txt");      //new file with name
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(report, true), true)){
            System.out.println();
            System.out.println("Generating Customer List...");
            printWriter.println("The Customer List is as follows: ");
            printWriter.println();
            for (Customer customer : customers){
                printWriter.println("Booking ID: " + customer.getBookingID() + ", Plate No: " + customer.getPlateNo()
                        + ", Customer name: " + customer.getName() +", Contact Details: " + customer.getContact() + ", NIC: " + customer.getNic());
            }
            printWriter.println();
            printWriter.println("--------------------------End of report------------------------");
            printWriter.println();
            System.out.println("Please check directory of project for report.");
        }catch (IOException ioException){       //catches exception thrown by the fileWriter
            ioException.printStackTrace();
        }
    }

    private void writeScheduleReport(){         //writes a report with the schedule details
        File report = new File("Schedule Report.txt");      //new file with name
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(report, true), true)){
            System.out.println();
            System.out.println("Generating Vehicle Schedule...");
            printWriter.println("The Vehicle Schedule is as follows: ");
            printWriter.println();
            for (Schedule schedule : schedules){
                printWriter.println("Booking ID: " + schedule.getBookingID() + ", Plate No: " + schedule.getPlateNo()
                        + ", Pick up date: " + schedule.getPickUpDate() +", Drop off date: " + schedule.getDropOffDate());
            }
            printWriter.println();
            printWriter.println("--------------------------End of report------------------------");
            printWriter.println();
            System.out.println("Please check directory of project for report.");
        }catch (IOException ioException){       //catches exception thrown by the fileWriter
            ioException.printStackTrace();
        }
    }

    //this method is used to add the customer and schedule records to the database
    private void addToDatabase(int id, String name, String number, String nic, String plate, Date dateOne, Date dateTwo){
            try{
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
                String addVehicle = "insert into customer (bookingId, plateNo, custName, contact, nic)" + "values(?, ?, ?, ?, ?)";

                PreparedStatement customerEntry = connection.prepareStatement(addVehicle);
                customerEntry.setInt(1, id);
                customerEntry.setString(2, plate);
                customerEntry.setString(3, name);
                customerEntry.setString(4, number);
                customerEntry.setString(5, nic);
                customerEntry.execute();
                customerEntry.close();

                String addSchedule = "insert into scheduleSlot (bookingId, plateNo, pickUpDate, dropOffDate)" + "values(?, ?, ?, ?)";
                PreparedStatement scheduleEntry = connection.prepareStatement(addSchedule);
                scheduleEntry.setInt(1, id);
                scheduleEntry.setString(2, plate);
                scheduleEntry.setDate(3, dateOne);
                scheduleEntry.setDate(4, dateTwo);
                scheduleEntry.execute();
                scheduleEntry.close();

                System.out.println();
                System.out.println("Customer has been added!");
                System.out.println("Schedule has been updated");
            }
            catch (Exception connectionException){      //catches exception thrown by get connection method
                connectionException.printStackTrace();
            }
    }

    //this method is used to retrieve the customer and schedule records from the database
    public void getFromDatabase(){
        try{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
        Statement getCustomer = connection.createStatement();
        Statement getSchedule = connection.createStatement();
        String get = "select * from customer";
        String getNext = "select * from scheduleSlot";
        ResultSet listOfCustomers = getCustomer.executeQuery(get);
            while (listOfCustomers.next()) {
                customers.add(new Customer(listOfCustomers.getInt(1), listOfCustomers.getString(2), listOfCustomers.getString(3),
                        listOfCustomers.getString(4), listOfCustomers.getString(5)));
            }

        ResultSet schedule = getSchedule.executeQuery(getNext);
            while (schedule.next()){
                schedules.add(new Schedule(schedule.getInt(1), schedule.getString(2), schedule.getDate(3),
                        schedule.getDate(4)));
            }
    }
        catch (SQLException sqlException){      //catches exception thrown by get connection method
            sqlException.printStackTrace();
            System.out.println("Error retrieving records");
        }
    }
}