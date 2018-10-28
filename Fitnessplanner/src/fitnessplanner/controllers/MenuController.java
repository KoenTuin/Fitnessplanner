package fitnessplanner.controllers;

import fitnessplanner.models.Exercises;
import fitnessplanner.database.Database;
import fitnessplanner.models.ExercisesList;
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
    private ExercisesList exercisesList = new ExercisesList();
    Database db = Database.getDatabase();

    @FXML
    public void showScheme()throws IOException {
        System.out.println("Go to personal scheme");
        //laad de nieuwe table in de bestaande anchorpane
//        menuScreen.getChildren().setAll(FXMLLoader.load("../../../resources/views/personalScheme.fxml"));

        Stage editPriceDialogStage = new Stage();
        Parent editPriceDialogRoot = FXMLLoader.load(
                getClass().getResource("../resources/views/personalScheme.fxml")
        );
        Scene editPriceDialog = new Scene(editPriceDialogRoot);
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
            try {
                if (e.getExercisesName().equals(naam)) {
                    discription.setText("");
                    discription.appendText(e.getDescription() + "\n");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
