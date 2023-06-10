package MusicStorePackage;

//I will be creating the class for the Music Items - super class of CD and Vinyl
abstract class MusicItem{
    int itemID;             //declaring variables for attributes of the Music Items
    String title;           //getters will be generated in the class that utilizes these attributes
    String genre;
    String artist;
    String releaseDate;
    double price;
    String type;
    int quantity;
}
