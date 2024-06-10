package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//This class represents a single exercise that would be tracked, it stores the exercise name and the information
//info of each set performed
public class Exercise implements Writable {

    private String name;
    private List<Sets> log;
    private List<Sets> sortedLog;
    private double oneRepMax;
    private List<Double> oneRepMaxes;


    //EFFECTS: creates a new exercise with a name and a log to store all sets with initial oneRepMax set as 0
    //         and creates an empty list to store one rep max of each set performed, creates a sorted log that sorts
    //         in ascending order by one rep maxes.
    public Exercise(String name) {
        this.name = name;
        this.log = new ArrayList<>();
        this.sortedLog = new ArrayList<>();
        this.oneRepMax = 0;
        this.oneRepMaxes = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECTS: adds a set to the end of the current log
    public void addSet(Sets set) {
        log.add(set);
        sortedLog.add(set);
        Collections.sort(sortedLog);
        if (set.calcOneRepMax() > oneRepMax) {
            this.oneRepMax = set.calcOneRepMax();
        }
        this.oneRepMaxes.add(set.calcOneRepMax());
        EventLog.getInstance().logEvent(new Event("Set Added"));


    }


    public double getOneRepMax() {
        return oneRepMax;
    }

    //REQUIRES: i < log.size()
    //MODIFIES: this
    //EFFECTS: removes a set at index i from the log, and removes the same set from sorted log,
    // if removed set was a one rep max replace it with next highest 1rm
    public void removeSet(int i) {
        Sets s = log.get(i);
        log.remove(s);
        sortedLog.remove(s);

        if (oneRepMaxes.get(i) == oneRepMax) {
            this.oneRepMaxes.remove(i);
            this.oneRepMax = highest(oneRepMaxes);
        } else {
            this.oneRepMaxes.remove(i);
        }
        EventLog.getInstance().logEvent(new Event("Set removed"));

    }

    //REQUIRES: log.contains(s)
    //MODIFIES: this
    //EFFECTS: removes a set from log and sortedLog, if removed set was a one rep max replace it with next highest 1rm
    public void removeSet(Sets s) {
        log.remove(s);
        sortedLog.remove(s);

        Double d = s.calcOneRepMax();
        if (d == oneRepMax) {
            this.oneRepMaxes.remove(d);
            this.oneRepMax = highest(oneRepMaxes);
        } else {
            this.oneRepMaxes.remove(d);
        }
        EventLog.getInstance().logEvent(new Event("Set removed"));

    }

    //REQUIRES: non-empty list
    //EFFECTS: produces the largest double within the list
    private double highest(List<Double> oneRepMaxes) {
        double i = 0;
        for (Double d : oneRepMaxes) {
            if (d > i) {
                i = d;
            }
        }
        return i;
    }

    //EFFECTS: returns amount of sets within Exercise
    public int length() {
        return log.size();
    }

    public List<Sets> getLog() {
        return log;
    }

    public List<Sets> getSortedLog() {
        return sortedLog;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("Sets", setToJson());
        return json;
    }


    // EFFECTS: returns Sets in this Exercise as a JSON array
    private JSONArray setToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Sets s : log) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
