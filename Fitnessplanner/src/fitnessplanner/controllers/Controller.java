package fitnessplanner.controllers;

import fitnessplanner.mapper.exerciseMapper;
import fitnessplanner.models.Exercises;
import fitnessplanner.models.MyJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
    MyJDBC db = MyJDBC.getDatabase();

    public void button(ActionEvent actionEvent) {
        inhoud = "Test oefening 1";
        textLabel.setText(inhoud);
    }

    @FXML
    public void showExcercises() throws SQLException {
//        //select info from local database from right table
//        MyJDBC db = new MyJDBC();
//        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout");
        System.out.println("It works");

        exerciseMapper exMapper = new exerciseMapper();
        ArrayList<Exercises> exList = (ArrayList)exMapper.exerciseList;
        System.out.println(exMapper.test);
        for (int i = 0; i < exList.size(); i++) {
            System.out.println(exList.get(i).getDescription());
        }

    }
    //Get from database data which is from catagory buik.gettext




    @FXML
    public void showDescription(String naam) throws SQLException {

        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout WHERE name = '"+ naam + "'");
        discription.setText("");
        while (resultSet.next()) {
            discription.appendText(resultSet.getString("description") + "\n");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //File file = new File("src/Test.png");resultSet = db.executeResultSetQuery("SELECT name FROM workout WHERE category = 'Buik'");
//        File file = new File("src/images/crunch.jpg");
//        Image image = new Image(file.toURI().toString());
//        workoutImage.setImage(image);
        discription.setText("");
        ResultSet resultSet = null;
        String[] dbCategorie = {buik.getText(),schouders.getText(),armen.getText(),benen.getText()};
        for (int i = 0; i < dbCategorie.length; i++) {

            try {

                resultSet = db.executeResultSetQuery("SELECT name,image FROM workout WHERE category = '" + dbCategorie[i] + "'");

                while (resultSet.next()) {

                    String naam = resultSet.getString("name");
                    Label label = new Label(naam);
                    String images = resultSet.getString("image");
                    Exercises ex = new Exercises(naam,0,null);
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
        //Koen oude code
//        try {
//
//            resultSet = db.executeResultSetQuery("SELECT name FROM workout WHERE category = 'Shoulders'");
//
//            while (resultSet.next()) {
//                String naam = resultSet.getString("name");
//                Label label = new Label(naam);
////                label.setId(naam);
//                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        try{
//                            showDescription(naam);
//                        }catch(SQLException e){
//                            System.out.println("A SQL excepption has occured\n" + e);
//                        }
//
//                    }
//                });
//                shoulderExcercises.getChildren().add(label);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//
//            resultSet = db.executeResultSetQuery("SELECT name FROM workout WHERE category = 'Armen'");
//
//            while (resultSet.next()) {
//                String naam = resultSet.getString("name");
//                Label label = new Label(naam);
////                label.setId(naam);
//                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        try{
//                            showDescription(naam);
//                        }catch(SQLException e){
//                            System.out.println("A SQL excepption has occured\n" + e);
//                        }
//
//                    }
//                });
//                armenExcercises.getChildren().add(label);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//
//            resultSet = db.executeResultSetQuery("SELECT name FROM workout WHERE category = 'Benen'");
//
//            while (resultSet.next()) {
//                String naam = resultSet.getString("name");
//                Label label = new Label(naam);
////                label.setId(naam);
//                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        try{
//                            showDescription(naam);
//                        }catch(SQLException e){
//                            System.out.println("A SQL excepption has occured\n" + e);
//                        }
//
//                    }
//                });
//                benenExcercises.getChildren().add(label);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
