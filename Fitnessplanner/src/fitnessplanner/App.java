import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("views/menu.fxml"));
        primaryStage.setTitle("Fitness applicatie");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        // test Database
//        Database db = Database.getDatabase();
//        ResultSet resultSet = db.executeResultSetQuery("SELECT * FROM category");
//
//        while ( resultSet.next() ){
//            System.out.println(resultSet.getString("name"));
//        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
