package fitnessplanner.mapper;

import fitnessplanner.models.Exercises;
import fitnessplanner.models.MyJDBC;

import java.io.File;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class exerciseMapper {
        private MyJDBC db;
        private String name;
        private int id;
        private String description;

        public List<Exercises> exerciseList;
        public String test = "test";

        public List getExerciseList(){
            return exerciseList;
        }
   public exerciseMapper(){
            db = MyJDBC.getDatabase();

            exerciseList = new ArrayList<>();
            getExerciseMethode();
   }
    public void getExerciseMethode(){



        try {
            ResultSet resultset = db.executeResultSetQuery("SELECT name FROM workout WHERE category = 'Buik'");
            name = resultset.getString("name");
            System.out.println("Name: "+name);
//            id = resultset.getInt("workout_id");
//            description = resultset.getString("description");
           // exerciseList = new ArrayList<>();
//            while(resultset.next()) {
//                Exercises ex = new Exercises(name, 0, null);
//                exerciseList.add(ex);
//            }
        }catch(SQLException e){
            System.out.println(e);
        }



    }
}
