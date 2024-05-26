package Rental;

import Customer.Customer;                       //relevant import statements are used to import any required methods from external classes
import Customer.CustomerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {       //class used to control actions in the Rental.fxml file
    private static String plateNo;          //plate no entered in search bar
    private static LocalDate pickUpDate;    //date entered in date picker
    private static LocalDate dropOffDate;   //date entered in date picker

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }       //returns the pick up date

    public LocalDate getDropOffDate() {
        return dropOffDate;
    }   //returns the drop off date

    public String getPlateNo() {
        return plateNo;
    }   //returns the plate number

    @FXML
    private TableView<VehicleDatabaseModel> tableManager;           //reference to table view in fxml
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnPlate;  //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnMake;   //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnModel;  //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, Double> columnRate;   //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnCapacity;   //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnEngine; //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnCc;     //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnDoor;   //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnAssistance; //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnStart;  //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnDisc;   //reference to column in table view of fxml file
    @FXML
    private TableColumn<VehicleDatabaseModel, String> columnType;   //reference to column in table view of fxml file

    @FXML
    private RadioButton radioRateLess;  //reference to radio button that filters rate
    @FXML
    private RadioButton radioRateMore;  //reference to radio button that filters rate
    @FXML
    private RadioButton filterCar;      //reference to radio button that filters cars
    @FXML
    private RadioButton filterBike;         //reference to radio button that filters bikes
    private ToggleGroup filterToggleGroup;  //creates a toggle group for radio buttons
    private ToggleGroup typeToggleGroup;    //creates a toggle group for radio buttons

    @FXML
    private TextField searchPlate;  //reference to search field
    @FXML
    private Button bookVehicle;     //reference to button that pop ups new window
    @FXML
    private DatePicker pickDate;    //reference to first date picker field
    @FXML
    private DatePicker dropDate;    //reference to second date picker field
    @FXML
    private Label status;           //reference to label that shows the availability of a vehicle

    @Override
    public void initialize(URL location, ResourceBundle resources) {       //method automatically runs when launching the gui
        ObservableList<VehicleDatabaseModel> vehicleList = FXCollections.observableArrayList(); //create observable list to add vehicle objects
        bookVehicle.setDisable(true);       //display book button when search field is empty
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");  //connects to database
            ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle;");   //executes query passed in parameters
                                                                                                    //gets all vehicle objects from db
            while(rs1.next()){                              //if there are any results returned from the query
                vehicleList.add(new VehicleDatabaseModel(   //adds the vehicles into the observable list as new objects
                        rs1.getString(1),        //assigns each columns value to the relevant attribute of vehicle
                        rs1.getString(2),
                        rs1.getString(3),
                        rs1.getDouble(4),
                        rs1.getString(5),
                        rs1.getString(6),
                        rs1.getString(7),
                        rs1.getString(8),
                        rs1.getString(9),
                        rs1.getString(10),
                        rs1.getString(11),
                        rs1.getString(12)
                ));
            }
        }
        catch (Exception connectionException){                  //catches exception thrown by get connection method
            connectionException.printStackTrace();
            System.out.println("There was an error when connecting to the database");   //displays error message
        }

        columnPlate.setCellValueFactory(new PropertyValueFactory<>("plateNo"));     //assigns the values returned from the,
        columnMake.setCellValueFactory(new PropertyValueFactory<>("make"));         //database to the columns in table view
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("seatingCap"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnCc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        columnDoor.setCellValueFactory(new PropertyValueFactory<>("doorNo"));
        columnAssistance.setCellValueFactory(new PropertyValueFactory<>("assistance"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnDisc.setCellValueFactory(new PropertyValueFactory<>("disc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableManager.setItems(vehicleList);                     //pushes the data into the table view

        filterToggleGroup = new ToggleGroup();
        this.radioRateLess.setToggleGroup(filterToggleGroup);   //adds radio buttons for rate filtering into the same toggle group
        this.radioRateMore.setToggleGroup(filterToggleGroup);

        typeToggleGroup = new ToggleGroup();                    //adds radio buttons for vehicle filtering into the same toggle group
        this.filterCar.setToggleGroup(typeToggleGroup);
        this.filterBike.setToggleGroup(typeToggleGroup);
    }

    public void filter(){       //I learned how to filter using toggle groups at this link https://www.youtube.com/watch?v=BXjDhFuikQw
        filterToggleGroup.selectToggle(null);   //deselects the radio buttons in this toggle group
        ObservableList<VehicleDatabaseModel> vehicleList = FXCollections.observableArrayList();     //creates observable list
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            //establishes connection
            if (this.typeToggleGroup.getSelectedToggle().equals(this.filterCar)){   //checks if cars are required
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle where vehicleType = 'Car';");     //executes query to get only cars from db
                while(rs1.next()){                              //while there are results returned from the query
                    vehicleList.add(new VehicleDatabaseModel(   //adds the vehicles into the observable list as objects
                            rs1.getString(1),        //gets value from each column
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getDouble(4),
                            rs1.getString(5),
                            rs1.getString(6),
                            rs1.getString(7),
                            rs1.getString(8),
                            rs1.getString(9),
                            rs1.getString(10),
                            rs1.getString(11),
                            rs1.getString(12)));
                }
            }
            else if (this.typeToggleGroup.getSelectedToggle().equals(this.filterBike)){     //checks if bikes are required
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle where vehicleType = 'Motorbike';");   //executes query to get only bikes from db
                while(rs1.next()){                              //while there are results returned from the query
                    vehicleList.add(new VehicleDatabaseModel(   //adds the vehicles into the observable list as objects
                            rs1.getString(1),        //gets value from each column
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getDouble(4),
                            rs1.getString(5),
                            rs1.getString(6),
                            rs1.getString(7),
                            rs1.getString(8),
                            rs1.getString(9),
                            rs1.getString(10),
                            rs1.getString(11),
                            rs1.getString(12)));
                }
            }
        }
        catch (Exception connectionException){         //catches exception thrown by the get connection method
            connectionException.printStackTrace();
            System.out.println("There was an error when connecting to the database");   //displays error message
        }

        columnPlate.setCellValueFactory(new PropertyValueFactory<>("plateNo"));         //assigns the values returned from the,
        columnMake.setCellValueFactory(new PropertyValueFactory<>("make"));             //database to the columns in table view
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("seatingCap"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnCc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        columnDoor.setCellValueFactory(new PropertyValueFactory<>("doorNo"));
        columnAssistance.setCellValueFactory(new PropertyValueFactory<>("assistance"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnDisc.setCellValueFactory(new PropertyValueFactory<>("disc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableManager.setItems(vehicleList);
    }

    //I learned how to filter using toggle groups at this link https://www.youtube.com/watch?v=BXjDhFuikQw
    public void filterRate(){                         //method to filter rates using radio buttons
        typeToggleGroup.selectToggle(null);     //deselects the radio buttons in this toggle group
        ObservableList<VehicleDatabaseModel> vehicleList = FXCollections.observableArrayList();     //creates observable list
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            //connects to database
            if (this.filterToggleGroup.getSelectedToggle().equals(this.radioRateLess)){     //checks if rate should be less than 3000
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle where rate < 3000;");
                //executes query to obtain all vehicles with less than 3000 rate
                while(rs1.next()){                                  //while there are results returned from the query
                    vehicleList.add(new VehicleDatabaseModel(       //adds the vehicles into the observable list as objects
                            rs1.getString(1),            //gets value from each column
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getDouble(4),
                            rs1.getString(5),
                            rs1.getString(6),
                            rs1.getString(7),
                            rs1.getString(8),
                            rs1.getString(9),
                            rs1.getString(10),
                            rs1.getString(11),
                            rs1.getString(12)));
                }
            }
            else if (this.filterToggleGroup.getSelectedToggle().equals(this.radioRateMore)){    //checks if rate should be more than 3000
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle where rate >= 3000;");
                //executes query to obtain all vehicles with more than 3000 rate
                while(rs1.next()){                                  //while there are results returned from the query
                    vehicleList.add(new VehicleDatabaseModel(       //adds the vehicles into the observable list as objects
                            rs1.getString(1),            //gets value from each column
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getDouble(4),
                            rs1.getString(5),
                            rs1.getString(6),
                            rs1.getString(7),
                            rs1.getString(8),
                            rs1.getString(9),
                            rs1.getString(10),
                            rs1.getString(11),
                            rs1.getString(12)));
                }
            }
        }
        catch (Exception connectionException){      //catches exception thrown from get connection method
            connectionException.printStackTrace();
            System.out.println("There was an error when connecting to the database");   //displays error message
        }

        columnPlate.setCellValueFactory(new PropertyValueFactory<>("plateNo"));     //assigns the values returned from the,
        columnMake.setCellValueFactory(new PropertyValueFactory<>("make"));         //database to the columns in table view
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("seatingCap"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnCc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        columnDoor.setCellValueFactory(new PropertyValueFactory<>("doorNo"));
        columnAssistance.setCellValueFactory(new PropertyValueFactory<>("assistance"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnDisc.setCellValueFactory(new PropertyValueFactory<>("disc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableManager.setItems(vehicleList);             //pushes data into the table view
    }

    public void searchPlateNo(){        //this method is to return the vehicle record that is searched for using the plate number
        filterToggleGroup.selectToggle(null);     //nullifies both toggle groups
        typeToggleGroup.selectToggle(null);       //because no filtering should be applied when displaying searched record
        CustomerController customerController= new CustomerController();    //creates obj of customer controller
        customerController.getFromDatabase();       //accesses method in customer controller using obj
        ObservableList<VehicleDatabaseModel> vehicleList = FXCollections.observableArrayList();     //creates an observable list
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            //establishes connection with database
            if (searchPlate.getText().isEmpty()){       //checks if nothing is searched
                bookVehicle.setDisable(true);           //display book button when search field is empty
                status.setText("Availability Status");  //nullifies the availability status
                status.setTextFill(Paint.valueOf("white"));     //sets the status to white
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle;");   //displays all vehicles again

                while(rs1.next()){
                    vehicleList.add(new VehicleDatabaseModel(
                            rs1.getString(1),
                            rs1.getString(2),
                            rs1.getString(3),
                            rs1.getDouble(4),
                            rs1.getString(5),
                            rs1.getString(6),
                            rs1.getString(7),
                            rs1.getString(8),
                            rs1.getString(9),
                            rs1.getString(10),
                            rs1.getString(11),
                            rs1.getString(12)
                    ));
                }
            }
            else{
                ResultSet rs1 = connect.createStatement().executeQuery("select * from vehicle where plateNo='" + searchPlate.getText() + "'");
                //if search plate is searched for, displays the particular vehicles details in table view
                    while(rs1.next()){
                        vehicleList.add(new VehicleDatabaseModel(
                                rs1.getString(1),
                                rs1.getString(2),
                                rs1.getString(3),
                                rs1.getDouble(4),
                                rs1.getString(5),
                                rs1.getString(6),
                                rs1.getString(7),
                                rs1.getString(8),
                                rs1.getString(9),
                                rs1.getString(10),
                                rs1.getString(11),
                                rs1.getString(12)
                        ));
                    }
                plateNo = searchPlate.getText();    //assigns the plates search value to plateNo (used in other classes)
            }
        }
        catch (Exception connectionException){      //catches exception thrown by get connection method
            connectionException.printStackTrace();
            System.out.println("There was an error when connecting to the database");   //displays error message
        }

        columnPlate.setCellValueFactory(new PropertyValueFactory<>("plateNo"));     //assigns the values returned from the,
        columnMake.setCellValueFactory(new PropertyValueFactory<>("make"));         //database to the columns in table view
        columnModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        columnRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        columnCapacity.setCellValueFactory(new PropertyValueFactory<>("seatingCap"));
        columnEngine.setCellValueFactory(new PropertyValueFactory<>("engine"));
        columnCc.setCellValueFactory(new PropertyValueFactory<>("cc"));
        columnDoor.setCellValueFactory(new PropertyValueFactory<>("doorNo"));
        columnAssistance.setCellValueFactory(new PropertyValueFactory<>("assistance"));
        columnStart.setCellValueFactory(new PropertyValueFactory<>("start"));
        columnDisc.setCellValueFactory(new PropertyValueFactory<>("disc"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableManager.setItems(vehicleList);         //pushes data into the table view
    }

    public void enterCustomerDetails(){         //Generating a new window was referenced from https://www.youtube.com/watch?v=ZzwvQ6pa_tk
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Customer/Customer.fxml"));    //generates window with design from Customer.fxml
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setTitle("Customer Details");
            stage.setScene(new Scene(root1));
            stage.show();   //displays new window when book now button is clicked

            pickUpDate = pickDate.getValue();                       //gets value entered in the date picker and assigns it to a variable
            dropOffDate = dropDate.getValue();                      //gets value entered in the date picker and assigns it to a variable
        }catch (IOException inputOutputException){                  //catches exception thrown by the get Resource method
            inputOutputException.printStackTrace();
            System.out.println("Failed to create new window");      //displays error message
        }
    }

    public void displayAvailability(){          //shows the availability of a particular vehicle when check availability button is clicked
        CustomerController c1 = new CustomerController();   //creates new customer controller obj
        for (Customer customer: c1.getCustomers()){         //obtains the arrayList of customers from the controller class
            if (customer.getPlateNo().equals(searchPlate.getText())){   //if the plate number is present in the customer class meaning it's booked
                status.setTextFill(Paint.valueOf("red"));   //displays not available in red
                status.setText("  Not available");
                bookVehicle.setDisable(true);               //disables booking button
            }
            else if(!customer.getPlateNo().equals(searchPlate.getText())){  //if the plate number is not present
                status.setTextFill(Paint.valueOf("green")); //displays available in green
                status.setText("     Available!");
                bookVehicle.setDisable(false);              //enables booking button
            }
        }
    }
}