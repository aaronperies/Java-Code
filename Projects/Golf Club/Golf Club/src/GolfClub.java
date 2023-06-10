/*Name - Aaron Peries
* Student ID - 2018414
* UoW ID - w1736298*/

import java.util.*;
import java.lang.*;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.*;

public class GolfClub {
    static int userResponse;                                           /*variable to get the option that the user chooses*/
    static Scanner scan = new Scanner(System.in);                       /*I will be initializing Scanner class*/
    static int result;                                                  /*variable to get the result from the user*/
    static int noGolfer;                                                /*variable to get the number of golfers the user decides to enter*/

    public static void main(String[] args) {
        HashMap<String, Integer> enterScores = new HashMap<String, Integer>();      /*declare hashmap in which I will store the scores*/
        HashMap<String, Integer> restore = new HashMap<>();            /*This hashmap will be used to store the deleted records*/
        String verify = null;

        menu();
        input();
        while (userResponse == (int)userResponse) {
                if (userResponse == 1) {
                    System.out.println("How many Golfers are in the group?");
                    NoOfGolfers();

                    for (int i = 0; i < noGolfer; i++) {                                    /*For the number of golfers mentioned the loop will iterate till the names and scores of all the golfers are entered*/
                        System.out.println("Enter the name: ");
                        String name = scan.next();
                        boolean keyPresent = enterScores.containsKey(name);
                        if (keyPresent == true) {                                           /*Checking if the name is already present in the hashmap*/
                            System.out.println("The name is already in the database, do you want to update your result or keep the existing one?");
                            verify = scan.next();
                            if (verify.equals("Yes") || verify.equals("yes")) {
                                System.out.println("Enter the score: ");
                                Result();

                                if (result > 17 && result < 109) {
                                    enterScores.put(name, result);
                                    System.out.println(enterScores);
                                    System.out.println("Your data has been saved");
                                    System.out.print("Please choose your next Option: ");
                                    input();
                                }
                                else {
                                    System.out.print("The score you entered is invalid, please try again: ");
                                    result = scan.nextInt();
                                    enterScores.put(name, result);
                                }
                            }
                        }   else if (verify == "No" || verify == "no") {
                            input();
                        }

                        System.out.println("Enter the score: ");
                        Result();

                        if (result > 17 && result < 109) {
                            enterScores.put(name, result);
                        } else {
                            System.out.print("The score you entered is invalid, please try again: ");
                            result = scan.nextInt();
                            enterScores.put(name, result);
                        }
                    }
                    System.out.println(enterScores);
                    System.out.println("Your data has been saved");
                    System.out.println("Please choose your next Option: ");
                    input();

                } else if (userResponse == 2) {                                         /*The user can check his/her score from the database*/
                    System.out.println("Enter the name of the golfer: ");
                    String name = scan.next();
                    boolean golferPresent = enterScores.containsKey(name);
                    if (golferPresent == true) {
                        System.out.println(enterScores.get(name));
                    } else {
                        System.out.println("The name you entered is not in the system");
                    }
                    System.out.println("Please choose your next Option: ");
                    input();

                } else if (userResponse == 3) {                                         /*The user will be able to see the entire scoreboard in a sorted manner from greatest score to largest*/
                    /*Sorting the hashmap by using the results I got as inputs*/
                    /*The sorting function for a hashmap is referenced from https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html*/
                    Map<String, Integer> scoreBoard = enterScores
                            .entrySet()
                            .stream()
                            .sorted(comparingByValue())
                            .collect(
                                    toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                            LinkedHashMap::new));

                    System.out.println(scoreBoard);
                    System.out.println("Please choose your next Option: ");
                    input();

                }else if (userResponse == 4) {
                    /*Here I will be enabling the user to delete his score from the database*/
                    System.out.print("Name of Golfer? ");
                    String name = scan.next();
                    if (enterScores.containsKey(name)) {
                        int deletedScore = enterScores.get(name);
                        restore.put(name, deletedScore);
                        enterScores.remove(name);
                        System.out.println(enterScores);
                        System.out.print("Choose your next Option: ");
                        input();
                    } else {
                        System.out.println("The name you entered is not in the database.");
                        System.out.print("Choose your next Option: ");
                        input();
                    }

                } else if (userResponse == 5){
                    /*Here I will be providing the user with the ability to restore his name back into the database*/
                    System.out.print("Name of Golfer? ");
                    String name = scan.next();
                    if (restore.containsKey(name)){
                        int deletedScore = restore.get(name);
                        enterScores.put(name, deletedScore);
                        System.out.println(enterScores);
                        System.out.print("Choose your next Option: ");
                        input();
                    }else{
                        System.out.println("The name you entered is not present.");
                        System.out.print("Choose your next Option: ");
                        input();
                    }

                } else if (userResponse == 6) {
                    /*If the user decides to exit the program without doing any other operations,
                     * this condition will exit the program*/
                    System.out.println("You have exited the program.");
                    break;
                }
                /*In the case that an incorrect response is recorded the message will be shown*/
                else {
                    System.out.print("You have entered an incorrect Option, please try again: ");
                    input();
                }
            }
    }
        private static void menu(){
            System.out.println();
            System.out.println("Welcome to Springfield Golf Club.");        /*At this point I will be defining the menu that is to be printed*/
            System.out.println();
            System.out.println("Select Option:");
            System.out.println("    1) Enter Scores");
            System.out.println("    2) Find Golfer");
            System.out.println("    3) Display Scoreboard");
            System.out.println("    4) Delete Golfer");
            System.out.println("    5) Restore Golfer");
            System.out.println("    6) Exit Program");
            System.out.println();
            System.out.print("Choose an option from the ones provided above: ");
        }
        private static void input() {                                                           /*I will be using this class to validate the option that the user enters*/
            boolean done = false;
            while (!done)
                try {
                    userResponse = scan.nextInt();                                               /*Here I will be reading in an input from the user for option*/
                    done = true;
                } catch (InputMismatchException notInt) {
                    System.out.println("You have entered a non-integer value, please try again.");
                    scan.nextLine();
                }
        }
        private static void Result() {                                                           /*I will be using this class to validate the score that the user enters*/
            boolean done = false;
            while (!done)
                try {
                    result = scan.nextInt();                                              /*Here I will be reading in a score from the user*/
                    done = true;
                } catch (InputMismatchException notInt) {
                    System.out.println("You have entered a non-integer value, please try again.");
                    scan.nextLine();
                }
        }
        private static void NoOfGolfers(){
            boolean done = false;
            while (!done)
                try {
                    noGolfer = scan.nextInt();                                              /*Here I will be reading in the number of Golfers from the user*/
                    done = true;
                } catch (InputMismatchException notInt) {
                    System.out.println("You have entered a non-integer value, please try again.");
                    scan.nextLine();
                }
        }
}



