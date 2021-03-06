package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            WorkoutBank wb = new WorkoutBank("Test WorkoutBank");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WorkoutBank wb = new WorkoutBank("Test WorkoutBank");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkoutBank.json");
            writer.open();
            writer.write(wb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkoutBank.json");
            wb = reader.read();
            assertEquals("Test WorkoutBank", wb.getName());
            assertEquals(0, wb.numWorkouts());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WorkoutBank wb = new WorkoutBank("Test WorkoutBank");
            Workout workout = new Run("03/03/21", 5, "seawall");
            Workout workout2 = new Circuit("testcircuit", "03/04/21", 3);
            workout2.addExercise("ex1", "eq1");
            wb.addWorkout(workout);
            wb.addWorkout(workout2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkoutBank.json");
            writer.open();
            writer.write(wb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkoutBank.json");
            wb = reader.read();
            assertEquals("Test WorkoutBank", wb.getName());
            List<Workout> workouts = wb.getWorkoutBank();
            assertEquals(2, workouts.size());
            checkWorkoutRun("", "03/03/21", "\r\n", 5, "seawall", workouts.get(0));
            checkWorkoutCircuit("testcircuit", "03/04/21", "\r\n", 3,
                    workouts.get(1).getListOfExercises(), workouts.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
