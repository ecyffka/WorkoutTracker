package ui;

import model.Circuit;
import model.Run;
import model.Workout;
import model.WorkoutBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Workout Tracker application
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class WorkoutTracker {
    WorkoutBank workoutbank;
    private static final String JSON_STORE = "./data/workoutbank.json";
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs workout tracker app
    public WorkoutTracker() {
        workoutbank = new WorkoutBank("Test WorkoutBank");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        handleChoice();
    }

    // EFFECTS: main menu, processes user choice
    public void handleChoice() {
        System.out.println("Welcome to the very greatest workout tracker EVER!\r\n"
                + "Choose one of the following:\r\n1: open saved workout log\r\n2: log circuit\r\n3: log run\r\n"
                + "4: look up a previously logged workout\r\n5: save and quit");
        Scanner choiceScan = new Scanner(System.in);
        Integer choice = choiceScan.nextInt();
        switch (choice) {
            case 1:
                loadWorkoutBank();
            case 2:
                logCircuit();
            case 3:
                logRun();
            case 4:
                lookupWorkout();
            case 5:
                saveWorkoutBank();
                System.exit(-1);
        }
    }

    // MODIFIES: run, workoutbank
    // EFFECTS: processes user input (re: logging run data)
    public void logRun() {
        System.out.println("HOORAY YOU RAN!! What's the date? (mm/dd/yy)");
        Scanner dateScan = new Scanner(System.in);
        String date = dateScan.nextLine();
        System.out.println("And the distance (km)?");
        Scanner distScan = new Scanner(System.in);
        Integer distance = distScan.nextInt();
        System.out.println("Where did you run?");
        Scanner routeScan = new Scanner(System.in);
        String route = routeScan.nextLine();
        Workout run = new Run(date, distance, route);
        addNotes(run);
        System.out.println("Awesome!\r\n" + run.printWorkout());
        workoutbank.addWorkout(run);
        handleChoice();
    }

    // MODIFIES: circuit, workoutbank
    // EFFECTS: processes user input (re: logging circuit data)
    public void logCircuit() {
        System.out.println("Woohah! Work it out with your bad self! What do you want to call your workout?");
        Scanner nameScan = new Scanner(System.in);
        String name = nameScan.nextLine();
        System.out.println("What's the date? (mm/dd/yy)");
        Scanner dateScan = new Scanner(System.in);
        String date = dateScan.nextLine();
        System.out.println("How many rounds?");
        Scanner roundsScan = new Scanner(System.in);
        Integer rounds = roundsScan.nextInt();
        Workout circuit = new Circuit(name, date, rounds);
        addExercises(circuit);
        addNotes(circuit);
        System.out.println("YAY FOR YOUUUUU!\r\n" + circuit.printWorkout());
        workoutbank.addWorkout(circuit);
        handleChoice();
    }

    // MODIFIES: w
    // EFFECTS: processes user input (re: adding exercises to workout)
    public static void addExercises(Workout w) {
        System.out.println("How many exercises in your circuit?");
        Scanner numberScan = new Scanner(System.in);
        Integer number = numberScan.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Name of exercise " + (i + 1) + "?");
            Scanner nameScan = new Scanner(System.in);
            String name = nameScan.nextLine();
            System.out.println("equipment used?");
            Scanner equipScan = new Scanner(System.in);
            String equip = equipScan.nextLine();
            w.addExercise(name, equip);
        }
    }

    // MODIFIES: w
    // EFFECTS: processes user input (re: adding notes to workout)
    public void addNotes(Workout w) {
        System.out.println("Want to add any notes? (yes or no)");
        Scanner addNoteScan = new Scanner(System.in);
        String addNote = addNoteScan.nextLine();
        if (addNote.equals("yes")) {
            System.out.println("Thoughts? Feeeeeleeeeens??");
            Scanner noteScan = new Scanner(System.in);
            String note = noteScan.nextLine();
            w.addNotes(note);
        }
    }

    // EFFECTS: search workout bank for a workout by name or date
    public void lookupWorkout() {
        System.out.println("Enter in the name OR date (mm/dd/yy) of the workout you're trying to find:");
        Scanner searchTermScan = new Scanner(System.in);
        String searchTerm = searchTermScan.nextLine();
        System.out.println(workoutbank.lookup(searchTerm));
        handleChoice();
    }

    // EFFECTS: saves the workout bank to file
    private void saveWorkoutBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(workoutbank);
            jsonWriter.close();
            System.out.println("Saved " + workoutbank.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workout bank from file
    private void loadWorkoutBank() {
        try {
            workoutbank = jsonReader.read();
            System.out.println("Loaded " + workoutbank.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
        handleChoice();
    }
}
