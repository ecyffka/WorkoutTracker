package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkoutTest {
    Workout runWorkout;
    Workout circuitWorkout;
    Run run;
    Circuit circuit;

    @BeforeEach
    public void setUp() {
        runWorkout = new Run("date",1,"route");
        circuitWorkout = new Circuit("name2","date2",2);
        run = new Run("date3",3,"route3");
        circuit = new Circuit("name4","date4",4);
    }

    @Test
    public void testGetRoute() {
        assertEquals(runWorkout.getRoute(), "route");
    }

    @Test
    public void testGetDistance() {
        assertEquals(runWorkout.getDistance(),1);
    }

    @Test
    public void testGetRounds() {
        assertEquals(circuitWorkout.getRounds(), 2);
    }

    @Test
    public void testAddNotesWR() {
        // apparent type Workout, actual type Run, notes = ""
        runWorkout.addNotes("test note");
        assertEquals(runWorkout.getNotes(), "test note\r\n");
        // apparent type Workout, actual type Run, notes != ""
        runWorkout.addNotes("test note2");
        assertEquals(runWorkout.getNotes(), "test note\r\ntest note2\r\n");
    }

    @Test
    public void testAddNotesWC() {
        // apparent type Workout, actual type Circuit, notes = ""
        circuitWorkout.addNotes("test note");
        assertEquals(circuitWorkout.getNotes(), "test note\r\n");
        // apparent type Workout, actual type Circuit, notes != ""
        circuitWorkout.addNotes("test note2");
        assertEquals(circuitWorkout.getNotes(), "test note\r\ntest note2\r\n");
    }

    @Test
    public void testAddNotesRR() {
        // apparent type Run, actual type Run, notes = ""
        run.addNotes("test note");
        assertEquals(run.getNotes(), "test note\r\n");
        // apparent type Run, actual type Run, notes != ""
        run.addNotes("test note2");
        assertEquals(run.getNotes(), "test note\r\ntest note2\r\n");
    }

    @Test
    public void testAddNotesCC() {
        // apparent type Circuit, actual type Circuit, notes = ""
        circuit.addNotes("test note");
        assertEquals(circuit.getNotes(), "test note\r\n");
        // apparent type Circuit, actual type Circuit, notes != ""
        circuit.addNotes("test note2");
        assertEquals(circuit.getNotes(), "test note\r\ntest note2\r\n");
    }

    @Test
    public void testAddExerciseWC() {
        // apparent type Workout, actual type Circuit, list is blank
        circuitWorkout.addExercise("ex1", "eq1");
        assertEquals(circuitWorkout.getListOfExercises().size(), 1);
        assertEquals(circuitWorkout.getListOfExercises().get(0).getName(), "ex1");
        // apparent type Workout, actual type Circuit, list is not blank
        circuitWorkout.addExercise("ex2", "eq2");
        assertEquals(circuitWorkout.getListOfExercises().size(), 2);
        assertEquals(circuitWorkout.getListOfExercises().get(1).getName(), "ex2");
    }

    @Test
    public void testAddExerciseCC() {
        // apparent type Circuit, actual type Circuit, list is blank
        circuit.addExercise("ex1", "eq1");
        assertEquals(circuit.getListOfExercises().size(), 1);
        assertEquals(circuit.getListOfExercises().get(0).getName(), "ex1");
        // apparent type Circuit, actual type Circuit, list is not blank
        circuit.addExercise("ex2", "eq2");
        assertEquals(circuit.getListOfExercises().size(), 2);
        assertEquals(circuit.getListOfExercises().get(1).getName(), "ex2");
    }
}