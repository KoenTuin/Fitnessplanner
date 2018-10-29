package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Exercises;
import models.PersonalExercisesList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonalScheme implements Initializable {
    @FXML
    private TableView<Exercises> schemeTableView;

    @FXML
    private HBox setTable;

    @FXML
    private AnchorPane personalScreen;

    PersonalExercisesList personalListOfExercises = PersonalExercisesList.getPersonalExerciselists();

    ObservableList<Exercises> personalExercises = FXCollections.observableArrayList(personalListOfExercises.PersonalListOfExercises);

//    public ObservableList<Exercises> getPersonalExercises(){
//
//
//    }

    @FXML
    public void schemeTableView(){

        TableColumn<Exercises, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("exercisesName"));

        TableColumn<Exercises, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setMinWidth(400);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        schemeTableView = new TableView<>();
        schemeTableView.setItems(personalExercises);
        schemeTableView.getColumns().addAll(nameColumn, descriptionColumn);
        System.out.println("Set the table");

        setTable.getChildren().addAll(schemeTableView);
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
    public void deleteExercise(ActionEvent event){

    }
    @FXML
    public void showExercises(ActionEvent event) throws IOException{
            //laad de nieuwe table in de bestaande anchorpane
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
            //maakt de oude table leeg
            personalScreen.getChildren().setAll();
            //laad de nieuwe table in
            personalScreen.getChildren().setAll(pane);
            //geeft de nieuwe table de juiste groote
            pane.prefWidthProperty().bind(personalScreen.widthProperty());
            pane.prefHeightProperty().bind(personalScreen.heightProperty());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schemeTableView();
    }
}
