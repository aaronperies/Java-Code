package Rental;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.InputMismatchException;    //importing exception statement for validation
import java.util.Scanner;   //importing package to enable obtaining inputs

public class VehicleRentalSystem extends Application {

    private static Scanner input = new Scanner(System.in);  //creating object of Scanner class to get input from the user
    private static int option;                              //here I will be assigning a variable to take in an option from the user
    private static int pass;                                //takes in the password input

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Rental.fxml"));   //name of fxml file used
        primaryStage.setTitle("Vehicle Rental System");     //name displayed in the window tab
        primaryStage.setScene(new Scene(root, 1315, 720));  //defining the size of the application window
        primaryStage.show();
    }

    public static void main(String[] args) {
        WestminsterVehicleRentalManager manager = new WestminsterVehicleRentalManager();    //creating object of class to access methods
        manager.displayMainMenu();      //accessing main menu
        enterOption();                  //getting input from user

        while (option != 3) {       //while user has chosen NOT to exit from system
            if (option == 1) {      //manager options
                System.out.print("Please enter the password: ");
                enterPassword();        //prompts for password

                while (pass != manager.getPassword()) {     //when password is incorrect
                    System.out.print("You have entered an incorrect password! Please try again: ");
                    enterPassword();            //inputs password again
                }

                manager.displayManagerMenu();       //if password is correct displays the managers options
                enterOption();                      //prompts for choice

                while (option != 7) {               //while the option is not to return to main menu, enable the manager to use all functions
                    if (option == 1) {
                        manager.addVehicle();       //enables manager to add a vehicle of type car or motorbike
                        System.out.println();
                        System.out.print("Choose an Option: ");
                        enterOption();              //after every function is performed we will be prompting for next choice
                    } else if (option == 2) {
                        manager.deleteVehicle();    //enables manager to delete a vehicle
                        System.out.println();
                        System.out.print("Choose an Option: "); //calls for next option to be chosen
                        enterOption();
                    } else if (option == 3) {
                        manager.editVehicle();      //enables manager to edit a vehicles info
                        System.out.println();
                        System.out.print("Choose an Option: ");   //calls for next option to be chosen
                        enterOption();
                    } else if (option == 4) {       //enables manager to print list of vehicles
                        manager.printList();
                        System.out.print("Choose an Option: "); //calls for next option to be chosen
                        enterOption();
                    } else if (option == 5) {
                        manager.writeStockReport();     //writes the stock to the file
                        System.out.println();
                        System.out.print("Choose an Option: "); //calls for next option to be chosen
                        enterOption();
                    } else if (option == 6) {
                        manager.changePassword();       //enables the manager to change the password
                        System.out.println();
                        System.out.print("Choose an Option: "); //calls for next option to be chosen
                        enterOption();
                    } else if (option == 8){
                        System.out.println();
                        System.out.println("You have exited the system.");
                        System.exit(7);       //exits from system
                    } else {                        //for options chosen besides the ones provided
                        System.out.print("You have entered an incorrect option! Please try again: ");
                        enterOption();
                    }
                }
                manager.displayMainMenu();      //returns manager back to main menu
                enterOption();
            }

            else if (option == 2) {              //customer options
                manager.displayCustomerMenu();  //displays options
                enterOption();              //prompts for choice

                while (option != 2) {       //while customer chooses NOT to return to main menu
                    if (option == 1) {
                        launch(args);       //launches GUI for customer use
                        System.out.print("Please choose your next option: ");
                        enterOption();
                    } else if(option == 3){
                        System.out.println();
                        System.out.println("You have exited the system.");
                        System.exit(7);     //exits from the system
                    } else {
                        System.out.print("You have entered a incorrect option! Please try again: ");
                        enterOption();  //checks invalid options
                    }
                }
                manager.displayMainMenu();  //displays main menu again if 2 is pressed
                enterOption();
            }
            else {
                System.out.print("You have entered an incorrect option! Please try again: ");
                enterOption();  //checks invalid options
            }
        }
        System.out.println("You have exited the system.");
        System.exit(7);     //exits from system
    }

    private static void enterOption(){               //here I will be validating the option that the user chooses
        for (;;) {                          //using endless for loop till a satisfactory option is chosen
            try {
                option = input.nextInt();   //call for option to be entered
                break;
            } catch (InputMismatchException incorrectInput) {   //catches the input mismatch exception
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }

    private static void enterPassword(){             //here I will be validating the password that the manager enters
        for(;;) {                           //using endless for loop till a satisfactory password is entered
            try{
                pass = input.nextInt();     //call for password to be entered
                break;                      //breaks loop if the type of password is correct
            } catch(InputMismatchException incorrectInput){     //catches the input mismatch exception
                System.out.println("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
}