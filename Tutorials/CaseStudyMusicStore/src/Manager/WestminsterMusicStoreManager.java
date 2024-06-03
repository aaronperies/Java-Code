package Manager;

import comparator.IDComparator;
import pojo.MusicItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class WestminsterMusicStoreManager implements StoreManager {

    private static final int MAX_SIZE = 1000;
    private List<MusicItem> itemList = new ArrayList<MusicItem>();
    private List<MusicItem> soldItem = new ArrayList<MusicItem>();

    @Override
    public void addItem(MusicItem musicItem) {
        if (itemList.size() < MAX_SIZE){
            itemList.add(musicItem);
        }
        else {
            System.out.println("Maximum size is attained");
        }

    }

    @Override
    public void deleteItem(MusicItem musicItem) {


    }

    @Override
    public void deleteItem(String itemID) {
        boolean found = false;
        for (MusicItem item:  itemList) {

            if (item.getItemID().equals(itemID)){
                itemList.remove(item);
                found = true;
                break;
            }

        }
        if (!found){
            System.out.println("Item not found!!...");
        }

    }

    @Override
    public void print() {
        for (MusicItem item:  itemList) {
            System.out.printf("|   ItemID   |   Item Title   |");
            System.out.println(item);
        }
    }

    @Override
    public void sort() {
//         Collection  - interface   ---->
//        Collections - Utility Class
//        collections  - Java Collection Framework

        Collections.sort(itemList);   // For item Title
        Collections.sort(itemList, new IDComparator());     //For itemID
    }

    @Override
    public void buy(String itemId) {
        soldItem.clear();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of item to buy : ");
        int noItem = sc.nextInt();
        int count = 0;

        while (count < noItem) {
            System.out.println("Enter the item number : ");
            String itemNo = sc.next();
            boolean found = false;

            for (MusicItem item : itemList) {
                if (item.getItemID().equals(itemNo)) {
                    soldItem.add(item);
                    deleteItem(itemNo);
                    found = true;
                    break;
                }
            }
            if (found) {
                count++;
            }
        }
        generateReport();
    }

    @Override
    public void generateReport() {
        File file = new File("SoldItem.txt");
        try(PrintWriter pw = new PrintWriter(new FileWriter(file, true), true)) {
            for (MusicItem item : soldItem) {
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Calendar calobj = Calendar.getInstance();

                pw.println(item.getItemTitle() + " : " + item.getItemID() + " : " + item.getPrice()
                        + df.format(calobj.getTime()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
