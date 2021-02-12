package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Exercise exercise;

    @BeforeEach
    public void setUp() {
        exercise = new Exercise("name", "equipment");
    }

    @Test
    public void testPrintExercise() {
        assertEquals(exercise.printExercise(), "name: equipment\r\n");
    }
}
