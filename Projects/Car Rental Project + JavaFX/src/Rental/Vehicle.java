package Rental;

abstract class Vehicle {    //superclass of car and motorbike
    private String plateNo; //although some of these attributes consist only of numbers I chose to use String because no calculations
    private String make;    //are done on them
    private String model;
    private double rate;
    private String seatingCap;
    private String engineType;
    private String cc;
    private String type;

    Vehicle(){
        super();
    }

    Vehicle(String plateNo, String make, String model, double rate, String seatingCap, String engineType, String cc, String type) {
        super();
        this.plateNo = plateNo;
        this.make = make;
        this.model = model;
        this.rate = rate;
        this.seatingCap = seatingCap;
        this.engineType = engineType;
        this.cc = cc;
        this.type = type;
    }

    public String getPlateNo() {
        return plateNo;
    }

    void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getMake() {
        return make;
    }

    void setMake(String make) {
        this.make = make;
    }

    String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRate() {
        return rate;
    }

    void setRate(double rate) {
        this.rate = rate;
    }

    public String getSeatingCap() {
        return seatingCap;
    }

    void setSeatingCap(String seatingCap) {
        this.seatingCap = seatingCap;
    }

    public String getEngineType() {
        return engineType;
    }

    void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getCc() {
        return cc;
    }

    void setCc(String cc) {
        this.cc = cc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                " plateNo = '" + plateNo + '\'' +
                ", make = '" + make + '\'' +
                ", rate = " + rate +
                ", seatingCap = '" + seatingCap + '\'' +
                ", engineType = '" + engineType + '\'' +
                ", cc = '" + cc + '\'' +
                ", type = '" + type + '\'' +
                '}';
    }
}