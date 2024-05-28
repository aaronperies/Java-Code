import java.util.InputMismatchException;
import java.util.Scanner;

//implementing interface functions here to hide the inner processes of methods
public class Calculator implements Functions{

    //overriding the method in interface
    @Override
    public double Add(double x, double y) {
        return x + y;
    }

    @Override
    public double Divide(double x, double y) {
        return x / y;
    }

    @Override
    public double Subtract(double x, double y) {
        return x - y;
    }

    @Override
    public double Multiply(double x, double y) {
        return x * y;
    }

    /*this menu will be displayed each time a process is completed
    * or if there is an error or forbidden character entered*/
    public static void DisplayMenu(){
        System.out.println("Choose an Operation: ");
        System.out.println("1 - Addition");
        System.out.println("2 - Subtraction");
        System.out.println("3 - Multiplication");
        System.out.println("4 - Division");
        System.out.println("5 - Exit Program");
    }

    /*this method will validate all the input that CAN be entered
    so that there are no input mismatch exceptions*/
    public int ValidateInput(){
        Scanner input = new Scanner(System.in);
        try {
            return input.nextInt();
        }catch (InputMismatchException ime){
            System.out.println("You entered a forbidden character! Try again: ");
            return 0;
        }
    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
        int choice;

        //do while loops are helpful when there is a single condition to look out for
        do {
            DisplayMenu();
            System.out.println("Operation: ");
            choice = cal.ValidateInput();
            int x,y;

            switch (choice) {
                case 1 -> {
                    System.out.println("Please enter num 1: ");
                    x = cal.ValidateInput();
                    System.out.println("Please enter num 2: ");
                    y = cal.ValidateInput();
                    System.out.println(cal.Add(x, y));
                }
                case 2 -> {
                    System.out.println("Please enter num 1: ");
                    x = cal.ValidateInput();
                    System.out.println("Please enter num 2: ");
                    y = cal.ValidateInput();
                    System.out.println(cal.Subtract(x, y));
                }
                case 3 -> {
                    System.out.println("Please enter num 1: ");
                    x = cal.ValidateInput();
                    System.out.println("Please enter num 2: ");
                    y = cal.ValidateInput();
                    System.out.println(cal.Multiply(x, y));
                }
                case 4 -> {
                    System.out.println("Please enter num 1: ");
                    x = cal.ValidateInput();
                    System.out.println("Please enter num 2: ");
                    y = cal.ValidateInput();
                    System.out.println(cal.Divide(x, y));
                }
                //these cases below don't have {} wrappers due to consisting of only 1 line of code
                case 5 -> System.out.println("Exiting Program...");
                default -> System.out.println("You have entered an incorrect choice!");
            }
        }while (choice != 5);
        //program will exit once the while condition is not satisfied
    }
}