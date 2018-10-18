package fitnessplanner.controllers;

import fitnessplanner.models.MyJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label textLabel;
    public String inhoud;
    public Button showText;
    public Button voegToe;
    public TextArea discription;
    public TitledPane buik;
    public TitledPane schouders;
    public TitledPane benen;
    public TitledPane armen;
    public VBox buikExcercises;
    public VBox shoulderExcercises;
    public VBox armenExcercises;
    public VBox benenExcercises;
    public ImageView workoutImage;
    public MyJDBC db = new MyJDBC();

    @FXML
    public void showExcercises() throws SQLException {
//        //select info from local database from right table
//        MyJDBC db = new MyJDBC();
//        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout");
        System.out.println("It works");
        //Get from database data which is from catagory buik.gettext
    }
    ArrayList<String> schema = new ArrayList<>();
    @FXML
    public void voegToe(ActionEvent actionEvent){
        String print = "Voeg workout toe aan lijst";
        schema.add(print);
        System.out.println(schema);
    }

    public void addWorkoutToList(String naam) throws SQLException{
//        if (voegToe.fire();){
//            System.out.println("test");
//        }

        ResultSet resultSet = db.executeResultSetQuery("SELECT workout_id FROM workout WHERE name = '"+ naam + "'");
            while (resultSet.next()){
                int workoutId = resultSet.getInt("workout_id");
                System.out.print(workoutId);
        }
    }


    @FXML
    public void showDescription(String naam) throws SQLException {

        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout WHERE name = '"+ naam + "'");
        discription.setText("");
        while (resultSet.next()) {
            discription.appendText(resultSet.getString("description") + "\n");
        }
        //if person clicks on voegtoe voer voeg too method uit
    }

    public void showWorkout(String naam){
        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout WHERE name = '"+ naam + "'");
        String images = resultSet.getString("image");

        System.out.println("Naam: " + naam);
//                label.setId(naam);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    //shows the description
                    showDescription(naam);
                    //shows the image
                    if (images != null) {
                        File file = new File("src/images/" + images);
                        Image image = new Image(file.toURI().toString());
                        workoutImage.setImage(image);
                    }else {
                        workoutImage.setImage(null);
                    }

                } catch (SQLException e) {
                    System.out.println("A SQL excepption has occured\n" + e);
                }

            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        discription.setText("");
//        MyJDBC db = new MyJDBC();
        ResultSet resultSet = null;
        String[] dbCategorie = {buik.getText(),schouders.getText(),armen.getText(),benen.getText()};
        for (int i = 0; i < dbCategorie.length; i++) {

            try {

                resultSet = db.executeResultSetQuery("SELECT name,image FROM workout WHERE category = '" + dbCategorie[i] + "'");

                while (resultSet.next()) {
                    String naam = resultSet.getString("name");
                    Label label = new Label(naam);



                    //adds label to the right parent
                    if (dbCategorie[i] == buik.getText()) {
                        buikExcercises.getChildren().add(label);
                    }else if(dbCategorie[i] == schouders.getText()){
                        shoulderExcercises.getChildren().add(label);
                    }else if(dbCategorie[i] == armen.getText()){
                        armenExcercises.getChildren().add(label);
                    }else if(dbCategorie[i] == benen.getText()){
                        benenExcercises.getChildren().add(label);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}