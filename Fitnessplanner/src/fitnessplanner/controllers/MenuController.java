package fitnessplanner.controllers;

import fitnessplanner.models.Exercises;
import fitnessplanner.database.Database;
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
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @FXML
    public void showExcercises() throws SQLException {
//        //select info from local database from right table
//        Database db = new Database();
//        ResultSet resultSet = db.executeResultSetQuery("SELECT description FROM workout");
//        System.out.println("It works");
//
//        exerciseMapper exMapper = new exerciseMapper();
//        ArrayList<Exercises> exList = (ArrayList)exMapper.exerciseList;
//        System.out.println(exMapper.test);
//        for (int i = 0; i < exList.size(); i++) {
//            System.out.println(exList.get(i).getDescription());
//        }

    }
    //Get from database data which is from catagory buik.gettext

    public void showImage(String image){
        if (image != null) {
            File file = new File("src/resources/images/" + image);
            System.out.println("Image path: " + file);
            Image newImage = new Image(file.toURI().toString());
            workoutImage.setImage(newImage);
        } else {
            workoutImage.setImage(null);
        }
    }

    @FXML
    public void showDescription(String naam) {

        ResultSet resultSet = null;
        try {
            resultSet = db.executeResultSetQuery("SELECT description FROM workout WHERE name = '" + naam + "'");

            discription.setText("");
            while (resultSet.next()) {
                discription.appendText(resultSet.getString("description") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadDescription() {
        discription.setText("");
        ResultSet resultSet = null;
        String[] dbCategorie = {buik.getText(), schouders.getText(), armen.getText(), benen.getText()};
        for (int i = 0; i < dbCategorie.length; i++) {

            try {

                resultSet = db.executeResultSetQuery("SELECT name,image FROM workout WHERE category = '" + dbCategorie[i] + "'");

                while (resultSet.next()) {

                    String naam = resultSet.getString("name");
                    Label label = new Label(naam);
                    String image = resultSet.getString("image");
                    Exercises ex = new Exercises(naam, 0, null);
                    System.out.println("Naam: " + naam);
//                label.setId(naam);
                    label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //shows the description
                            showDescription(naam);
                            //shows the image
                            showImage(image);
                        }
                    });
                    //adds label to the right parent
                    if (dbCategorie[i] == buik.getText()) {
                        buikExcercises.getChildren().add(label);
                    } else if (dbCategorie[i] == schouders.getText()) {
                        shoulderExcercises.getChildren().add(label);
                    } else if (dbCategorie[i] == armen.getText()) {
                        armenExcercises.getChildren().add(label);
                    } else if (dbCategorie[i] == benen.getText()) {
                        benenExcercises.getChildren().add(label);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDescription();
    }
}
