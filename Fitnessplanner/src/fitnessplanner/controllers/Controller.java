package fitnessplanner.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class Controller {
    public Label textLabel;
    public String inhoud;
    public Button showText;
    public TextArea discription;
    public TitledPane buik;

    public void button(ActionEvent actionEvent){
        inhoud = "Test oefening 1";
        textLabel.setText(inhoud);

    }
    @FXML
    public void showText(){
        System.out.println("It works");
        //Get from database data which is from catagory buik.gettext
        discription.setText("It works");
        System.out.println(buik.getText());
    }
}
