package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunTest {
    Run run;
    Workout workout;

    @BeforeEach
    public void setUp() {
        run = new Run("date", 2, "route");
        workout = new Run("date2", 4, "route2");
    }

    @Test
    public void testPrintWorkout() {
        // notes = "", apparent type Run
        assertEquals(run.printWorkout(), "On date you ran 2km on route: route\r\n");
        // notes = "", apparent type Workout
        assertEquals(workout.printWorkout(), "On date2 you ran 4km on route: route2\r\n");
        // notes != "", apparent type Run
        run.addNotes("test notes");
        assertEquals(run.printWorkout(), "On date you ran 2km on route: route\r\nnotes: test notes\r\n\r\n");
        // notes != "", apparent type Workout
        workout.addNotes("test notes2");
        assertEquals(workout.printWorkout(), "On date2 you ran 4km on route: route2\r\nnotes: test notes2" +
                "\r\n\r\n");
    }
}