package pojo;

public class Vinyl extends MusicItem {

    private int speed;
    private double diameter;

    public Vinyl(String itemID, String itemTitle, String itemGenre, String releasedDate, String artist, double price, int speed, double diameter) {
        super(itemID, itemTitle, itemGenre, releasedDate, artist, price);
        this.speed = speed;
        this.diameter = diameter;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return super.toString()+
                "speed=" + speed +
                ", diameter=" + diameter;
    }
}
