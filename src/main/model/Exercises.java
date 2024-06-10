package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//This class represents a collection of all Exercises a user tracks
public class Exercises implements Writable {
    private String name;
    private List<Exercise> log;

    //EFFECTS: constructs a list where all exercises would be stored
    public Exercises() {
        this.name = "Exercises";
        log = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add Exercise to the log of exercises
    public void addExercise(Exercise e) {
        log.add(e);
        EventLog.getInstance().logEvent(new Event("Exercise added"));
    }

    public String getName() {
        return this.name;
    }


    //EFFECTS: returns number of exercises in list
    public int numExercises() {
        return log.size();
    }

    //EFFECTS: returns exercise at given index
    public Exercise getExercise(int i) {
        return log.get(i);
    }

    //EFFECTS: returns index of given exercise
    public int getIndex(Exercise e) {
        return log.indexOf(e);
    }

    //EFFECTS: returns list of all exercises
    public List<Exercise> getLog() {
        return log;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("lifts", exerciseToJson());
        return json;
    }

    // EFFECTS: returns Exercise in Exercises as a JSON array
    private JSONArray exerciseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Exercise e : log) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }





}
