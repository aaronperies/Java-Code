package Rental;

import org.junit.Test;      //for my testing I will be using JUnit 4

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WestminsterVehicleRentalManagerTest {      //test class (auto generated)

    private ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();    //arrayList to which all the vehicle objects will be added
    private Car car = new Car("WXZ-12", "Tesla", "520D", 3000, "5", "V6 Parallel Twin", "2800", "Car", "4", "Hands-free");
    private MotorBike bike = new MotorBike("YTP-010", "Harley", "3DX12", 2500, "2", "4 Cylinder Engine", "390", "MotorBike", "Jump-start", "2");
    //creating 2 vehicle objects to be added in to array list in below mentioned methods
    @Test
    public void addVehicle() {
        vehicleArrayList.add(car);      //adds car obj
        vehicleArrayList.add(bike);     //adds motorbike obj

        for (Vehicle vehicle: vehicleArrayList) {   //iterates through array list
            System.out.println(vehicle);            //prints vehicle
        }
        writeStockReport();         //writes report on vehicles
    }

    @Test
    public void deleteVehicle() {
        vehicleArrayList.add(car);      //adds car obj
        vehicleArrayList.add(bike);     //adds motorbike obj
        String plateNo = "WXZ-12";      //provides plate number of vehicle to be deleted

        for (Vehicle vehicle: vehicleArrayList) {           //iterates through the array list
            if (vehicle.getPlateNo().equals(plateNo)){      //checks if provided plate number is present
                System.out.println(vehicle);                //displays vehicle with plate number
                vehicleArrayList.remove(vehicle);           //removes the vehicle
            }
        }
        System.out.println(vehicleArrayList);        //displays entire array list after removing
        writeStockReport();                          //writes vehicle report after deletion
    }

    @Test
    public void editVehicle() {
        vehicleArrayList.add(car);          //adds car obj
        vehicleArrayList.add(bike);         //adds motorbike obj
        String plateNo = "WXZ-12";          //plate number of vehicle whose data will be changed
        double rate = 3500;                 //provide new rate

        for (Vehicle vehicle: vehicleArrayList){            //iterate through the array list
            if (vehicle.getPlateNo().equals(plateNo)){      //checks if provided plate number is available in list
                System.out.println("Rate before change: " + vehicle.getRate());     //displays rate before changing
                vehicle.setRate(rate);                                              //changes the rate
                System.out.println("Rate after change: " + vehicle.getRate());      //displays rate after changing
            }
        }
        writeStockReport();                 //writes report after changing the info of a vehicle
    }

    @Test
    public void printList() {
        vehicleArrayList.add(car);          //adds car obj
        vehicleArrayList.add(bike);         //adds motorbike obj

        System.out.println("List before sorting");
        for (Vehicle vehicle: vehicleArrayList){        //iterates through list
            System.out.println(vehicle.getMake());      //prints obj before sorting
        }
        System.out.println();
        vehicleArrayList.sort((make1, make2) -> make1.getMake().compareToIgnoreCase(make2.getMake()));  //sorts the list
        System.out.println("List after sorting");
        for (Vehicle vehicle: vehicleArrayList){    //iterates through array list once more
            System.out.println(vehicle.getMake());  //displays the obj in sorted manner
        }
    }

    @Test
    public void writeStockReport() {        //this method is tested in the above mentioned methods
        File report = new File("Test Vehicle Stock Report.txt");       //name of text file
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(report, true), true)){
            System.out.println();
            System.out.println("Generating report...");
            printWriter.println("The Vehicle Stock List is as follows: ");
            printWriter.println();
            for (Vehicle vehicle : vehicleArrayList){                           //iterates through entire array list
                printWriter.println("Plate number: " + vehicle.getPlateNo() + ", Make: " + vehicle.getMake()       //writes each vehicles attributes to file
                        + ", Model: " + vehicle.getModel() +", Rate: " + vehicle.getRate() + "(per hour), Vehicle Type: " + vehicle.getType());
            }
            printWriter.println();
            printWriter.println("--------------------------End of report------------------------");
            printWriter.println();
            System.out.println("Please check directory of project for report.");
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}