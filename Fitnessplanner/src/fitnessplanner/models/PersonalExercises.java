package models;

import javafx.scene.control.TextArea;

import java.awt.*;

public class PersonalExercises extends Exercises {

    private String createdBy;


    public PersonalExercises(String exercisesName, int exerciseId, String description, String image, String category) {
        super(exercisesName, exerciseId, description, image, category);
    }
}
