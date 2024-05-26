package Rental;                 //package name

import java.io.File;            //creates text file for intended report
import java.io.FileWriter;
import java.io.IOException;     //for input and file validation
import java.io.PrintWriter;
import java.sql.*;              //connecting to mySQL database
import java.util.ArrayList;     //due to use of array list in storing vehicle obj
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;       //to obtain input from the manager

public class WestminsterVehicleRentalManager implements RentalVehicleManager{

    private Scanner input = new Scanner(System.in);     //creating instance of Scanner class to take inputs
    private int password = 1234;                        //I don't want just anybody to access the managers options, only the manager is allowed to
    private static int lotCount;                               //used to maintain the number of spaces left in the lot
    private ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();    //arrayList to which all the vehicle objects will be added
    private String plateNo, seatingCap, make, model, engine, cc, doorNo, assistanceType, type, start, disc;    //local variables used to take in inputs
    private static double rate;

    WestminsterVehicleRentalManager() {     //default constructor for class
        super();
    }

    private String getPlateNo() {
        return plateNo;
    }       //generating getters and setters of class attributes

    private String getSeatingCap() {
        return seatingCap;
    }

    private String getMake() {
        return make;
    }

    private String getModel() {return model;}

    private String getEngine() {
        return engine;
    }

    private String getCc() {
        return cc;
    }

    private String getDoorNo() {
        return doorNo;
    }

    private String getAssistanceType() {
        return assistanceType;
    }

    private String getType() {
        return type;
    }

    private String getStart() {
        return start;
    }

    private String getDisc() {
        return disc;
    }

    private double getRate() {
        return rate;
    }

    int getPassword() {
        return password;
    }

