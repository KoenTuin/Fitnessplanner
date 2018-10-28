package fitnessplanner.models;

import java.sql.SQLException;

public class Exercises {
    private String exercisesName;
    private int exerciseId;
    private String description;
    private String image;
    private String category;

    public Exercises(String exercisesName, int exerciseId, String description, String image, String category) {
        this.exercisesName = exercisesName;
        this.exerciseId = exerciseId;
        this.description = description;
        this.image = image;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                ", image='" + image + '\'' +
                '}';
    }
}
