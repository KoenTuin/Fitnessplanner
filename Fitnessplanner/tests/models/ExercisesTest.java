package models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

//Import for us of Hamcrest

    public class ExercisesTest {
        public Exercises exercises;

        @Before
        public void myTestetup(){
            exercises = new Exercises("Pull-ups",10,
                    "Do a pullup", "", "Back");
        }

        @Test
        public void getName() {
            assertEquals("Pull-ups",exercises.getExercisesName());
        }

        @Test
        public void setName() {
            exercises.setExercisesName("");
            assertTrue(exercises.getExercisesName().isEmpty());
        }

        @Test (expected = AssertionError.class)
        public void setImage() {
            exercises.setImage("");
            assertFalse(exercises.getImage().isEmpty());
        }

        @Test
        public void setExercisesID() {
            exercises.setExerciseId(11);
            assertNotEquals(10, exercises.getExerciseId());
        }

        @Test
        public void setCategory() {
            exercises.setCategory(null);
            assertNull(exercises.getCategory());
        }
        //todo create five hamcrest tests. (assertThat)

        @Test
        public void getNameHamcrest() {
            exercises.setExercisesName("Squat");
            assertThat(exercises.getExercisesName(), is(equalTo("Squat")));
        }

        @Test
        public void setDescriptionHamcrest() {
            exercises.setDescription("Do a squad");
            assertThat(exercises.getDescription(), is(not(equalTo("Do a lunge"))));
        }

        @Test
        public void checkToStringHamcrest() {
            assertThat(exercises.toString(), is(equalTo("Exercises{" +
                    "exercisesName='" + exercises.getExercisesName() + '\'' +
                    ", exerciseId=" + exercises.getExerciseId() +
                    ", description='" + exercises.getDescription() + '\'' +
                    ", image='" + exercises.getImage() + '\'' +
                    ", category='" + exercises.getCategory() + '\'' +
                    '}')));
        }

        @Test
        public void getImageHamcrest() {
            assertThat(exercises.getImage(), is(notNullValue()));
        }

        @Test
        public void getExerciseIdHamcrest() {
            int value = exercises.getExerciseId() + 1;
            assertThat(value, is(not(equalTo(exercises.getExerciseId()))));
        }
    }