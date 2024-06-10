package persistence;

import model.Exercise;
import model.Exercises;
import model.Sets;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            Exercises wr = new Exercises();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Exercises exercises = new Exercises();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyExercises.json");
            writer.open();
            writer.write(exercises);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyExercises.json");
            exercises = reader.read();
            assertEquals("Exercises", exercises.getName());
            assertEquals(0, exercises.numExercises());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        Exercise bench = new Exercise("Bench Press");
        bench.addSet(new Sets(100,50));
        Exercise squat = new Exercise("Squat");
        squat.addSet(new Sets(200,50));
        try {
            Exercises exercises = new Exercises();
            exercises.addExercise(bench);
            exercises.addExercise(squat);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralExercises.json");
            writer.open();
            writer.write(exercises);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralExercises.json");
            exercises = reader.read();
            assertEquals("Exercises", exercises.getName());
            List<Exercise> log = exercises.getLog();
            assertEquals(2, log.size());
            checkExercise("Bench Press", new Sets(100,50), log.get(0));
            checkExercise("Squat", new Sets(200,50), log.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
