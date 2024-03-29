package ui;

import model.Run;
import model.Workout;
import model.WorkoutBank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the GUI page for logging a run
public class RunPanel extends JPanel {
    private JPanel panel;
    private GroupLayout layout;
    private JLabel date;
    private JTextField udate;
    private JLabel distance;
    private JTextField udistance;
    private JLabel route;
    private JTextField uroute;
    private JLabel notes;
    private JTextArea unotes;
    private JButton submit;
    private JTextArea summary;
    private Workout run;
    private WorkoutBank log;

    // EFFECTS: sets up run entry form
    public RunPanel(WorkoutBank log) {
        this.log = log;
        panel = new JPanel();
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        date = new JLabel("Date");
        udate = new JTextField(28);
        distance = new JLabel("Distance (km)");
        udistance = new JTextField();
        route = new JLabel("Route");
        uroute = new JTextField();
        notes = new JLabel("Notes");
        unotes = new JTextArea();
        submit = new JButton("Submit");
        submit.addActionListener(new ClickHandler());
        summary = new JTextArea();
        summary.setVisible(false);
        setHorizontal();
        setVertical();
        add(panel, BorderLayout.EAST);
        validate();
    }

    // MODIFIES: this
    // EFFECTS: sets horizontal groups for page components
    public void setHorizontal() {
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(date).addComponent(distance).addComponent(route)
                                .addComponent(notes))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(udate).addComponent(udistance).addComponent(uroute)
                                .addComponent(unotes).addComponent(submit).addComponent(summary)));
    }

    // MODIFIES: this
    // EFFECTS: sets vertical groups for page components
    public void setVertical() {
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(date).addComponent(udate))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(distance).addComponent(udistance))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(route).addComponent(uroute))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(notes).addComponent(unotes))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(submit))
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(summary)));
    }

    // handles mouse clicks for 'Submit' button
    private class ClickHandler implements ActionListener {
        @Override
        // MODIFIES: this
        // EFFECTS: logs run when 'Submit' button is clicked and prints out a summary
        public void actionPerformed(ActionEvent e) {
            run = new Run(udate.getText(), Integer.parseInt(udistance.getText()), uroute.getText());
            run.addNotes(unotes.getText());
            log.addWorkout(run);
            summary.setText(run.printWorkout());
            summary.setVisible(true);
            validate();
        }
    }
}