package model;


import org.json.JSONObject;
import persistence.Writable;

//This class represents a single set that would be tracked, a set is composed of weight and reps performed
public class Sets implements Writable, Comparable {

    private final int weight;
    private final int reps;


    //EFFECTS: creates a new set with weight and reps
    public Sets(int weight, int reps) {
        this.weight = weight;
        this.reps = reps;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }


    //EFFECTS: calculates 1 rep max using Brzycki formula
    public double calcOneRepMax() {
        double w = getWeight();
        double r = getReps();
        return Math.round(w * (36 / (37 - r)));
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Weight", weight);
        json.put("Reps",reps);
        return json;
    }


    @Override
    public int compareTo(Object o) {
        double t = this.calcOneRepMax();
        double other = ((Sets) o).calcOneRepMax();

        if (t == other) {
            return 0;
        } else if (t > other) {
            return 1;
        } else {
            return -1;
        }


    }
}
