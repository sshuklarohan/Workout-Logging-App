package ui.tabs;

import model.Exercise;
import model.Sets;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Represents a tab of an exercise in the FitTrackGUI, displays users sets and gives options to sort and add sets
public class ExerciseTab extends JPanel {

    private final Exercise exercise;
    private final GridLayout columnLayout = new GridLayout(1,3);
    private final JLabel tweight = new JLabel("WEIGHT");
    private final JLabel treps = new JLabel("REPS");
    private List<Sets> currentLog;
    private JButton button;
    private JTextField input1;
    private JTextField input2;



    //EFFECTS: creates a new exercise tab in the FItTrackGui, stores the given exercise, and its unsorted log
    //         as the current log, sets the screen layout as a grid and prints all info to be stored on the page
    public ExerciseTab(Exercise e) {
        this.exercise = e;
        this.currentLog = e.getLog();
        this.setLayout(new GridLayout(40,1));
        printScreen();

    }


    //MODIFIES: this
    //EFFECTS: adds the headers at the top of the screen, adds the log of all sets, adds button to add a set,
    // and adds the button to sort sets
    private void printScreen() {
        addTitle();
        addSets();
        addSetButton();
        orderButton();
    }

    //MODIFIES: this
    //EFFECTS: Adds titles for "Sets" and "Weight rows to the screen
    private void addTitle() {
        JLabel empty = new JLabel("");
        JPanel column = new JPanel(columnLayout);
        column.add(tweight);
        column.add(treps);
        column.add(empty);
        this.add(column);
    }


    //MODIFIES: this
    //EFFECTS: Adds a column for every set in log containing "weight" "reps" and a button to delete the set
    private void addSets() {
        for (int i = 0; i < currentLog.size(); i++) {
            JPanel columnn = new JPanel(columnLayout);
            Sets s = currentLog.get(i);
            JLabel weight = new JLabel(s.getWeight() + "          ");
            JLabel reps = new JLabel(s.getReps() + "");
            JButton b = new JButton("x");
            b.setBackground(Color.red);
            columnn.add(weight);
            columnn.add(reps);
            columnn.add(b);
            this.add(columnn);


            b.addActionListener(e -> {
                exercise.removeSet(s);
                this.remove(columnn);
                this.revalidate();
                this.repaint();
            });

        }
    }


    //MODIFIES: this
    //EFFECTS: adds a button that when pressed opens a window that takes user input to add a set
    private void addSetButton() {
        JButton add = new JButton("+");
        add.setBackground(Color.green);
        this.add(add);

        add.addActionListener(e -> {
            takeInput();
        });
    }


    //MODIFIES: this
    //EFFECTS: creates a frame that takes the users input for a new set
    //         and adds the set to the users current sets upon button input
    private void takeInput() {
        JFrame frame = createFrame();

        button.addActionListener(e -> {
            exercise.addSet(new Sets(Integer.parseInt(input1.getText()),Integer.parseInt(input2.getText())));
            frame.dispose();
            this.removeAll();
            printScreen();
            this.revalidate();
            this.repaint();
        });


    }


    //MODIFIES: this
    //EFFECTS: creates frame creates a frame that takes the users input for a new set
    private JFrame createFrame() {
        JFrame frame = new JFrame("Add Set");
        JPanel panel = new JPanel();
        button = new JButton("Enter");
        panel.setLayout(new GridLayout(3,1));
        JPanel inputpanel1 = new JPanel(columnLayout);
        input1 = new JTextField(10);
        inputpanel1.add(tweight);
        inputpanel1.add(input1);
        JPanel inputpanel2 = new JPanel(columnLayout);
        input2 = new JTextField(10);
        inputpanel2.add(treps);
        inputpanel2.add(input2);
        panel.add(inputpanel1);
        panel.add(inputpanel2);
        panel.add(button);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        return frame;
    }


    //MODIFIES: this
    //EFFECTS: adds button that lets user toggle between a log that is sorted by date added or sorted by highest 1rm
    private void orderButton() {
        JButton button = new JButton("Sort by...");
        JPanel panel = new JPanel(new GridLayout(1,2));
        JComboBox<String> printCombo = new JComboBox<>();
        printCombo.addItem("");
        printCombo.addItem("Date");
        printCombo.addItem("One rep Max");
        panel.add(button);
        panel.add(printCombo);
        add(panel);

        button.addActionListener(e -> {
            String selected = (String) printCombo.getSelectedItem();


            if (selected.equals("Date")) {
                currentLog = exercise.getLog();
                this.removeAll();
                printScreen();
            } else if (selected.equals("One rep Max")) {
                currentLog = exercise.getSortedLog();
                this.removeAll();
                printScreen();
            }
        });

    }


}
