package MusicStorePackage;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Comparator;

//class that implements the interface 'Store Manager'
class WestminsterMusicStoreManager implements StoreManager{

    private ArrayList<WestminsterMusicStoreManager> printItems = new ArrayList<>();
    private ArrayList<WestminsterMusicStoreManager> cart = new ArrayList<>();               //array list to store the items bought

    @Override                       //displays the objects(records) obtained from the database in string format when prompted to
    public String toString(){
        return "ItemID: " + this.itemID + " , Title: " + this.title + " , Price: " + this.price + " , Type: " + this.type;
    }

    private Scanner input = new Scanner(System.in);         //initializing an object of scanner class to get input
    private CD variableAccess = new CD();                   //creating the objects of class 'CD' to access class variables inherited from class 'Music Item'
    private Vinyl methodAccess = new Vinyl();               //creating object of Vinyl class to access methods
    private Date setDate = new Date();                      //creating object of date class to set date

    private int countRecords;                               //assigning the number of records already entered in the database
    private int itemID = variableAccess.itemID;             //assigning the attributes in the database columns
    private String title = variableAccess.title;
    private String genre = variableAccess.genre;
    private String artist = variableAccess.artist;
    private String releaseDate = variableAccess.releaseDate;
    private double price = variableAccess.price;
    private String type = variableAccess.type;
    private double duration = variableAccess.getDuration();
    private int speed = methodAccess.getSpeed();
    private int diameter = methodAccess.getDiameter();
    private int quantity = methodAccess.quantity;

    //this constructor is for use with the database in order to display the items inside the TableView
    WestminsterMusicStoreManager(int itemID, String title, String genre, String artist, String releasedate, double price, String itemType, double duration, int speed, int diameter, int quantity) {
        this.itemID=itemID;
        this.title=title;
        this.genre=genre;
        this.artist=artist;
        this.releaseDate=releasedate;
        this.price=price;
        this.type=itemType;
        this.duration=duration;
        this.speed=speed;
        this.diameter=diameter;
        this.quantity=quantity;
    }

    //Constructor used for printing the list of items with relevant columns in printList function, sort function and generate report function.
    private WestminsterMusicStoreManager(int itemID, String title, double price, String itemType) {
        this.itemID = itemID;
        this.title = title;
        this.price = price;
        this.type = itemType;
    }

    private String getTitle() {                 //creating getters for the variables obtained through subclass CD from superclass MusicItem
        return title;
    }

    private String getGenre() {
        return genre;
    }

    private String getReleaseDate() {
        return releaseDate;
    }

    private double getPrice(){
        return price;
    }

    private String getType() {
        return type;
    }

    private String getArtist() {
        return artist;
    }

    private int getQuantity(){
        return quantity;
    }

    @Override                                   //defining the method bodies that were obtained from the Interface (no unsupported methods)
    public void add(){                          //setting restrictions on the number of items that can be entered into the database.
        if (countRecords == 1000) {
            System.out.println("Maximum number of items have already been entered.");
        }else {
           System.out.println("Do you want to add a CD or a Vinyl? (c/v): ");
            char choice = input.next().charAt(0);
            input.nextLine();

            while (choice != 'c' && choice != 'C' && choice != 'v' && choice != 'V') {
                System.out.println("You have entered an incorrect character operation, please try again!");
                System.out.println("Do you want to add a CD or a Vinyl? (c/v): ");
                choice = input.next().charAt(0);
                input.nextLine();
            }

            if (choice == 'c' || choice == 'C') {
                System.out.println("You chose to add a CD to the store.");
                System.out.println(" ");
                System.out.print("Enter the title of the CD: ");
                    title = input.nextLine();
                System.out.print("Enter the genre of the CD: ");
                    genre = input.nextLine();
                System.out.print("Enter the artist: ");
                    artist = input.nextLine();
                System.out.println("Enter the date when the CD was first released");
                    setDate.setYear();
                    setDate.setMonth();
                    setDate.setDay();
                System.out.print("Enter the price of the CD: ");
                    enterPrice();
                System.out.print("Enter the duration of the CD (MM.SS): ");
                    variableAccess.setDuration();
                    type = "CD";
                    releaseDate = setDate.getYear() + "-" + setDate.getMonth()+ "-" + setDate.getDay();
                    quantity = 10;              //default value is 10 in stock
                System.out.println(" ");
                insertItems();
                System.out.println("You have successfully added a new CD!");
                System.out.println(" ");
            }
            else {
                System.out.println("You chose to add a Vinyl to the store.");
                System.out.println(" ");
                System.out.print("Enter the title of the Vinyl Record: ");
                    title = input.nextLine();
                System.out.print("Enter the genre of the Vinyl: ");
                    genre = input.nextLine();
                System.out.print("Enter the artist: ");
                    artist = input.nextLine();
                System.out.println("Enter the date when the Vinyl was first released");
                    setDate.setYear();
                    setDate.setMonth();
                    setDate.setDay();
                System.out.print("Enter the price of the Vinyl: ");
                    enterPrice();
                System.out.print("Enter the speed of the record (33, 45 & 78 RPM): ");
                    methodAccess.setSpeed();
                System.out.print("Enter the diameter of the record (10, 12 INCH): ");
                    methodAccess.setDiameter();
                    type = "Vinyl";
                    releaseDate = setDate.getYear() + "-" + setDate.getMonth()+ "-" + setDate.getDay();
                    quantity = 10;            //default value is 10 in stock
                System.out.println(" ");
                insertItems();
                System.out.println("You have successfully added a new Vinyl record!");
                System.out.println(" ");
            }
            addCount();
        }
    }

