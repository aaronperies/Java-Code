package MusicStorePackage;

import java.util.InputMismatchException;
import java.util.Scanner;

class CD extends MusicItem {
    private double duration; //MM.SS            //class specific attribute
    private Scanner input = new Scanner(System.in);

    double getDuration() {               //creating getter and setter methods for the manager to set the duration of the CD
        return duration;
    }

    void setDuration() {
        enterDuration();
    }

    private void enterDuration(){               //providing validation for the duration
        for (;;) {
            try {
                duration = input.nextDouble();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-numerical input! Please try again: ");
                input.nextLine();
            }
        }
    }
}
