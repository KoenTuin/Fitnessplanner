package fitnessplanner.mapper;

import fitnessplanner.database.Database;
import fitnessplanner.models.Exercises;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class exerciseMapper {
        private Database db;
        private String name;
        private int id;
        private String description;

        public List<Exercises> exerciseList;
        public String test = "test";

        public List getExerciseList(){
            return exerciseList;
        }
   public exerciseMapper(){
            db = Database.getDatabase();

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
