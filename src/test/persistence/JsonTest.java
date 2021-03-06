package persistence;

import model.Exercise;
import model.Workout;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonTest {
    protected void checkWorkoutRun(String name, String date, String notes, Integer distance, String route,
                                   Workout workout) {
        assertEquals(name, workout.getName());
        assertEquals(date, workout.getDate());
        assertEquals(notes, workout.getNotes());
        assertEquals(distance, workout.getDistance());
        assertEquals(route, workout.getRoute());
    }

    protected void checkWorkoutCircuit(String name, String date, String notes, Integer rounds,
                                       List<Exercise> listOfExercise, Workout workout) {
        assertEquals(name, workout.getName());
        assertEquals(date, workout.getDate());
        assertEquals(notes, workout.getNotes());
        assertEquals(rounds, workout.getRounds());
        assertEquals(listOfExercise, workout.getListOfExercises());
    }
}
