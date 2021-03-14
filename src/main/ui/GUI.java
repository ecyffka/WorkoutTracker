package ui;

import model.WorkoutBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI extends JFrame {
    public static final int WIDTH = 550;
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

    public GUI() {
        super("Workout Tracker");
        log = new WorkoutBank("Test WorkoutBank");
      //  self = this;
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
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createMenu() {
        JPanel menuArea = new JPanel();
        menuArea.setLayout(new GridLayout(0, 1));
        menuArea.setSize(new Dimension(0, 0));
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

    private class ClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the resize tool
        //          called by the framework when the tool is clicked
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

        public JPanel loadPanel() {
            try {
                log = jsonReader.read();
                System.out.println("Loaded " + log.getName() + " from " + JSON_STORE);
            } catch (IOException n) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            JPanel panel = new JPanel();
            ImageIcon icon = new ImageIcon("./data/corgi.jpg");
            JLabel image = new JLabel();
            image.setIcon(icon);
            panel.add(image);
            return panel;
        }

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

    public static void main(String[] args) {
        new GUI();
    }
}