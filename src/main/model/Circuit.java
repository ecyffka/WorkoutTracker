package model;

import java.util.ArrayList;

// Represents a strength circuit (a subclass of Workout) with a name and number of rounds (times the circuit was
// completed), along with all properties from Workout.
public class Circuit extends Workout {
    private String name;         // name of workout
    private Integer rounds;      // number of times the circuit was completed (rounds)

    public Circuit(String name, String date, Integer rounds) {
        this.name = name;
        this.date = date;
        this.rounds = rounds;
        this.notes = "";
        this.listOfExercises = new ArrayList();
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public Integer getRounds() {
        return this.rounds;
    }

    // REQUIRES: circuit's list of exercises is not blank
    // EFFECTS: prints formatted list of exercises
    public String printExercises() {
        String nameList = "";
        for (Exercise e : listOfExercises) {
            nameList = nameList + e.printExercise();
        }
        return nameList;
    }

    // EFFECTS: prints formatted summary of workout
    public String printWorkout() {
        String circuitString = "On " + getDate() + ", you completed " + getRounds() + " rounds of workout " + getName()
                + ":\r\n" + printExercises();
        if (!this.notes.equals("")) {
            circuitString += "Notes: " + getNotes();
        }
        return circuitString;
    }
}