    @Override
    public void addVehicle() {          //this method will be used to add a vehicle of type car or motorbike into the lot

        addVehiclesToList();            //calls for the database function that will add all vehicle objects into the arrayList
        displayParkingLot();            //displays number of spaces left in parking lot
        if(lotCount == 50){             //checks if lot count has been reached and doesn't allow to add more vehicles
            System.out.println("Maximum number of vehicles have already been acquired!");
        }
        else {                          //if max spaces haven't been used up it'll continue with adding
            System.out.print("Do you want to add a Car or a MotorBike? (c/b) : ");     //choice is obtained from manager
                char choice = input.next().charAt(0);
                input.nextLine();

            while(choice != 'c' && choice != 'C' && choice != 'b' && choice != 'B'){    //validates the choice given
                System.out.println("You have entered an incorrect choice! Please try again.");
                System.out.println();
                System.out.print("Do you want to add a Car or a MotorBike? (c/b) : ");
                    choice = input.next().charAt(0);
                    input.nextLine();                                                   //re prompts for correct choice
            }

            if (choice == 'c' || choice == 'C'){        //if choice is to add a car
                Car carObj = new Car();                 //creates new car obj to access methods
                System.out.println("You chose to add a Car to the Lot.");
                System.out.println();
                System.out.print("Enter the plate number: ");
                    plateNo = input.nextLine();

                for (Vehicle vehicle: vehicleArrayList) {       //validates the plate number to ensure no duplication has taken place
                    while (plateNo.equals(vehicle.getPlateNo())){
                        System.out.print("The plate number you entered already belongs to another vehicle, " +
                                "please enter a different plate number: ");
                        plateNo = input.nextLine();
                    }
                }
                    carObj.setPlateNo(plateNo);             //starts adding all the inputs into the car obj's constructor
                System.out.print("Enter the brand: ");
                    make = input.nextLine();
                    carObj.setMake(make);
                System.out.print("Enter the model: ");
                    model = input.nextLine();
                    carObj.setModel(model);
                System.out.print("Enter the rate (per day) : ");
                    enterRate();
                    carObj.setRate(rate);
                System.out.print("Enter the seating capacity: ");
                    seatingCap = input.nextLine();
                    carObj.setSeatingCap(seatingCap);
                System.out.print("Enter the engine type: ");
                    engine = input.nextLine();
                    carObj.setEngineType(engine);
                System.out.print("Enter the CC value: ");
                    cc = input.nextLine();
                    carObj.setCc(cc);
                System.out.print("Enter the door number: ");
                    doorNo = input.nextLine();
                    carObj.setDoorNo(doorNo);
                System.out.print("Enter the driver assistance: ");
                    assistanceType = input.nextLine();
                    carObj.setAssistanceType(assistanceType);
                type = "Car";           //sets type by default
                start = "";             //sets other specific attributes that don't belong to car class by default
                disc = "";
                carObj.setType(type);
                System.out.println();
                    insertVehicle();    //calls for the obj to be inserted into the database
                    Car carInstance = new Car(plateNo, make, model, rate, seatingCap, engine, cc, type, doorNo, assistanceType);
                    vehicleArrayList.add(carInstance);  //adds newly created car obj to arrayList
                System.out.println("You have successfully added a Car into the lot.");
                System.out.println();
                vehicleArrayList.clear();   //clears arrayList
            }
            else{
                MotorBike bikeObj = new MotorBike();        //if c is not chosen then b has to be the value chosen
                System.out.println("You chose to add a MotorBike to the lot.");
                System.out.println();
                System.out.print("Enter the plate number: ");
                    plateNo = input.nextLine();             //prompts for inputs of motorbike obj

                for (Vehicle vehicle: vehicleArrayList) {       //validates the plate number to ensure no duplication has taken place
                    while (plateNo.equals(vehicle.getPlateNo())){
                        System.out.print("The plate number you entered already belongs to another vehicle, " +
                                "please enter a different plate number: ");
                        plateNo = input.nextLine();
                    }
                }
                    bikeObj.setPlateNo(plateNo);
                System.out.print("Enter the brand: ");
                    make = input.nextLine();
                    bikeObj.setMake(make);
                System.out.print("Enter the model: ");
                    model = input.nextLine();
                    bikeObj.setModel(model);
                System.out.print("Enter the rate (per day) : ");
                    enterRate();
                    bikeObj.setRate(rate);
                System.out.print("Enter the seating capacity: ");
                    seatingCap = input.nextLine();
                    bikeObj.setSeatingCap(seatingCap);
                System.out.print("Enter the engine type: ");
                    engine = input.nextLine();
                    bikeObj.setEngineType(engine);
                System.out.print("Enter the CC value: ");
                    cc = input.nextLine();
                    bikeObj.setCc(cc);
                System.out.print("Enter the start mode: ");
                    start = input.nextLine();
                    bikeObj.setStartMode(start);
                System.out.print("Enter the number of discs in a tyre: ");
                    disc = input.nextLine();
                    bikeObj.setTyreDisc(disc);
                type = "Motorbike";             //sets type by default
                bikeObj.setType(type);
                doorNo = "";                  //sets specific attributes of car class to null
                assistanceType = "";
                System.out.println();
                    insertVehicle();          //inserts motorbike into database
                    MotorBike bikeInstance = new MotorBike(plateNo, make, model, rate, seatingCap, engine, cc, type, start, disc);
                    vehicleArrayList.add(bikeInstance);     //inserts newly created bike obj into arrayList
                System.out.println("You have successfully added a Bike into the lot.");
                System.out.println();
                vehicleArrayList.clear();     //clears ArrayList
            }                                 //the arrayList in cleared in every method because the obj's will otherwise be repeatedly added into the arrayList
            displayParkingLot();            //displays number of spaces left in parking lot
            writeStockReport();             //writes the vehicle list report after changes are made
        }
    }

    @Override
    public void deleteVehicle() {       //this method is used to delete a vehicle from the parking lot
        addVehiclesToList();            //adds all the vehicles into the arrayList

        if (vehicleArrayList.isEmpty()){    //checks if parking lot is empty before removing vehicles
            System.out.println("Go back and add vehicles! Parking lot is empty");
        }
        else {                              //if it isn't empty then it proceeds to ask for the plate number
            System.out.print("Enter the plate number of the vehicle: ");
            plateNo = input.nextLine();

            //I have chosen to use the iterator instead of for each because concurrentModificationException springs up
            for (Iterator<Vehicle> vehicleIterator = vehicleArrayList.iterator(); vehicleIterator.hasNext();){
                Vehicle vehicle = vehicleIterator.next();
                if (vehicle.getPlateNo().equals(plateNo)) {     //checks if the plates are equal
                    vehicleIterator.remove();                   //then removes from the arrayList
                    delete();                                   //deletes from database as well
                }
            }
            System.out.println();
            displayParkingLot();                //displays spaces remaining
            vehicleArrayList.clear();           //clears arrayList
            writeStockReport();                 //writes the vehicle stock report to illustrate changes
        }
    }

