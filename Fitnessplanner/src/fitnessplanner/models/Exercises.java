package fitnessplanner.models;

public class Exercises {
    private String exercisesName;
    private String exerciseId;
    private String description;


    public Exercises(String exercisesName, String exerciseId, String description) {
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
}
