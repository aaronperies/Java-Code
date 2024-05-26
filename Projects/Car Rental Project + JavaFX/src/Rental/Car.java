package Rental;

class Car extends Vehicle {     //type of vehicle
    private String doorNo;      //cars have doors while bikes don't
    private String assistanceType;  //cars provide all sorts of driver assistance features such as hands free driving

    Car() {
        super();
    }   //default constructor

    //constructor with values to be passed as parameters
    Car(String plateNo, String make, String model, double rate, String seatingCap, String engineType, String cc, String type, String doorNo, String assistanceType) {
        super(plateNo, make, model, rate, seatingCap, engineType, cc, type);
        this.doorNo = doorNo;
        this.assistanceType = assistanceType;
    }

    //getters and setters for the attributes of car
    public String getDoorNo() {
        return doorNo;
    }

    void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    @Override
    public String toString() {      //toString method to print object with attributes when necessary in understandable format
        return "Car{ " +
                "doorNo = '" + doorNo + '\'' +
                ", assistanceType = '" + assistanceType + '\'' +
                '}';
    }
}