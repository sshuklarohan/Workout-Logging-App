package ui.tabs;

import model.Exercise;
import model.Exercises;
import ui.FitTrackGUI;

import javax.swing.*;
import java.awt.*;

//Represents the home screen of the FitTrackGUI, displays an image and lists ll one repmaxes
public class HomeTab extends JPanel {

    private JScrollPane reportPane;
    private JTextArea reportText;
    private Exercises exercises;
    private GridBagConstraints constraints;

    //EFFECTS: Creates a new home tab in the FitTrackUI,with an image and a text box that displays onerepmaxes
    //         upon the press of a button
    public HomeTab(Exercises e) {
        this.exercises = e;
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();

        placeImage();

        placeUpdateButton();

        JPanel reportBlock = new JPanel(new GridLayout(2, 1));
        reportBlock.setSize(50,50);
        reportBlock.setBackground(Color.black);
        reportPane = new JScrollPane(new JTextArea(6, 40));
        reportText = new JTextArea("", 6, 40);
        reportText.setVisible(true);

        reportBlock.add(reportPane);

        constraints.gridy = 10;
        constraints.gridx = 5;

        add(reportBlock, constraints);
        this.setBackground(Color.black);


    }

    //MODIFIES: this
    //EFFECTS: adds an update button that lists the data of your current 1 rep maxes when clicked
    private void placeUpdateButton() {
        JButton b1 = new JButton("View One rep Maxes");
        JPanel buttonRow = new JPanel(new GridLayout());
        buttonRow.add(b1);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            reportText.setText(reportAllMaxes());
            reportPane.setViewportView(reportText);
        });

        constraints.gridy = 5;
        constraints.gridx = 5;
        this.add(buttonRow, constraints);

    }


    //EFFECTS: returns a string that lists all exercises with their corresponding one rep maxes
    private String reportAllMaxes() {
        StringBuilder maxes = new StringBuilder();
        for (Exercise e: exercises.getLog()) {
            maxes.append("\n").append(e.getName() + ": " + e.getOneRepMax());
        }
        return maxes.toString();
    }


    //MODIFIES: this
    //Effects: adds an image from ./data/projectImage.png to home page
    private void placeImage() {

        ImageIcon image = new ImageIcon("./data/projectImage.png");
        JLabel label = new JLabel(image);
        constraints.gridy = 0;
        constraints.gridx = 5;
        this.add(label, constraints);


    }
}
