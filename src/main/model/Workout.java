package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

// Represents the framework of a workout with the date completed, notes, and a list of exercises
// This is an abstract class to be extended by subclasses representing more specific types of workouts
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public abstract class Workout implements Writable {
    protected String name;                        // name of workout
    protected String date;                        // date workout completed
    protected String notes;                       // notes
    protected List<Exercise> listOfExercises;     // list of exercises

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getNotes() {
        return this.notes;
    }

    public List<Exercise> getListOfExercises() {
        return this.listOfExercises;
    }

    // SETTER
    public void setListOfExercises(List<Exercise> l) {
        this.listOfExercises = l;
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

    @Override
    public abstract JSONObject toJson();
}