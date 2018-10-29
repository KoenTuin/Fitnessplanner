package controllers;

import models.Exercises;
import database.Database;
import models.ExercisesList;
import models.PersonalExercises;
import models.PersonalExercisesList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Label textLabel;
    @FXML
    private Button showText;
    @FXML
    private Button voegToe;
    @FXML
    private TextArea discription;
    @FXML
    private TitledPane buik;
    @FXML
    private TitledPane schouders;
    @FXML
    private TitledPane benen;
    @FXML
    private TitledPane armen;
    @FXML
    private VBox buikExcercises;
    @FXML
    private VBox shoulderExcercises;
    @FXML
    private VBox armenExcercises;
    @FXML
    private VBox benenExcercises;
    @FXML
    private ImageView workoutImage;
    @FXML
    private BorderPane menuScreen;
    @FXML
    private AnchorPane menuScreenSwitch;
    private ExercisesList exercisesList = new ExercisesList();
    PersonalExercisesList personalListOfExercises = PersonalExercisesList.getPersonalExerciselists();

    @FXML
    public void addExerciseToPersonalList() {
        List<Exercises> testList;
        String textDescription = discription.getText().replaceAll("\\s+", "");
        for (Exercises e : exercisesList.listOfExercises) {
            if (e.getDescription().replaceAll("\\s+", "").equals(textDescription)) {
                if (personalListOfExercises.PersonalListOfExercises == null) {
                    System.out.println("New list: first value");
                    personalListOfExercises.PersonalListOfExercises = personalListOfExercises.loadExercises(e);
                } else {
                    if (checkIfDouble(e, textDescription)) {
                        personalListOfExercises.PersonalListOfExercises = personalListOfExercises.loadExercises(e);
                        System.out.println("New list: value added to list");

                        for (Exercises f : personalListOfExercises.PersonalListOfExercises) {
                            System.out.println(f);
                        }
                    } else {
                        System.out.println("value was duplicate");
                    }

                }
                

            }
        }
    }

    public boolean checkIfDouble(Exercises ex, String description) {
        boolean waarde = true;
        for (Exercises e : personalListOfExercises.PersonalListOfExercises) {
            if (description.equals(e.getDescription().replaceAll("\\s+", ""))) {
                waarde = false;
            }
        }
        return waarde;
    }


    @FXML
    public void showScheme(ActionEvent event) throws IOException {
        //laad de nieuwe table in de bestaande anchorpane
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/views/personalScheme.fxml"));
        //maakt de oude table leeg
        menuScreenSwitch.getChildren().setAll();
        //laad de nieuwe table in
        menuScreenSwitch.getChildren().setAll(pane);
        //geeft de nieuwe table de juiste groote
        pane.prefWidthProperty().bind(menuScreenSwitch.widthProperty());
        pane.prefHeightProperty().bind(menuScreenSwitch.heightProperty());
    }


    public void showImage(String image) {
        if (image != null) {

            String standardPath = "src/resources/images/";
            File file = new File(standardPath + image);
            Image newImage = new Image(file.toURI().toString());

            workoutImage.setImage(newImage);

        } else {
            workoutImage.setImage(null);
        }
    }


    @FXML
    public void showDescription(String naam) {
        List<Exercises> listOfExercises = exercisesList.listOfExercises;
        for (Exercises e : listOfExercises) {
            if (e.getExercisesName().equals(naam)) {
                discription.setText("");
                discription.appendText(e.getDescription() + "\n");
            }
        }
    }


    public void loadCategoryExercises() {

        discription.setText("");
        String category = "";

        for (Exercises e : exercisesList.listOfExercises) {
            category = e.getCategory();
        }

        String[] dbCategory = {buik.getText(), schouders.getText(), armen.getText(), benen.getText()};
        for (int i = 0; i < dbCategory.length; i++) {

            for (Exercises e : exercisesList.listOfExercises) {
                if (category.equals(dbCategory[i])) {

                    String name = e.getExercisesName();
                    String image = e.getImage();
                    Label label = new Label(name);

                    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //shows the description
                            showDescription(name);
                            //shows the image
                            showImage(image);
                        }
                    });
                    //adds label to the right parent
                    if (e.getCategory().equals(buik.getText())) {
                        buikExcercises.getChildren().add(label);
                    } else if (e.getCategory().equals(schouders.getText())) {
                        shoulderExcercises.getChildren().add(label);
                    } else if (e.getCategory().equals(armen.getText())) {
                        armenExcercises.getChildren().add(label);
                    } else if (e.getCategory().equals(benen.getText())) {
                        benenExcercises.getChildren().add(label);
                    }
                }
            }

        }
    }


    @Override

    public void initialize(URL location, ResourceBundle resources) {
        exercisesList.listOfExercises = exercisesList.loadExercises();
        loadCategoryExercises();
    }
}

