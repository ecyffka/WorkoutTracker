package model;

// Represents a run (a subclass of Workout) with a distance and route, along with all properties from Workout.
public class Run extends Workout {
    private Integer distance;     // distance ran
    private String route;         // running route

    public Run(String date, Integer distance, String route) {
        this.date = date;
        this.distance = distance;
        this.route = route;
        this.notes = "";
    }

    // GETTERS
    public Integer getDistance() {
        return this.distance;
    }

    public String getRoute() {
        return this.route;
    }

    // EFFECTS: print formatted summary of run
    public String printWorkout() {
        String runString = "On " + getDate() + " you ran " + getDistance() + "km on route: " + getRoute();
        if (!this.notes.equals("")) {
            runString += "\r\nnotes: " + getNotes();
        }
        return runString;
    }
}