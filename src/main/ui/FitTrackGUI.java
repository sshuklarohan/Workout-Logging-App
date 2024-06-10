package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.ExerciseTab;
import ui.tabs.HomeTab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

//represents the application main window
public class FitTrackGUI extends JFrame implements WindowListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/exercises.json";
    private Exercises exercises;
    private JTabbedPane sidebar;
    private JMenuBar menuBar;



    //EFFECTS:runs a FitTrackGUI
    public FitTrackGUI() {
        super("FitTrack Console");
        setSize(WIDTH, HEIGHT);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        exercises = new Exercises();


        exercises.addExercise(new Exercise("Bench Press"));
        exercises.addExercise(new Exercise("Squat"));
        exercises.addExercise(new Exercise("Deadlift"));


        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        addMenuBar();
        add(sidebar);
        addWindowListener(this);
        setVisible(true);





    }

    // MODIFIES: this
    // EFFECTS: loads exercises from file
    private void loadExercises() {
        try {
            exercises = jsonReader.read();
            sidebar.removeAll();
            loadTabs();
            EventLog.getInstance().logEvent(new Event("Loaded Exercises from " + JSON_STORE));
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



    //MODIFIES: this
    //EFFECTS: adds load and save tab
    private void loadTabs() {
        JPanel homeTab = new HomeTab(exercises);
        sidebar.add(homeTab);
        sidebar.setTitleAt(0,"Home");
        for (Exercise e: exercises.getLog()) {
            JPanel exerciseTab = new ExerciseTab(e);
            sidebar.add(exerciseTab,(exercises.getIndex(e) + 1));
            sidebar.setTitleAt((exercises.getIndex(e) + 1),e.getName());
        }


    }

    //MODIFIES: this
    //EFFECTS: adds a menu bar with one menu item to "save/load" and another to "add" an exercise
    private void addMenuBar() {
        menuBar = new JMenuBar();
        JMenu saveMenu = new JMenu("Save/Load");
        addMenuItem(saveMenu, new AddSaveAction(),
                KeyStroke.getKeyStroke("control S"));
        addMenuItem(saveMenu, new AddLoadAction(),
                KeyStroke.getKeyStroke("control L"));
        menuBar.add(saveMenu);

        JMenu addMenu = new JMenu("Add");
        addMenuItem(addMenu, new AddExerciseAction(),
                KeyStroke.getKeyStroke("control A"));
        menuBar.add(addMenu);


        setJMenuBar(menuBar);
    }




    //MODIFIES: this.menubar
    //EFFECTS: adds and item to a given menu in the menubar, and adds its functionality
    private void addMenuItem(JMenu theMenu, AbstractAction action, KeyStroke accelerator) {
        JMenuItem menuItem = new JMenuItem(action);
        menuItem.setMnemonic(menuItem.getText().charAt(0));
        menuItem.setAccelerator(accelerator);
        theMenu.add(menuItem);
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event: EventLog.getInstance()) {
            System.out.println(event);
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


    //EFFECTS: performs a call to saveExercises when called
    private class AddSaveAction extends AbstractAction {

        AddSaveAction() {
            super("Save Exercises");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            saveExercises();
        }
    }



    //EFFECTS: performs a call to loadExercises when called
    private class AddLoadAction extends AbstractAction {

        AddLoadAction() {
            super("Load Exercises");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadExercises();
        }
    }


    //EFFECTS: performs a call to addExerciseFrame when called
    private class AddExerciseAction extends AbstractAction {

        AddExerciseAction() {
            super("Add Exercise");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            addExerciseFrame();
        }
    }


    //MODIFIES: this
    //EFFECTS: creates a frame that takes the users input for a new exercise
    //         and adds the exercise to the users current exercise list
    private void addExerciseFrame() {
        JFrame frame = new JFrame("Add Exercise");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        JTextField input = new JTextField(20);
        JButton button = new JButton("Enter");
        inputpanel.add(input);
        inputpanel.add(button);
        panel.add(inputpanel);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        input.requestFocus();

        button.addActionListener(e -> {
            exercises.addExercise(new Exercise(input.getText()));
            frame.dispose();
            sidebar.removeAll();
            loadTabs();
            getTabbedPane().setSelectedIndex(exercises.numExercises());
        });


    }




    // EFFECTS: saves the exercises to file
    private void saveExercises() {
        try {
            jsonWriter.open();
            jsonWriter.write(exercises);
            jsonWriter.close();
            EventLog.getInstance().logEvent(new Event("Saved Exercises to " + JSON_STORE));
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }





}