    @Override
    public void editVehicle(){          //allows the manager to edit particular attributes of a vehicle
        addVehiclesToList();            //obtains objects from database
        System.out.println("Do keep in mind the only 2 features of a vehicle that can be edited are the rate per hour & the engine type!");
        System.out.println("Enter the plate number of the vehicle of which you want to edit the information of: ");
            plateNo = input.nextLine();     //gets plate number
            toBeChanged();                  //displays data of vehicle before changing
        System.out.println();
            System.out.print("Enter the new Engine type: ");
            engine = input.nextLine();      //gets new engine
            System.out.print("Enter the new rate: ");
            enterRate();      //gets new rate
        changed();                          //performs the changes in the database
        System.out.println("You have successfully changed the attributes of the vehicle");
        vehicleArrayList.clear();
        writeStockReport();                 //writes vehicle report list with required changes
    }

    @Override
    public void printList() {              //method to print the arrayList in order of make
        addVehiclesToList();               //obtains vehicles from database
        System.out.println("Printing the list of items...");
        System.out.println();
        vehicleArrayList.sort((make1, make2) -> make1.getMake().compareToIgnoreCase(make2.getMake()));  //sorts the arrayList
        for (Vehicle vehicle: vehicleArrayList){    //for each vehicle obj in the array List
            System.out.println("Plate No: " + vehicle.getPlateNo() + ", Make: " + vehicle.getMake() + ", Model: " + vehicle.getModel() + ", Type: " + vehicle.getType());
        }   //prints out above shown details
        System.out.println();
        vehicleArrayList.clear();
    }

