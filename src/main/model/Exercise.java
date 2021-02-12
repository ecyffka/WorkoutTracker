package model;

public class Exercise {
    private String name;
    private String equipment;

    public Exercise(String name, String equipment) {
        this.name = name;
        this.equipment = equipment;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getEquipment() {
        return this.equipment;
    }

    // EFFECTS: print formatted exercise and equipment used
    public String printExercise() {
        return this.getName() + ": " + this.getEquipment() + "\r\n";
    }
}