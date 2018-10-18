package fitnessplanner.models;


public class Exercise {
    private String exerciseName;
    private int exerciseId;
    private String description;


    public Exercise(String exerciseName, int exerciseId, String description) {
        this.exerciseName = exerciseName;
        this.exerciseId = exerciseId;
        this.description = description;
    }

    public String getExerciseName()  {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
