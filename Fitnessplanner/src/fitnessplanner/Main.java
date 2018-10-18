package fitnessplanner;

import fitnessplanner.models.MyJDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/Menu.fxml"));
        primaryStage.setTitle("Fitness applicatie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // test myjdbc
        MyJDBC db = MyJDBC.getDatabase();
        ResultSet resultSet = db.executeResultSetQuery("SELECT * FROM category");

        while ( resultSet.next() ){
            System.out.println(resultSet.getString("name"));
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
