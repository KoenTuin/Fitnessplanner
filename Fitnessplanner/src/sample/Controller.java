package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class Controller {
    public Label textLabel;
    public String inhoud;

    public void button(ActionEvent actionEvent){
        inhoud = "Test oefening 1";
        textLabel.setText(inhoud);
    }
}
