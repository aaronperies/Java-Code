package MusicStorePackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable{

    @FXML
    private TableView<WestminsterMusicStoreManager> table;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Integer> columnItemID;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, String> columnTitle;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, String> columnGenre;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, String> columnArtist;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, String> columnReleaseDate;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Double> columnPrice;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, String> columnItemType;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Double> columnDuration;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Integer> columnSpeed;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Integer> columnDiameter;
    @FXML
    private TableColumn<WestminsterMusicStoreManager, Integer> columnQuantity;

    private ObservableList<WestminsterMusicStoreManager> variableList = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicStore","root","");       //connecting to db

            ResultSet set1 = connect.createStatement().executeQuery("select * from MusicItem;");

            while(set1.next()){
                variableList.add(new WestminsterMusicStoreManager(
                        set1.getInt("itemID"),
                        set1.getString("title"),
                        set1.getString("genre"),
                        set1.getString("artist"),
                        set1.getString("releasedate"),
                        set1.getDouble("price"),
                        set1.getString("itemType"),
                        set1.getDouble("duration"),
                        set1.getInt("speed"),
                        set1.getInt("diameter"),
                        set1.getInt("quantity")));
            }

            columnItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
            columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            columnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
            columnArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
            columnReleaseDate.setCellValueFactory(new PropertyValueFactory<>("releasedate"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            columnItemType.setCellValueFactory(new PropertyValueFactory<>("itemType"));
            columnDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
            columnSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
            columnDiameter.setCellValueFactory(new PropertyValueFactory<>("diameter"));
            columnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

            table.setItems(variableList);
        }
        catch(Exception databaseException){
            databaseException.printStackTrace();
        }

    }
}