    @Override
    public void delete(){
        System.out.println("You chose to delete an item.");
        System.out.print("Enter ID of item: ");
        enterID();                          //getting user input for ID of Item to be deleted
        deleteItems();                      //utilizing method that removes item from the database
        System.out.println(" ");

        displayCount();                    //this method will be invoked after deleting the item from the database or else count will be incorrect
        System.out.println(" ");
    }

    @Override
    public void printList(){
        System.out.println("You chose to print the list of items.");
        System.out.println(" ");
        getItems();
        System.out.println(" ");
    }

    @Override
    public void sort(){
        System.out.println("You chose to sort the list of items.");
        System.out.println(" ");
        printItems.sort(new Comparator<WestminsterMusicStoreManager>() {
            @Override
            public int compare(WestminsterMusicStoreManager title1, WestminsterMusicStoreManager title2) {
                return title1.getTitle().compareToIgnoreCase(title2.getTitle());
            }
        });
        for (WestminsterMusicStoreManager record: printItems){
            System.out.println(record);
        }
        System.out.println(" ");
    }

    @Override
    public void buy(){
        System.out.print("Enter the ID of the item you wish to purchase: ");
        enterID();
        System.out.print("How many copies do you wish to buy (max. 5): ");  /*I'll be setting a limit to the number of items a customer can buy as default quantity is 10*/
        enterQuantity();
        while(quantity > 5 || quantity <1){
            System.out.print("You have entered an invalid quantity! Please choose a valid quantity! (max. 5): ");
            enterQuantity();
        }
        System.out.println(" ");
        buyItems();
        System.out.println(" ");
    }

    @Override
    public void generateReport(){                                       //How to perform File handling method was referenced from https://www.programcreek.com/2011/03/java-write-to-a-file-code-example/
        System.out.println("Generating report...");
        try{
            File report = new File("SalesReport.txt");
            FileOutputStream reportStream = new FileOutputStream(report);
            BufferedWriter reportWriter = new BufferedWriter(new OutputStreamWriter(reportStream));
            reportWriter.write("Shown below is a Sales Report of the Store");
            reportWriter.newLine();
            reportWriter.write(" ");
            reportWriter.newLine();

            for (WestminsterMusicStoreManager line: cart){
                reportWriter.write(String.valueOf(line));
                reportWriter.newLine();
            }
            reportWriter.close();
            System.out.println("Report Complete, please check directory");
            System.out.println(" ");
        }
        catch(IOException exceptionInWritingFile){
            exceptionInWritingFile.printStackTrace();
        }
    }

    @Override
    public void exit(){
        System.out.println("You have exited from the store. Please come again!");
    }

    void menu (){                //method displays menu for user or manager to choose option
        System.out.println("Welcome to the Westminster Music Store!");
        System.out.println("Options:");
        System.out.println("    1. Add a new item.");
        System.out.println("    2. Delete an item.");
        System.out.println("    3. Print list of items.");
        System.out.println("    4. Sort the items.");
        System.out.println("    5. View all available items");
        System.out.println("    6. Buy an item.");
        System.out.println("    7. Generate report.");
        System.out.println("    8. Exit store.");
        System.out.println();
        System.out.print("Please choose what you wish to do: ");
    }

