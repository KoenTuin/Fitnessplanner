package fitnessplanner.models;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercises {
    private String exercisesName;
    private int exerciseId;
    private String description;;

    public Exercises(String exercisesName, int exerciseId, String description) {
        this.exercisesName = exercisesName;
        this.exerciseId = exerciseId;
        this.description = description;
    }

    public String getExercisesName() {
        return exercisesName;
    }

    public void setExercisesName(String exercisesName) {
        this.exercisesName = exercisesName;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }



    public String getDescription() throws SQLException {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "exercisesName='" + exercisesName + '\'' +
                ", exerciseId=" + exerciseId +
                ", description='" + description + '\'' +
                '}';
    }
}
