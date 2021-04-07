package ui;

import model.Circuit;
import model.Workout;
import model.WorkoutBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the GUI page for logging a circuit workout
public class CircuitPanel extends JPanel {
    private JPanel panel;
    private GroupLayout layout;
    private JLabel name;
    private JTextField uname;
    private JLabel date;
    private JTextField udate;
    private JLabel rounds;
    private JTextField urounds;
    private JLabel num;
    private JTextField unum;
    private JLabel notes;
    private JTextArea unotes;
    private JButton addExercises;
    private JLabel exercise;
    private JTextField uexercise;
    private JLabel equipment;
    private JTextField uequipment;
    private JButton next;
    private JButton done;
    private JTextArea summary;
    private Workout circuit;
    private WorkoutBank log;
    private Integer exerciseCount;

    // EFFECTS: sets up circuit entry form
    public CircuitPanel(WorkoutBank log) {
        this.log = log;
        panel = new JPanel();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        setFields();
        setExerciseFields();
        summary = new JTextArea();
        summary.setVisible(false);
        setHorizontal();
        setVertical();
        add(panel, BorderLayout.EAST);
        validate();
    }

    // MODIFIES: this
    // EFFECTS: sets up fields for circuit log form
    public void setFields() {
        name = new JLabel("Name");
        uname = new JTextField(29);
        date = new JLabel("Date");
        udate = new JTextField();
        rounds = new JLabel("Rounds");
        urounds = new JTextField();
        num = new JLabel("# Exercises");
        unum = new JTextField();
        notes = new JLabel("Notes");
        unotes = new JTextArea();
        addExercises = new JButton("Add Exercises");
        addExercises.addActionListener(new ClickHandler());
    }

    // MODIFIES: this
    // EFFECTS: sets up fields for exercise log form
    public void setExerciseFields() {
        exercise = new JLabel("Exercise");
        exercise.setVisible(false);
        uexercise = new JTextField();
        uexercise.setVisible(false);
        equipment = new JLabel("Equipment");
        equipment.setVisible(false);
        uequipment = new JTextField();
        uequipment.setVisible(false);
        next = new JButton("Next Exercise");
        next.setVisible(false);
        next.addActionListener(new NextHandler());
        done = new JButton("Submit");
        done.setVisible(false);
        done.addActionListener(new DoneHandler());
    }

    // MODIFIES: this
    // EFFECTS: sets horizontal groupings for page components
    public void setHorizontal() {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(name).addComponent(date).addComponent(rounds)
                                .addComponent(num).addComponent(notes).addComponent(exercise)
                                .addComponent(equipment))

                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(uname).addComponent(udate).addComponent(urounds)
                                .addComponent(unum).addComponent(unotes).addComponent(addExercises)
                                .addComponent(uexercise).addComponent(uequipment).addComponent(next)
                                .addComponent(done).addComponent(summary)));
    }

    // MODIFIES: this
    // EFFECTS: sets vertical groups for page components
    public void setVertical() {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(name).addComponent(uname))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(date).addComponent(udate))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(rounds).addComponent(urounds))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(num).addComponent(unum))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(notes).addComponent(unotes))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(addExercises))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(exercise).addComponent(uexercise))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(equipment).addComponent(uequipment))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(next))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(done))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(summary)));
    }

    // handles mouse clicks for 'Add Exercises' button
    private class ClickHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: initiates exercise log component when 'Add Exercises' button is clicked
        public void actionPerformed(ActionEvent e) {
            exerciseCount = Integer.parseInt(unum.getText());
            if (e.getSource() == addExercises) {
                circuit = new Circuit(uname.getText(), udate.getText(), Integer.parseInt(urounds.getText()));
                circuit.addNotes(unotes.getText());
                exercise.setVisible(true);
                uexercise.setVisible(true);
                equipment.setVisible(true);
                uequipment.setVisible(true);
                next.setVisible(true);
                if (exerciseCount == 1) {
                    next.setVisible(false);
                    done.setVisible(true);
                }
            }
        }
    }

    // handles mouse clicks for 'Submit' button
    private class DoneHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: logs circuit and prints out a summary when 'Submit' button is clicked.
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == done) {
                circuit.addExercise(uexercise.getText(), uequipment.getText());
                log.addWorkout(circuit);
                summary.setText(circuit.printWorkout());
                summary.setVisible(true);
                validate();
            }
        }
    }

    // handles mouse clicks for 'Next Exercise' button
    private class NextHandler implements ActionListener {

        @Override
        // MODIFIES: this
        // EFFECTS: adds exercise to list when 'next' button is clicked
        public void actionPerformed(ActionEvent e) {
            circuit.addExercise(uexercise.getText(), uequipment.getText());
            uexercise.setText("");
            uequipment.setText("");
            if (exerciseCount == 2) {
                next.setVisible(false);
                done.setVisible(true);
            }
            exerciseCount--;
        }
    }
}
