package persistence;

import model.Workout;
import model.WorkoutBank;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutBank wb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkoutBank.json");
        try {
            WorkoutBank wb = reader.read();
            assertEquals("Test WorkoutBank", wb.getName());
            assertEquals(0, wb.numWorkouts());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkoutBank.json");
        try {
            WorkoutBank wb = reader.read();
            assertEquals("Test WorkoutBank", wb.getName());
            List<Workout> workouts = wb.getWorkoutBank();
            assertEquals(2, workouts.size());
            checkWorkoutRun("", "03/03/21", "\r\n", 5, "seawall", workouts.get(0));
            checkWorkoutCircuit("testcircuit", "03/04/21", "\r\n", 3,
                    workouts.get(1).getListOfExercises(), workouts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
