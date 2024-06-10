package ui;

import model.Exercise;
import model.Exercises;
import model.Sets;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


//This class represents the FitTrackApp user interface, processes user input and displays a list of exercises and their
//corresponding sets for the user to view and modify
public class FitTrackApp {

    private static final String JSON_STORE = "./data/exercises.json";
    private Exercises exercises;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: run fit track application
    public FitTrackApp() {
        exercises = new Exercises();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runFitTrack();
    }

    //MODIFIES: this
    //EFFECTS: processes user input
    private void runFitTrack() {
        int run = 1;


        loadOption();

        while (run == 1) {
            listNames(exercises);
            int command = input.nextInt();

            if (command == (exercises.numExercises() + 1)) {
                run = 0;
            } else if (command == (exercises.numExercises() + 2)) {
                run = 0;
                saveExercises();
            } else {
                processCommand(command);
            }

        }

    }


    //MODIFIES: this
    //EFFECTS: processes user command
    private void processCommand(int command) {
        if (command == exercises.numExercises()) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Input desired Exercise: ");
            String name = sc.nextLine();
            exercises.addExercise(new Exercise(name));
        } else if ((0 <= command) && (command < (exercises.numExercises()))) {
            viewExercise(command);
        } else {
            System.out.println("Invalid Selection");
        }
    }

    //MODIFIES: this
    //EFFECTS: asks user if they want to load a save file or start fresh
    private void loadOption() {
        System.out.println("Do you wish to (1: Load Save file) or (2: Start New FitTrack)");
        int in = input.nextInt();
        if (in == 1) {
            loadExercises();
        } else if (in == 2) {
            createLogs();
        } else {
            System.out.println("Invalid input");
        }
    }



    //EFFECTS: Initializes user FitTrack data
    private void createLogs() {
        exercises.addExercise(new Exercise("Squat"));
        exercises.addExercise(new Exercise("Benchpress"));
        exercises.addExercise(new Exercise("Deadlift"));
    }

    //REQUIRES: c is within index of exercises.size() + 2
    //EFFECTS: Displays log of Exercise and menu of options for user
    private void viewExercise(int c) {
        int in = 1;

        while (in != 3) {
            printlog(exercises.getExercise(c));
            System.out.print("Do you wish to (1: add set) or (2: remove set) or (3: exit exercise):");
            in = input.nextInt();
            if (in == 1) {
                addSet(c);
            } else if (in == 2) {
                removeSet(c);
            }
        }
    }

    //REQUIRES: c is within index of exercises
    //MODIFIES: this
    //EFFECTS: adds a set using user input to desired exercise
    private void addSet(int c) {
        System.out.print("Enter Weight amount: ");
        int weight = input.nextInt();
        System.out.print("Enter Reps performed: ");
        int reps = input.nextInt();
        exercises.getExercise(c).addSet(new Sets(weight,reps));
    }

    //REQUIRES: c is within index of exercises
    //MODIFIES: this
    //EFFECTS: removes a set according to user input
    private void removeSet(int c) {
        System.out.println("Enter # of set you wish to remove");
        int setnum = input.nextInt();
        if ((setnum > 0) && (setnum <= (exercises.getExercise(c).length()))) {
            exercises.getExercise(c).removeSet((setnum - 1));
        } else {
            System.out.println("Invalid input");
        }

    }

    //REQUIRES: lifts is not empty
    //EFFECTS: prints names of all exercises in lifts listed next to their index
    //         prints additional option to add exercise or quit
    private void listNames(Exercises lifts) {
        System.out.println("EXERCISES");
        for (Exercise e: lifts.getLog()) {
            System.out.println(lifts.getIndex(e) + ": " + e.getName());
        }
        System.out.println(lifts.numExercises() + ": Add exercise");
        System.out.println(lifts.numExercises() + 1 + ": Quit");
        System.out.println(lifts.numExercises() + 2 + ": Save and Quit");
    }


    //EFFECTS: prints out the exercise name as well as the log of all sets of the current exercise
    private void printlog(Exercise e) {
        System.out.println(e.getName());
        System.out.println("# Weight Reps");
        for (Sets set: e.getLog()) {
            System.out.print(e.getLog().indexOf(set) + 1 + " ");
            printSet(set);
        }
        System.out.println("1RM: " + e.getOneRepMax());
    }


    //EFFECTS: prints out weight and corresponding reps on the same line with space between
    private void printSet(Sets s) {
        System.out.print(s.getWeight() + "   ");
        System.out.println(s.getReps());
    }

    // EFFECTS: saves the exercises to file
    private void saveExercises() {
        try {
            jsonWriter.open();
            jsonWriter.write(exercises);
            jsonWriter.close();
            System.out.println("Saved Exercises to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads exercises from file
    private void loadExercises() {
        try {
            exercises = jsonReader.read();
            System.out.println("Loaded Exercises from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
