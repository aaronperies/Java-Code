package MusicStorePackage;

import java.util.InputMismatchException;
import java.util.Scanner;

class Vinyl extends MusicItem{
    private int speed;   //33 45 78 RPM are standard speeds of Vinyl records
    private int diameter;   //12 10 INCHES are standard disc diameters of Vinyl records
    private Scanner input = new Scanner(System.in);

    int getSpeed() {
        return speed;
    }       //generating getters and setters for speed

    void setSpeed() {
        enterSpeed();

        while (speed != 33 && speed != 45 && speed != 78){
            System.out.print("Invalid speed! Please enter a valid speed: ");
            enterSpeed();
        }
    }

    int getDiameter() {
        return diameter;
    }       //generating getters and setters for diameter

    void setDiameter() {
        enterDiameter();

        while (diameter != 10 && diameter != 12){
            System.out.print("Invalid diameter! Please enter a valid diameter: ");
            enterDiameter();
        }
    }

    private void enterSpeed(){          //validating input received for speed
        for (;;) {
            try {
                speed = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }

    private void enterDiameter(){       //validating input received for diameter
        for (;;) {
            try {
                diameter = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
}
