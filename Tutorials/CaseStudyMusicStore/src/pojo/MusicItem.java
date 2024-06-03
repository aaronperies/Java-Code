package pojo;

import java.util.Objects;

public abstract class MusicItem implements Comparable<MusicItem>{

    private String itemID;
    private String itemTitle;
    private String itemGenre;
    private String releasedDate;
    private String artist;      // expected is List of artist       private List<Artist> artist
    private double price;       // Expected is BigDecimal

    public MusicItem(String itemID, String itemTitle, String itemGenre, String releasedDate, String artist, double price) {
        this.itemID = itemID;
        this.itemTitle = itemTitle;
        this.itemGenre = itemGenre;
        this.releasedDate = releasedDate;
        this.artist = artist;
        this.price = price;
    }

    // Don't have setter method for key attribute


    public String getItemID() {
        return itemID;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemGenre() {
        return itemGenre;
    }

    public void setItemGenre(String itemGenre) {
        this.itemGenre = itemGenre;
    }

    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "itemID='" + itemID + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemGenre='" + itemGenre + '\'' +
                ", releasedDate='" + releasedDate + '\'' +
                ", artist='" + artist + '\'' +
                ", price=" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicItem)) return false;
        MusicItem musicItem = (MusicItem) o;
        return Double.compare(musicItem.getPrice(), getPrice()) == 0 &&
                getItemID().equals(musicItem.getItemID()) &&
                getItemTitle().equals(musicItem.getItemTitle()) &&
                getItemGenre().equals(musicItem.getItemGenre()) &&
                getReleasedDate().equals(musicItem.getReleasedDate()) &&
                getArtist().equals(musicItem.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemID(), getItemTitle(), getItemGenre(), getReleasedDate(), getArtist(), getPrice());
    }

    @Override
    public int compareTo(MusicItem o) {
        return this.getItemTitle().compareTo(o.getItemTitle());
    }

}
