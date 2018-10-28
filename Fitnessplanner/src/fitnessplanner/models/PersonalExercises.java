package fitnessplanner.models;

import javafx.scene.control.TextArea;

import java.awt.*;

public class PersonalExercises extends Exercises {

    private String createdBy;


    public PersonalExercises(String exercisesName, int exerciseId, String description) {
        super(exercisesName, exerciseId, description);
    }
}
