package model;

// Represents an exercise with a name and necessary equipment
public class Exercise {
    private String name;             // name of the exercise
    private String equipment;        // necessary equipment

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