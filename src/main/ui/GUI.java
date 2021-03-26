package ui;

import model.WorkoutBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// Workout Tracker GUI
// Citation: used Simple Drawing Player as a rough starting point
//    (source: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Starter)
public class GUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 350;

    private JButton load;
    private JButton circuit;
    private JButton run;
    private JButton lookup;
    private JButton exit;
    private JPanel current;
    private WorkoutBank log;
    private static final String JSON_STORE = "./data/workoutbank.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: sets up workout tracker interface
    public GUI() {
        super("Workout Tracker");
        log = new WorkoutBank("Test WorkoutBank");
        current = new JPanel();
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createMenu();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: sets up main menu page
    private void createMenu() {
        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(0, 1));
        add(menuArea, BorderLayout.WEST);

        load = new JButton("Load Saved Workout Log");
        menuArea.add(load);
        load.addActionListener(new ClickHandler());
        circuit = new JButton("Add Circuit");
        menuArea.add(circuit);
        circuit.addActionListener(new ClickHandler());
        run = new JButton("Add Run");
        menuArea.add(run);
        run.addActionListener(new ClickHandler());
        lookup = new JButton("Look Up Saved Workout");
        menuArea.add(lookup);
        lookup.addActionListener(new ClickHandler());
        exit = new JButton("Save & Exit");
        menuArea.add(exit);
        exit.addActionListener(new ClickHandler());
    }

    // handles all mouse clicks on the main menu page
    private class ClickHandler implements ActionListener {

        // MODIFIES: this
        // EFFECTS: directs user to their selected page based on button click
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = new JPanel();
            if (e.getSource() == load) {
                panel = loadPanel();
            }
            if (e.getSource() == circuit) {
                panel = new CircuitPanel(log);
            }
            if (e.getSource() == run) {
                panel = new RunPanel(log);
            }
            if (e.getSource() == lookup) {
                panel = new LookupPanel(log);
            }
            if (e.getSource() == exit) {
                exitPanel();
            }
            remove(current);
            validate();
            current = panel;
            add(panel, BorderLayout.EAST);
            validate();
        }

        // CITATION: image source: https://mymodernmet.com/corgi-facts/
        
        // MODIFIES: this
        // EFFECTS: loads saved workout log and displays confirmation image
        public JPanel loadPanel() {
            try {
                log = jsonReader.read();
                System.out.println("Loaded " + log.getName() + " from " + JSON_STORE);
            } catch (IOException n) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            playSound();
            JPanel panel = new JPanel();
            panel.setBackground(Color.white);
            panel.setSize(400,350);
            ImageIcon icon = new ImageIcon("./data/corgi.jpg");
            JLabel image = new JLabel();
            image.setIcon(icon);
            panel.add(image);
            return panel;
        }

        // CITATION: playSound method adapted from:
        //    http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html,
        //   'dog.wav' from: https://www.freesoundeffects.com/

        // EFFECTS: plays dog bark sound to confirm loading of saved workout log
        public void playSound() {
            try {
                AudioInputStream audioInputStream = AudioSystem
                        .getAudioInputStream(new File("./data/dog.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
        }

        // MODIFIES: this (log)
        // EFFECTS: saves workout log and exits application
        public void exitPanel() {
            try {
                jsonWriter.open();
                jsonWriter.write(log);
                jsonWriter.close();
                System.out.println("Saved " + log.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException n) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
            System.exit(-1);
        }
    }

    // EFFECTS: runs workout tracker interface
    public static void main(String[] args) {
        new GUI();
    }
}