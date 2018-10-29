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
import javafx.scene.layout.HBox;
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

        setTable.getChildren().addAll(schemeTableView);

    }
    

    @FXML
    public void deleteExercise(ActionEvent event){
        ObservableList<Exercises> exerciseSelected, allExercises;
        allExercises = schemeTableView.getItems();
        exerciseSelected = schemeTableView.getSelectionModel().getSelectedItems();

        exerciseSelected.forEach(allExercises::remove);

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
