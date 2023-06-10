package MusicStorePackage;

//creating interface for implementation done by WestminsterStoreManager
interface StoreManager {
    void add();                 //contains all methods that can be used by classes that implement StoreManager interface
    void delete();              //all methods implemented by classes will be overwritten
    void printList();
    void sort();
    void buy();
    void generateReport();
    void exit();
}
