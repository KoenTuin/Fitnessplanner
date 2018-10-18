package fitnessplanner.models;

import java.awt.*;

public class pExercise extends Exercise {

    private String createdBy;

    public pExercise(String exerciseName, int exerciseId, String description, String createdBy) {
        super(exerciseName, exerciseId, description);
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
