package model;

import java.util.Date;

public class Run {
    private String date;
    private Integer distance;
    private String route;
    private String notes;

    public Run(String date, Integer distance, String route) {
        this.date = date;
        this.distance = distance;
        this.route = route;
        this.notes = "";
    }

    //getters
    public String getDate() {
        return this.date;
    }

    public Integer getDistance() {
        return this.distance;
    }

    public String getRoute() {
        return this.route;
    }

    public String getNotes() {
        return this.notes;
    }

    // setter
    public void addNotes(String n) {
        this.notes += n;
    }

    // method
    public String printRun() {
        String runString = "On " + getDate() + " you ran " + getDistance() + "km on route: " + getRoute();
        if (!this.notes.equals("")) {
            runString += "\r\nnotes: " + getNotes();
        }
        return runString;
    }

}