    private void enterID(){                 //validating whether the ID entered is of correct variable type
        for (;;){
            try {
                itemID = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }

    private void enterQuantity(){                 //validating whether the quantity entered is of correct variable type
        for (;;){
            try {
                quantity = input.nextInt();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-integer input! Please try again: ");
                input.nextLine();
            }
        }
    }

    private void enterPrice(){              //validating whether the price entered is of correct variable type
        for (;;){
            try {
                price = input.nextDouble();
                break;
            } catch (InputMismatchException incorrectInput) {
                System.out.print("You have entered a non-numerical input! Please try again: ");
                input.nextLine();
            }
        }
    }

    //here I will begin creating the methods to implement the database actions such as inserting, deleting records etc.

    private void addCount(){
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore","root","");       //connecting to db

            Statement query = connect.createStatement();

            ResultSet rs1;                  //to assign the count obtained I referenced a method used in https://stackoverflow.com/questions/5794186/assigning-select-count-query-result-to-a-java-variable

            rs1 = query.executeQuery("SELECT COUNT(itemID) AS recordCount FROM MusicItem;");

            if (rs1.next())
                countRecords = rs1.getInt(1);
            System.out.println(countRecords + " items have already been entered, you can add another " + (1000-countRecords) + " items");
        }
        catch(Exception databaseException){
            databaseException.printStackTrace();
        }
    }

    private void displayCount(){
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore","root","");       //connecting to db

            Statement query = connect.createStatement();

            ResultSet rs1;                  //to assign the count obtained I referenced a method used in https://stackoverflow.com/questions/5794186/assigning-select-count-query-result-to-a-java-variable

            rs1 = query.executeQuery("SELECT COUNT(itemID) AS recordCount FROM MusicItem;");

            if (rs1.next())
                countRecords = rs1.getInt(1);
            System.out.println((1000-countRecords) + " items are remaining in the database");
        }
        catch(Exception databaseException){
            databaseException.printStackTrace();
        }
    }

    private void insertItems(){           //I will be using this method to add 10 default items into the database as a startup amount
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore","root","");       //connecting to db

            String addItem = "insert into MusicItem (title, genre, artist, releasedate, price, itemType, duration, speed, diameter, quantity)" + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement dataEntry = connect.prepareStatement(addItem);
            dataEntry.setString(1, getTitle());
            dataEntry.setString(2, getGenre());
            dataEntry.setString(3, getArtist());
            dataEntry.setString(4, getReleaseDate());
            dataEntry.setDouble(5, getPrice());
            dataEntry.setString(6, getType());
            dataEntry.setDouble(7, variableAccess.getDuration());
            dataEntry.setInt   (8, methodAccess.getSpeed());
            dataEntry.setInt   (9, methodAccess.getDiameter());
            dataEntry.setInt   (10, getQuantity());

            dataEntry.execute();

            System.out.println("Insert Complete");
        }
        catch(Exception databaseException){
            databaseException.printStackTrace();
        }
    }

    private void deleteItems(){
        String deletedItemType = "";
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore","root","");       //connecting to db

            Statement query = connect.createStatement();

            ResultSet deletedType;
            deletedType = query.executeQuery("select itemType from MusicItem where itemID=" + itemID);

            if (deletedType.next()){
                deletedItemType = deletedType.getString(1);
            }

            String deleteItem = "delete from MusicItem where itemID=" + itemID;
            int rowsAffected = query.executeUpdate(deleteItem);

            System.out.println("Delete Complete");
            System.out.println("The type of item you deleted was " + deletedItemType);
        }
        catch(Exception databaseException){
            databaseException.printStackTrace();
        }
    }

    private void getItems() {               //this database method will be used to add the list of all the items into an array list
            try {
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "");       //connecting to db
                Statement query = connect.createStatement();
                String print = "select itemID, title, price, itemType from MusicItem";
                ResultSet listOfItems = query.executeQuery(print);

                while (listOfItems.next()){
                    WestminsterMusicStoreManager listItems = new WestminsterMusicStoreManager(listOfItems.getInt("itemID"),
                            listOfItems.getString("title"), listOfItems.getDouble("price"), listOfItems.getString("itemType"));
                    printItems.add(listItems);
                    System.out.println(listItems);
                }

            } catch (Exception databaseException) {
                databaseException.printStackTrace();
            }
        }

    private void buyItems(){                //this is the db method for purchasing items from the store and adding items to array list
        double totalBill = 0;
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore", "root", "");       //connecting to db
            Statement query = connect.createStatement();
            Statement queryTwo = connect.createStatement();

            String cartItems = "select itemID, title, price, itemType from MusicItem where itemID = " + itemID;
            ResultSet soldItems = query.executeQuery(cartItems);        //with this result set I will be adding all the items bought into an Array List called cart

            ResultSet finalBill = queryTwo.executeQuery("select (price * " + quantity + ") from MusicItem where itemID = " + itemID);          //with this result set I will be getting the total bill for the quantity I ordered in the store

            if(finalBill.next()){
                totalBill = finalBill.getDouble(1);
            }

            while(soldItems.next()){
                WestminsterMusicStoreManager sold = new WestminsterMusicStoreManager(soldItems.getInt("itemID"),
                        soldItems.getString("title"), soldItems.getDouble("price"), soldItems.getString("itemType"));
                cart.add(sold);
            }

            String removePurchased = "update MusicItem set quantity = 10-" + quantity + " where itemID = " + itemID;        //with this query I will be deducting the stock available
            int rowsAffected = query.executeUpdate(removePurchased);

            System.out.println("Your final bill is " + totalBill);
            System.out.println("Purchase Complete! Thank you for purchasing from Westminster Music Store!");

        }catch (Exception databaseException){
            databaseException.printStackTrace();
        }
    }
}