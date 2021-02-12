package model;

import java.util.List;

public class Exercise {
    private String name;
    private Integer value;
    private String equipment;

    public Exercise(String name, String equipment) {
        this.name = name;
        this.equipment = equipment;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getEquipment() {
        return this.equipment;
    }

    // methods
    public String printExercise() {
        return this.name + ": " + this.equipment + "\r\n";
    }
}
