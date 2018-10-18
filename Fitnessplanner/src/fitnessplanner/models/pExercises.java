package fitnessplanner.models;

public class pExercises extends Exercises {

    private String createdBy;

    public pExercises(String exercisesName, String exerciseId, String description, String createdBy) {
        super(exercisesName, exerciseId, description);
        this.createdBy = createdBy;
    }
}
