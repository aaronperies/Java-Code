package pojo;

public class CD extends MusicItem {

    private int duration;

    public CD(String itemID, String itemTitle, String itemGenre, String releasedDate, String artist, double price, int duration) {
        super(itemID, itemTitle, itemGenre, releasedDate, artist, price);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString() +
                "duration=" + duration +
                "} " ;
    }
}
