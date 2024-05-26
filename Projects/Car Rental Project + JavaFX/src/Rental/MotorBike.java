package Rental;

class MotorBike extends Vehicle {   //type of vehicle
    private String startMode;       //specific attributes of bikes
    private String tyreDisc;

    MotorBike()
    {
        super();
    }       //default constructor

    //constructor with values passed in parameters
    MotorBike(String plateNo, String make, String model, double rate, String seatingCap, String engineType, String cc, String type, String startMode, String tyreDisc) {
        super(plateNo, make, model, rate, seatingCap, engineType, cc, type);
        this.startMode = startMode;
        this.tyreDisc = tyreDisc;
    }

    //getters and setters for attributes of motorbike
    public String getStartMode() {
        return startMode;
    }

    public void setStartMode(String startMode) {
        this.startMode = startMode;
    }

    public String  getTyreDisc() {
        return tyreDisc;
    }

    public void setTyreDisc(String tyreDisc) {
        this.tyreDisc = tyreDisc;
    }

    @Override
    public String toString() {          //toString method to print object with attributes when necessary in understandable format
        return "MotorBike { " +
                "startMode = '" + startMode + '\'' +
                ", tyreDisc = '" + tyreDisc + '\'' +
                '}';
    }
}