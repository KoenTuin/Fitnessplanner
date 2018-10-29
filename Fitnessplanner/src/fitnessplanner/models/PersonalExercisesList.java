/*
Author: Koen van der Tuin

Purpose: The purpose of the PersonalExercisesList class is to create a list of Exercises which the user wants
to be in their own scheme.
 */
package models;

import java.util.ArrayList;
import java.util.List;

public class PersonalExercisesList {

    public List<Exercises> PersonalListOfExercises;
    private List<Exercises> exercises = new ArrayList<>();

    public static PersonalExercisesList personalExercisesList;

    public static PersonalExercisesList getPersonalExerciselists() {
        if (personalExercisesList == null) {
            personalExercisesList = new PersonalExercisesList();
        }

        return personalExercisesList;
    }

    public List<Exercises> loadExercises(Exercises e) {

        Exercises exercise = e;
        exercises.add(exercise);

        return exercises;
    }
}
