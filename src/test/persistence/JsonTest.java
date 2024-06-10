package persistence;

import model.Exercise;
import model.Sets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkExercise(String name, Sets s1, Exercise exercise) {
        assertEquals(name, exercise.getName());
        checkSet(s1,exercise.getLog().get(0));
    }

    private void checkSet(Sets s1, Sets s2) {
        assertEquals(s1.getWeight(),s2.getWeight());
        assertEquals(s1.getReps(),s2.getReps());
    }

}
