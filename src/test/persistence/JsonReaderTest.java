package persistence;

import model.Exercise;
import model.Exercises;
import model.Sets;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Exercises exercises = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyExercises.json");
        try {
            Exercises exercises = reader.read();
            assertEquals("Exercises", exercises.getName());
            assertEquals(0, exercises.numExercises());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralExercises.json");
        try {
            Exercises exercises = reader.read();
            assertEquals("Exercises", exercises.getName());
            List<Exercise> log = exercises.getLog();
            assertEquals(2, log.size());
            checkExercise("Bench Press", new Sets(100,10), log.get(0));
            checkExercise("Squat", new Sets(100,20), log.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
