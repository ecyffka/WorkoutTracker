package model;

import java.util.ArrayList;

public class Circuit extends Workout {
    private String name;
    private Integer rounds;

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
