package ui;

import model.Run;
import model.Workout;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        handleChoice();
    }

    public static void handleChoice() {
        System.out.println("Would you like to log a run or a strength circuit?");
        Scanner choiceScan = new Scanner(System.in);
        String choice = choiceScan.nextLine();
        if (choice.equals("run")) {
            logRun();
        } else if (choice.equals("strength circuit")) {
            logCircuit();
        }
    }

    public static void logRun() {
        Run run;
        System.out.println("Oh no. At least it's over now! What's the date?");
        Scanner dateScan = new Scanner(System.in);
        String date = dateScan.nextLine();
        System.out.println("And the distance?");
        Scanner distScan = new Scanner(System.in);
        Integer distance = distScan.nextInt();
        System.out.println("Where did you run?");
        Scanner routeScan = new Scanner(System.in);
        String route = routeScan.nextLine();
        run = new Run(date, distance, route);
        addRunNotes(run);
        System.out.println("Awesome!\r\n" + run.printRun());
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
        Workout workout = new Workout(name, date, rounds);
        addExercises(workout);
        addCircuitNotes(workout);
        System.out.println("Great!\r\n" + workout.printWorkout());
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

    public static void addRunNotes(Run r) {
        System.out.println("Want to add any notes?");
        Scanner addNoteScan = new Scanner(System.in);
        String addNote = addNoteScan.nextLine();
        if (addNote.equals("yes")) {
            System.out.println("Thoughts? Feeeeeleeeeens??");
            Scanner noteScan = new Scanner(System.in);
            String note = noteScan.nextLine();
            r.addNotes(note);
        }
    }

    public static void addCircuitNotes(Workout w) {
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
