package MusicStorePackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicStore extends Application {

    private static Scanner input = new Scanner(System.in);      //creating object of Scanner class to get input from the user
    private static int option;           //here I will be assigning a variable to take in an input from the user

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MusicStore.fxml"));
        primaryStage.setTitle("Music Store");                               //title that will be displayed in the title bar of the window
        primaryStage.setScene(new Scene(root, 1300, 700));      //defining the width and height of the windows that will be launched
        primaryStage.show();
    }

    public static void main(String[] args) {

        WestminsterMusicStoreManager manager = new WestminsterMusicStoreManager(0, "", "", "", "", 0,"",0,0,0,0);
        manager.menu();                         //getting the menu printed so the user can choose which option he wants to use
        getOption();                            //gets option from the user

    while(option == (int)option){               //here I will be checking which option is being chosen and if an incorrect integer is entered, an error message is printed out
        if (option == 1) {
            manager.add();                      //using the object name I have defined, I will be calling the methods defined in WestminsterMusicStoreManager
            System.out.print("Choose your next Option: ");
            getOption();                        //gets next option from the user until he finally exits from the program
        }
        else if (option == 2){
            manager.delete();
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 3){
            manager.printList();
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 4){
            manager.sort();
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 5){
            launch(args);
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 6){
            manager.buy();
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 7){
            manager.generateReport();
            System.out.print("Choose your next Option: ");
            getOption();
        }
        else if (option == 8){
            manager.exit();
            System.exit(8);
        }
        else {
            System.out.print("You have entered an incorrect option! Please try again: ");
            getOption();
        }
      }
    }

    private static void getOption(){               //here I will be validating the option that the user chooses
        for (;;) {
            try {
                option = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
}