package model;

import java.util.List;

public abstract class Workout {
    protected String date;
    protected String notes;
    protected List<Exercise> listOfExercises;

    // GETTERS
    public String getDate() {
        return this.date;
    }

    public String getNotes() {
        return this.notes;
    }

    public List<Exercise> getListOfExercises() {
        return this.listOfExercises;
    }

    // MODIFIES: this
    // EFFECTS: adds note to workout
    public void addNotes(String n) {
        this.notes += (n + "\r\n");
    }

    // REQUIRES: object actual type must be Circuit
    // MODIFIES: this
    // EFFECTS: adds exercise to workout's list of exercises
    public void addExercise(String name, String equipment) {
        Exercise e = new Exercise(name, equipment);
        this.listOfExercises.add(e);
    }

    public abstract String printWorkout();
}