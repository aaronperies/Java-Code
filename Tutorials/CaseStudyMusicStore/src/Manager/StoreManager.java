package Manager;

import pojo.MusicItem;

public interface StoreManager {
    void addItem(MusicItem musicItem);
    void deleteItem(MusicItem musicItem);
    void deleteItem(String itemID);
    void print();
    void sort();
    void buy(String itemID);
    void generateReport();


}
