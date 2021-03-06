package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutBankTest {
    WorkoutBank wb;
    Workout workout;
    Workout workout2;

    @BeforeEach
    public void setup() {
        wb = new WorkoutBank("test");
        workout = new Run("03/03/21", 5, "seawall");
        workout2 = new Circuit("testcircuit", "03/04/21", 3);
    }

    @Test
    public void testAddWorkoutTest() {
        wb.addWorkout(workout);
        assertEquals(wb.getWorkoutBank().size(), 1);
        wb.addWorkout(workout2);
        assertEquals(wb.getWorkoutBank().size(), 2);
    }

    @Test
    public void testLookup() {
        // empty bank
        assertEquals(wb.lookup("testcircuit"), "No matches found.");
        // non-empty, name match found
        wb.addWorkout(workout);
        wb.addWorkout(workout2);
        assertEquals(wb.lookup("testcircuit"), workout2.printWorkout());
        // non-empty, date match found
        assertEquals(wb.lookup("03/03/21"), workout.printWorkout());
        // non-empty, no match
        assertEquals(wb.lookup("zzz"), "No matches found.");
    }

    @Test
    public void testNumWorkouts() {
        assertEquals(wb.numWorkouts(), 0);
        wb.addWorkout(workout);
        assertEquals(wb.numWorkouts(), 1);
        wb.addWorkout(workout2);
        assertEquals(wb.numWorkouts(), 2);
    }
}
