package models;

import database.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExercisesList {
    public List<Exercises> listOfExercises;
    Database db = Database.getDatabase();

    public List<Exercises> loadExercises() {
        List<Exercises> exercises = new ArrayList<>();
        ResultSet resultSet;

        try {
            resultSet = db.executeResultSetQuery("SELECT name, workout_id, description, image, category FROM workout ");
            while (resultSet.next()) {
                String name = (resultSet.getString("name"));
                int id = (resultSet.getInt("workout_id"));
                String description = (resultSet.getString("description"));
                String image = (resultSet.getString("image"));
                String category = (resultSet.getString("category"));
                Exercises exercise = new Exercises(name, id, description, image, category);

                exercises.add(exercise);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return exercises;
    }
}
