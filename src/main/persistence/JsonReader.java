package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.*;
import org.json.*;

// Represents a reader that reads workout bank from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workout bank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workout bank from JSON object and returns it
    private WorkoutBank parseWorkoutBank(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        WorkoutBank wb = new WorkoutBank(name);
        addWorkouts(wb, jsonObject);
        return wb;
    }

    // MODIFIES: wb
    // EFFECTS: parses workouts from JSON object and adds them to workout bank
    private void addWorkouts(WorkoutBank wb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("workoutbank");
        for (Object json : jsonArray) {
            JSONObject nextWorkout = (JSONObject) json;
            addWorkout(wb, nextWorkout);
        }
    }

    // MODIFIES: wb
    // EFFECTS: parses workout from JSON object and adds it to workout bank
    private void addWorkout(WorkoutBank wb, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        if (!name.equals("")) {
            wb.addWorkout(addCircuit(jsonObject));
        } else {
            wb.addWorkout(addRun(jsonObject));
        }
    }

    private Workout addCircuit(JSONObject jsonObject) {
        Workout workout;
        String name = jsonObject.getString("name");
        String date = jsonObject.getString("date");
        String notes = jsonObject.getString("notes");
        Integer rounds = jsonObject.getInt("rounds");
        List<Exercise> listOfExercise = new ArrayList();
        JSONArray arrayOfExercise = jsonObject.getJSONArray("listOfExercises");
        for (Object o : arrayOfExercise) {
            JSONObject jo = (JSONObject) o;
            String nameEx = jo.getString("name");
            String equipment = jo.getString("equipment");
            Exercise e = new Exercise(nameEx, equipment);
            listOfExercise.add(e);
        }
        workout = new Circuit(name, date, rounds);
        workout.setListOfExercises(listOfExercise);
        workout.addNotes(notes);
        return workout;
    }

    private Workout addRun(JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String notes = jsonObject.getString("notes");
        String route = jsonObject.getString("route");
        Integer distance = jsonObject.getInt("distance");
        Workout workout = new Run(date, distance, route);
        workout.addNotes(notes);
        return workout;
    }
}