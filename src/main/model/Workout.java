package model;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private String name;
    private String date;
    private Integer rounds;
    private String notes;
    private List<Exercise> listOfExercise;

    public Workout(String name, String date, Integer rounds) {
        this.name = name;
        this.date = date;
        this.rounds = rounds;
        this.notes = "";
        listOfExercise = new ArrayList();
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public Integer getRounds() {
        return this.rounds;
    }

    public String getNotes() {
        return this.notes;
    }

    // setters
    public void addNotes(String n) {
        this.notes += n;
    }

    // methods
    public void addExercise(String name, String equipment) {
        Exercise e = new Exercise(name, equipment);
        this.listOfExercise.add(e);
    }

    public String printExercises() {
        String nameList = "";
        for (Exercise e : listOfExercise) {
            nameList = nameList + e.printExercise();
        }
        return nameList;
    }

    public String printWorkout() {
        String output = "On " + getDate() + ", you completed " + getRounds() + " rounds of workout " + getName()
                + ":\r\n" + printExercises();
        if (!this.notes.equals("")) {
            output += "Notes: " + getNotes();
        }
        return output;
    }


}
