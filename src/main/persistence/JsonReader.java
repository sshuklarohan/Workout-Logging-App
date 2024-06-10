package persistence;

import model.Exercise;
import model.Exercises;
import model.Sets;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads Exercises from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads exercises from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Exercises read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseExercises(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses exercises from JSON object and returns it
    private Exercises parseExercises(JSONObject jsonObject) {
        Exercises e = new Exercises();
        addExercises(e, jsonObject);
        return e;
    }

    // MODIFIES: Exercises
    // EFFECTS: parses Exercise from JSON object and adds them to Exercises
    private void addExercises(Exercises exercises, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("lifts");
        for (Object json : jsonArray) {
            JSONObject nextExercise = (JSONObject) json;
            addExercise(exercises, nextExercise);
        }
    }

    // MODIFIES: Exercises
    // EFFECTS: parses Sets from JSON object and adds them to Exercise, adds Exercise to Exercises
    private void addExercise(Exercises exercises, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonArray = jsonObject.getJSONArray("Sets");
        Exercise lift = new Exercise(name);
        exercises.addExercise(lift);
        for (Object json : jsonArray) {
            JSONObject nextSet = (JSONObject) json;
            addSet(lift, nextSet);
        }
    }


    // MODIFIES: Exercises
    // EFFECTS: parses Sets from JSON object and adds it to Exercise
    private void addSet(Exercise e, JSONObject jsonObject) {
        int reps = jsonObject.getInt("Reps");
        int weight = jsonObject.getInt("Weight");
        Sets set = new Sets(weight,reps);
        e.addSet(set);
    }
}

