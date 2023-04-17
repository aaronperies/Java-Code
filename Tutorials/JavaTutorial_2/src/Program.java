//like in other programming languages, the import statements appear at the top
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    //this tutorial is to demonstrate the process of getting input from the user
    public static void main(String[] args) {
        //whereas in python you use input or cin in c++, java is a little bit...extra
        System.out.println("Please enter your name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        /*what are try catch blocks?*/
        try {
            //now we'll try printing the obtained input through a print statement
            System.out.println("Hello " + name + ", what is your age?");
            int age = input.nextInt();

            //let's try using conditional execution of code
            if (age < 18){
                System.out.println("You are not an adult yet");
            }
            else{
                System.out.println("You are an adult");
            }
        }catch (InputMismatchException e){
            System.out.println("You entered a forbidden character!");
        }

        System.out.println("Do you want to exit the program?: ");
        char option = input.next().charAt(0);

        if (option == 'y' || option == 'Y'){
            System.out.println("Exiting system...");
        }
    }
}
