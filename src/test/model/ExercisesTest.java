package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExercisesTest {

    Exercises exercises;
    List<Exercise> l1;
    Exercise bench;
    Exercise squat;

    @BeforeEach
    void setup() {
        exercises = new Exercises();
        l1 = new ArrayList<>();
        bench = new Exercise("Bench press");
        squat = new Exercise("squat");
    }



    @Test
    void testConstructor() {
        assertEquals(l1,exercises.getLog());
    }

    @Test
    void testGetExerciseAndIndex() {
        exercises.addExercise(bench);
        exercises.addExercise(squat);
        l1.add(bench);
        l1.add(squat);
        assertEquals("Exercises",exercises.getName());
        assertEquals(l1.get(0),exercises.getExercise(0));
        assertEquals(l1.indexOf(squat),exercises.getIndex(squat));
        assertEquals(l1,exercises.getLog());
        assertEquals(l1.size(),exercises.numExercises());
    }
}