    @Override
    public void writeStockReport() {        //writes a stock report for the vehicle stock
        addVehiclesToList();                //obtains vehicles from database
        File report = new File("Vehicle Stock Report.txt");     //creates text file
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(report, true), true)){
            //file writer is used so that the new data is added to the list and doesn't replace the existing data
            System.out.println();
            System.out.println("Generating report...");
            printWriter.println("The Vehicle Stock List is as follows: ");
            printWriter.println();
            for (Vehicle vehicle : vehicleArrayList){   //for each vehicle obj in vehicle ArrayList
                printWriter.println("Plate number: " + vehicle.getPlateNo() + ", Make: " + vehicle.getMake()
                + ", Model: " + vehicle.getModel() +", Rate: " + vehicle.getRate() + "(per hour), Vehicle Type: " + vehicle.getType());
            }   //above mentioned attributes are printed
            printWriter.println();
            printWriter.println("--------------------------End of report------------------------");
            printWriter.println();
            System.out.println("Please check directory of project for report.");
        }catch (IOException ioException){       //catches IOException thrown by FileWriter
            ioException.printStackTrace();
        }
        vehicleArrayList.clear();
    }

    void changePassword(){          //this is a function for the manager where he/she can change the current password
        System.out.println("Enter the current password: ");
        int pass = input.nextInt();     //gets current password

        while(pass != password){        //checks if password is correct
            System.out.println("You entered the incorrect password, please try again: ");
            pass = input.nextInt();     //asks for correct password
        }
        System.out.println("Enter your new password: ");
        password = input.nextInt();     //gets new password
        System.out.println("You have successfully changed your password!.");
    }

    private static void enterRate(){             //here I will be validating the password that the manager enters
        Scanner input = new Scanner(System.in);
        for(;;){
            try{
                rate = input.nextDouble();
                break;
            } catch(InputMismatchException incorrectInput){     //catch the InputMismatchException thrown by incorrect data types
                System.out.println("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }

    void displayMainMenu(){     //main menu from which manager or customer can choose the options he/she wants to access
        System.out.println("Welcome to the Westminster Car Rental Service!");
        System.out.println();
        System.out.println("Options: ");
        System.out.println("    1). Manager Options");
        System.out.println("    2). Customer Options");
        System.out.println("    3). Exit from System");
        System.out.println();
        System.out.print("Please enter your option: ");
    }

    void displayManagerMenu(){      //displays the options available to the manager
        System.out.println();
        System.out.println("Options: ");
        System.out.println("    1). Add a new Vehicle");
        System.out.println("    2). Delete a Vehicle");
        System.out.println("    3). Edit Vehicle information");
        System.out.println("    4). Print List of Vehicles");
        System.out.println("    5). Generate report");
        System.out.println("    6). Change Password");
        System.out.println("    7). Return to Main menu");
        System.out.println("    8). Exit from System");
        System.out.println();
        System.out.print("Please enter your option: ");
    }

    void displayCustomerMenu(){     //displays the options available to the customer
        System.out.println();
        System.out.println("Options: ");
        System.out.println("    1). Display list of Vehicles/ Book a Vehicle");
        System.out.println("    2). Return to Main menu");
        System.out.println("    3). Exit System");
        System.out.println();
        System.out.print("Please enter your option: ");
    }

    private void displayParkingLot(){       //this method will obtain the count of records in the database
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            Statement query = connect.createStatement();
            ResultSet setOne = query.executeQuery("SELECT COUNT(plateNo) AS lotCount FROM Vehicle;");
            //executes query to obtain count
            if(setOne.next()){
                lotCount = setOne.getInt(1);    //assigns the filled slot number to variable
                System.out.println((50-lotCount) + " lots are available for use."); //displays remaining spaces in parking lot
            }
        }
        catch (Exception connectionException){
            connectionException.printStackTrace();  //catches the errors for getConnection method
            System.out.println("Error connecting to database");
        }
    }

    private void insertVehicle(){           //inserts the vehicle data (car or motorbike) into the database
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            String addVehicle = "insert into Vehicle (plateNo, make, model, rate, seatingCap, engineType, cc, doorN0, assistance, startMode, tyreDisc, vehicleType)" + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //inserts data into the relevant columns with a string query
            PreparedStatement vehicleEntry = connection.prepareStatement(addVehicle);
                vehicleEntry.setString(1, getPlateNo());    //adds data into columns
                vehicleEntry.setString(2, getMake());       //one by one
                vehicleEntry.setString(3, getModel());
                vehicleEntry.setDouble(4, getRate());
                vehicleEntry.setString(5, getSeatingCap());
                vehicleEntry.setString(6, getEngine());
                vehicleEntry.setString(7, getCc());
                vehicleEntry.setString(8, getDoorNo());
                vehicleEntry.setString(9, getAssistanceType());
                vehicleEntry.setString(10, getStart());
                vehicleEntry.setString(11, getDisc());
                vehicleEntry.setString(12, getType());
                vehicleEntry.execute();

            System.out.println("Vehicle has been added!");      //displays message after insertion is complete
        }
        catch (Exception connectionException){                  //catches exception thrown by the getConnection method
            connectionException.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }

    private void addVehiclesToList(){   //this database method is used to obtain all the items in the list and add them to the array list
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            Statement getVehicle = connection.createStatement();
            Statement getVehicleType = connection.createStatement();
            String get = "select * from Vehicle";       //obtains all the values in columns
            ResultSet listOfVehicles = getVehicle.executeQuery(get);
            ResultSet vehicleType = getVehicleType.executeQuery("select vehicleType from Vehicle"); //obtains the vehicle type of each record

            if (vehicleType.next()){       //while the vehicle Type of records are returned
                String thisType = vehicleType.getString(1);
                if (thisType.equals("Car")){    //checks if vehicle is a car
                    while (listOfVehicles.next()){  //while there are records in the table
                        plateNo = listOfVehicles.getString(1); make = listOfVehicles.getString(2); model = listOfVehicles.getString(3); rate = listOfVehicles.getDouble(4);
                        seatingCap = listOfVehicles.getString(5); engine = listOfVehicles.getString(6); cc = listOfVehicles.getString(7);
                        doorNo = listOfVehicles.getString(8); assistanceType = listOfVehicles.getString(9); start = listOfVehicles.getString(10);
                        disc = listOfVehicles.getString(11); type = listOfVehicles.getString(12);
                        Car car = new Car(getPlateNo(), getMake(), getModel(), getRate(), getSeatingCap(), getEngine(), getCc(), getType(), getDoorNo(), getAssistanceType());
                        //creates new car obj and adds it to the arrayList
                        vehicleArrayList.add(car);
                    }
                }
                else if (thisType.equals("MotorBike")){     //checks if vehicle is a motor bike
                    while (listOfVehicles.next()){          //while there are records in the table
                        plateNo = listOfVehicles.getString(1); make = listOfVehicles.getString(2); model = listOfVehicles.getString(3); rate = listOfVehicles.getDouble(3);
                        seatingCap = listOfVehicles.getString(4); engine = listOfVehicles.getString(5); cc = listOfVehicles.getString(6);
                        doorNo = listOfVehicles.getString(7); assistanceType = listOfVehicles.getString(8); start = listOfVehicles.getString(9);
                        disc = listOfVehicles.getString(10); type = listOfVehicles.getString(11);
                        MotorBike bike = new MotorBike(getPlateNo(), getMake(), getModel(), getRate(), getSeatingCap(), getEngine(), getCc(), getType(), getStart(), getDisc());
                        //creates new motorBike obj and adds it to the arrayList
                        vehicleArrayList.add(bike);
                    }
                }
            }
        }
        catch (Exception connectionException){      //catches exception thrown by getConnection method
            connectionException.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }

    private void delete(){          //deletes vehicle from database
        String deletedVehicleType = "";     //used to store the vehicle type to display later onwards
        try{
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");       //connecting to db
            Statement deleteVehicle = connection.createStatement();

            ResultSet deletedVehicle;
            deletedVehicle = deleteVehicle.executeQuery("select vehicleType from Vehicle where plateNo='" + getPlateNo() + "'");   //must use inverted commas when mentioning the plate number
            //gets the vehicle type of record which matches with the plate number entered

            if (deletedVehicle.next()){     //while there is a record
                deletedVehicleType = deletedVehicle.getString(1);   //vehicle type is assigned
            }
            String deleteVehicleFromDB = "delete from Vehicle where plateNo='" + getPlateNo() + "'";
            deleteVehicle.executeUpdate(deleteVehicleFromDB);       //deletes the relevant vehicle from database

            System.out.println("You have successfully deleted a " + deletedVehicleType + " from the system");   //displays type of vehicle deleted
        }catch (Exception connectionException){         //catches exception thrown by getConnection method
            connectionException.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }

    private void toBeChanged(){     //this method displays some attributes of the vehicle to be edited
        String model = "";
        double rate = 0;
        String engine = "";
        String type = "";
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            Statement changedVariables = connection.createStatement();
            ResultSet rateEngine = changedVariables.executeQuery("select model, rate, engineType, vehicleType from vehicle where plateNo='" + getPlateNo() + "'");
            //obtains specific attributes of vehicle to be edited
            if (rateEngine.next()){                                //if there is a record that matches
                model = rateEngine.getString(1);        //specific attributes are assigned to variables
                rate = rateEngine.getDouble(2);
                engine = rateEngine.getString(3);
                type = rateEngine.getString(4);
            }
            System.out.println("Plate No: " + getPlateNo() + ", Model: " + model + ", Rate: " + rate + ", Engine Type: " + engine + ", Vehicle Type: " + type);
        }catch (Exception connectionException){                     //catches exception thrown by getConnection method
            connectionException.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }

    private void changed(){             //method used to change the rate and engine type of vehicles in database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/VehicleRentalSystem","root","");
            String query = "update vehicle set rate = ?, engineType = ? where plateNo ='" + getPlateNo() + "'"; //query to update columns
                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setDouble   (1, getRate());    //sets new values in column
                preparedStmt.setString(2, getEngine());

            preparedStmt.executeUpdate();               //executes the update statement
        }catch (Exception connectionException){         //catches the exception thrown by getConnection method
            connectionException.printStackTrace();
            System.out.println("Error connecting to database");
        }
    }
}