package models;

import java.util.ArrayList;
import java.util.List;

public class PersonalExercisesList{

    public List<Exercises> PersonalListOfExercises;

    private List<Exercises> exercises = new ArrayList<>();

    public List<Exercises> loadExercises(Exercises e) {


//        if(PersonalListOfExercises == null){
//
//            Exercises exercise = e;
//            exercises.add(exercise);
//        }

        Exercises exercise = e;
        exercises.add(exercise);


        return exercises;
    }
}
