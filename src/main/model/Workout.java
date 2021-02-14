package model;

import java.util.List;

// Represents the framework of a workout with the date completed, notes, and a list of exercises
// This is an abstract class to be extended by subclasses representing more specific types of workouts
public abstract class Workout {
    protected String date;                        // date workout completed
    protected String notes;                       // notes
    protected List<Exercise> listOfExercises;     // list of exercises

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