package MusicStorePackage;

import java.util.InputMismatchException;
import java.util.Scanner;

class Date{             //I'll be creating a Date class to get and set release dates for the Music Items
    private int day;        //as per any date, the variables I'll be utilizing are as shown on the left
    private int month;
    private int year;
    private Scanner input = new Scanner(System.in);

    int getYear() {              //defining the getters and setters for the year
        return year;
    }

    void setYear() {                    //manager can set the release year
        System.out.print("Enter the year (YYYY): ");
        enterYear();

        while (year>2019 || year<1){
            System.out.print("Invalid year! Please enter a valid year (YYYY): ");       //validating year entered
            enterYear();
        }
    }

    int getMonth() {              //defining the getters and setters for the month
        return month;
    }

    void setMonth() {                   //manager can set the release month
        System.out.print("Enter the month (M/MM): ");
        enterMonth();

        while (month<1 || month>12){
            System.out.print("Invalid month! Please enter a valid month (1-12): ");
            enterMonth();
        }
    }

    int getDay() {               //defining the getters and setters for the day
        return day;
    }

    void setDay() {                         //manager can set the release day
        System.out.print("Enter the day (D/DD): ");
        enterDay();

        if(getYear()%4 == 0){               //validating leap year
            if (getMonth() == 1){
                while(day<1 || day>31){     //enabling 31 days for January
                    System.out.print("Invalid day! Please enter a valid day in January (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 2){
                while(day<1 || day>29){     //enabling 29 days for February
                    System.out.print("Invalid day! Please enter a valid day in February (1-29): ");
                    enterDay();
                }
            }
            else if (getMonth() == 3){
                while(day<1 || day>31){     //enabling 31 days for March
                    System.out.print("Invalid day! Please enter a valid day in March (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 4){
                while(day<1 || day>30){     //enabling 30 days for April
                    System.out.print("Invalid day! Please enter a valid day in April (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 5){
                while(day<1 || day>31){     //enabling 31 days for May
                    System.out.print("Invalid day! Please enter a valid day in May (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 6){
                while(day<1 || day>30){     //enabling 30 days for June
                    System.out.print("Invalid day! Please enter a valid day in June (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 7){
                while(day<1 || day>31){     //enabling 31 days for July
                    System.out.print("Invalid day! Please enter a valid day in July (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 8){
                while(day<1 || day>31){     //enabling 31 days for August
                    System.out.print("Invalid day! Please enter a valid day in August (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 9){
                while(day<1 || day>30){     //enabling 30 days for September
                    System.out.print("Invalid day! Please enter a valid day in September (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 10){
                while(day<1 || day>31){     //enabling 31 days for October
                    System.out.print("Invalid day! Please enter a valid day in October (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 11){
                while(day<1 || day>30){     //enabling 30 days for November
                    System.out.print("Invalid day! Please enter a valid day in November (1-30): ");
                    enterDay();
                }
            }
            else {
                while(day<1 || day>31){     //enabling 31 days for December
                    System.out.print("Invalid day! Please enter a valid day in December (1-31): ");
                    enterDay();
                }
            }
        }
        else if(getYear()%4 != 0){          //if not a leap year, February will have 28 days, other months are normal
            if (getMonth() == 1){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in January (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 2){
                while(day<1 || day>28){
                    System.out.print("Invalid day! Please enter a valid day in February (1-28): ");
                    enterDay();
                }
            }
            else if (getMonth() == 3){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in March (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 4){
                while(day<1 || day>30){
                    System.out.print("Invalid day! Please enter a valid day in April (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 5){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in May (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 6){
                while(day<1 || day>30){
                    System.out.print("Invalid day! Please enter a valid day in June (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 7){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in July (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 8){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in August (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 9){
                while(day<1 || day>30){
                    System.out.print("Invalid day! Please enter a valid day in September (1-30): ");
                    enterDay();
                }
            }
            else if (getMonth() == 10){
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in October (1-31): ");
                    enterDay();
                }
            }
            else if (getMonth() == 11){
                while(day<1 || day>30){
                    System.out.print("Invalid day! Please enter a valid day in November (1-30): ");
                    enterDay();
                }
            }
            else {
                while(day<1 || day>31){
                    System.out.print("Invalid day! Please enter a valid day in December (1-31): ");
                    enterDay();
                }
            }
        }
    }
    private void enterDay(){                //validating the day data type
        for (;;) {
            try {
                day = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
    private void enterMonth(){                //validating the month data type
        for (;;) {
            try {
                month = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
    private void enterYear(){                //validating the year data type
        for (;;) {
            try {
                year = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }
}