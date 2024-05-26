package Rental;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class VehicleDatabaseModel {                     //this class is used as a model for vehicle objects obtained from the database
    private final SimpleStringProperty plateNo;         //this class will have the attributes of vehicle+car+motorbike
    private final SimpleStringProperty make;            //the simple****property is used because that is the format of the data when we insert them
    private final SimpleStringProperty model;           //thus, the data has to be retrieved in the same format and displayed
    private final SimpleDoubleProperty rate;
    private final SimpleStringProperty seatingCap;
    private final SimpleStringProperty engine;
    private final SimpleStringProperty cc;
    private final SimpleStringProperty doorNo;
    private final SimpleStringProperty assistance;
    private final SimpleStringProperty start;
    private final SimpleStringProperty disc;
    private final SimpleStringProperty type;

    //constructor for the class so that we can place values for them which are retrieved from the db
    VehicleDatabaseModel(String plateNo, String make, String model, double rate, String seatingCap, String engine, String cc, String doorNo, String assistance, String start, String disc, String type) {
        this.plateNo = new SimpleStringProperty(plateNo);
        this.make = new SimpleStringProperty(make);
        this.model = new SimpleStringProperty(model);
        this.rate = new SimpleDoubleProperty(rate);
        this.seatingCap = new SimpleStringProperty(seatingCap);
        this.engine = new SimpleStringProperty(engine);
        this.cc = new SimpleStringProperty(cc);
        this.doorNo = new SimpleStringProperty(doorNo);
        this.assistance = new SimpleStringProperty(assistance);
        this.start = new SimpleStringProperty(start);
        this.disc = new SimpleStringProperty(disc);
        this.type = new SimpleStringProperty(type);
    }
    //getters and setters are generated, even though they aren't used explicitly, they are called for implicitly
    public String getPlateNo() {
        return plateNo.get();
    }

    public SimpleStringProperty plateNoProperty() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo.set(plateNo);
    }

    public String getMake() {
        return make.get();
    }

    public SimpleStringProperty makeProperty() {
        return make;
    }

    public void setMake(String make) {
        this.make.set(make);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public double getRate() {
        return rate.get();
    }

    public SimpleDoubleProperty rateProperty() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate.set(rate);
    }

    public String getSeatingCap() {
        return seatingCap.get();
    }

    public SimpleStringProperty seatingCapProperty() {
        return seatingCap;
    }

    public void setSeatingCap(String seatingCap) {
        this.seatingCap.set(seatingCap);
    }

    public String getEngine() {
        return engine.get();
    }

    public SimpleStringProperty engineProperty() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine.set(engine);
    }

    public String getCc() {
        return cc.get();
    }

    public SimpleStringProperty ccProperty() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc.set(cc);
    }

    public String getDoorNo() {
        return doorNo.get();
    }

    public SimpleStringProperty doorNoProperty() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo.set(doorNo);
    }

    public String getAssistance() {
        return assistance.get();
    }

    public SimpleStringProperty assistanceProperty() {
        return assistance;
    }

    public void setAssistance(String assistance) {
        this.assistance.set(assistance);
    }

    public String getStart() {
        return start.get();
    }

    public SimpleStringProperty startProperty() {
        return start;
    }

    public void setStart(String start) {
        this.start.set(start);
    }

    public String getDisc() {
        return disc.get();
    }

    public SimpleStringProperty discProperty() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc.set(disc);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}