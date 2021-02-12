package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircuitTest {
    Circuit circuit;
    Workout workout;

    @BeforeEach
    public void setUp() {
        circuit = new Circuit("name","date",3);
        workout = new Circuit("name2","date2",6);
        circuit.addExercise("ex1a","eq1a");
        workout.addExercise("ex1b", "eq1b");
    }

    @Test
    public void testPrintExercises() {
        // one exercise
        assertEquals(circuit.printExercises(), "ex1a: eq1a\r\n");
        // >1 exercise
        circuit.addExercise("ex2a","eq2a");
        assertEquals(circuit.printExercises(), "ex1a: eq1a\r\nex2a: eq2a\r\n");
    }

    @Test
    public void testPrintWorkout() {
        // notes = "", apparent type Circuit
        assertEquals(circuit.printWorkout(), "On date, you completed 3 rounds of workout name:\r\n" +
                "ex1a: eq1a\r\n");
        // notes != "", apparent type Circuit
        circuit.addNotes("test note");
        assertEquals(circuit.printWorkout(), "On date, you completed 3 rounds of workout name:\r\n" +
                "ex1a: eq1a\r\nNotes: test note\r\n");
        // notes = "", apparent type Workout
        assertEquals(workout.printWorkout(), "On date2, you completed 6 rounds of workout name2:\r\n" +
                "ex1b: eq1b\r\n");
        // notes != "", apparent type Workout
        workout.addNotes("test note2");
        assertEquals(workout.printWorkout(), "On date2, you completed 6 rounds of workout name2:\r\n" +
                "ex1b: eq1b\r\nNotes: test note2\r\n");
    }
}