package fitnessplanner.controllers;

import fitnessplanner.models.MyJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Label textLabel;
    public String inhoud;
    public Button showText;
    public TextArea discription;
    public TitledPane buik;
    public VBox buikExcercises;
    public VBox shoulderExcercises;
    public VBox armenExcercises;
    public VBox benenExcercises;

    public void button(ActionEvent actionEvent) {
        inhoud = "Test oefening 1";
        textLabel.setText(inhoud);

    }

    @FXML
    public void showExcercises() throws SQLException {
//        //select info from local database from right table
//        MyJDBC db = new MyJDBC();
//        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout");
//
//

        System.out.println("It works");
        //Get from database data which is from catagory buik.gettext


    }

    @FXML
    public void showDescription(String naam) throws SQLException {
        MyJDBC db = new MyJDBC();
        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout WHERE name = '"+ naam + "'");
        discription.setText("");
        while (resultSet.next()) {
            discription.appendText(resultSet.getString("description") + "\n");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        discription.setText("");
        MyJDBC db = new MyJDBC();
        ResultSet resultSet = null;
        try {

            resultSet = db.executeResultSetQuery("SELECT name FROM workout");

            while (resultSet.next()) {
                String naam = resultSet.getString("name");
                Label label = new Label(naam);
//                label.setId(naam);
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try{
                            showDescription(naam);
                        }catch(SQLException e){
                            System.out.println("A SQL excepption has occured\n" + e);
                        }

                    }
                });
                buikExcercises.getChildren().add(label);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
