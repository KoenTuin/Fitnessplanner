package fitnessplanner.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalScheme implements Initializable {
    @FXML
    private TableView schemeTableView;

    @FXML
    public void schemeTableView(){


//        while (resultSet.next()) {
//            Vhousenumber = resultSet.getString("Vhousenumber");
//            Vcity = resultSet.getString("Vcity");
//            Vzipcode = resultSet.getString("Vzipcode");
//            Hotelname = resultSet.getString("Hotelname");

            //if currentdate -aantaldagen <= to database date than show
//            if (date.compareTo(datadb) <= 0 && returnBagage == isGevonden) {
//                bagagetabel.add(new bagageTabel(BagageID, Date, Time, State,
//                        Labelnumber, Type, Brand, Color1, Color2, Characteristics,
//                        IsReturned, Airport, Location, Initial, Insertion, Surname,
//                        Street, Housenumber, Zipcode, City, Email, Phone1, Phone2,
//                        Flightnumber, From, To, Vstreet, Vhousenumber, Vcity, Vzipcode,
//                        Hotelname));

//                for (int i = 0; i < table.getColumns().size(); i++) {
//                    TableColumn column = (TableColumn) table.getColumns().get(i);
//                    column.setCellValueFactory(new PropertyValueFactory(column.getId()));
//                }
//            }
//        }
//
//        table.setItems(bagagetabel);
    }
    

    @FXML
    public void deleteExercise(){

    }
    @FXML
    public void showExercises(){

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schemeTableView();
    }
}
