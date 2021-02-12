package ui;

import model.Circuit;
import model.Run;
import model.Workout;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        handleChoice();
    }

    public static void handleChoice() {
        System.out.println("Would you like to log a run or a circuit?");
        Scanner choiceScan = new Scanner(System.in);
        String choice = choiceScan.nextLine();
        if (choice.equals("run")) {
            logRun();
        } else if (choice.equals("circuit")) {
            logCircuit();
        }
    }

    public static void logRun() {
        System.out.println("Oh no. At least it's over now! What's the date?");
        Scanner dateScan = new Scanner(System.in);
        String date = dateScan.nextLine();
        System.out.println("And the distance (km)?");
        Scanner distScan = new Scanner(System.in);
        Integer distance = distScan.nextInt();
        System.out.println("Where did you run?");
        Scanner routeScan = new Scanner(System.in);
        String route = routeScan.nextLine();
        Workout run = new Run(date, distance, route);
        addNotes(run);
        System.out.println("Awesome!\r\n" + run.printWorkout());
        addAnother();
    }

    public static void logCircuit() {
        System.out.println("Woohah! Work it out with your bad self! What do you want to call your workout?");
        Scanner nameScan = new Scanner(System.in);
        String name = nameScan.nextLine();
        System.out.println("What's the date?");
        Scanner dateScan = new Scanner(System.in);
        String date = dateScan.nextLine();
        System.out.println("How many rounds?");
        Scanner roundsScan = new Scanner(System.in);
        Integer rounds = roundsScan.nextInt();
        Workout circuit = new Circuit(name, date, rounds);
        addExercises(circuit);
        addNotes(circuit);
        System.out.println("YAY FOR YOUUUUU!\r\n" + circuit.printWorkout());
        addAnother();
    }

    public static void addExercises(Workout w) {
        System.out.println("How many exercises in your circuit?");
        Scanner numberScan = new Scanner(System.in);
        Integer number = numberScan.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Name of exercise " + (i + 1) + "?");
            Scanner nameScan = new Scanner(System.in);
            String name = nameScan.nextLine();
            System.out.println("equipment used?");
            Scanner equipScan = new Scanner(System.in);
            String equip = equipScan.nextLine();
            w.addExercise(name, equip);
        }
    }

    public static void addNotes(Workout w) {
        System.out.println("Want to add any notes?");
        Scanner addNoteScan = new Scanner(System.in);
        String addNote = addNoteScan.nextLine();
        if (addNote.equals("yes")) {
            System.out.println("Thoughts? Feeeeeleeeeens??");
            Scanner noteScan = new Scanner(System.in);
            String note = noteScan.nextLine();
            w.addNotes(note);
        }
    }

    public static void addAnother() {
        System.out.println("Want to add another workout?");
        Scanner addResponseScan = new Scanner(System.in);
        String addResponse = addResponseScan.nextLine();
        if (addResponse.equals("yes")) {
            handleChoice();
        } else {
            System.out.println("OK BYE!!!! GOOD THING YOU HAVE TO WORK OUT EVERY DAY FOREVER AND EVER AND EVER.");
        }
    }
}