package com.example.powerliftcoach;

import java.util.HashMap;
import java.util.Map;

public class WorkoutData {
    private String name = "John Doe"; // Store users name
    private double averageBodyWeight = 0.0; // stores users average body weight
    private double averageReadinessScore = 0.0; // stores users average readiness
    private String currentBlock = "Hypertrophy"; // stores current block that user is in
    public int currentBlockNum = 0; // stores the number assosiated to the current block 0=hypertorphy, 1 = strength, 2 = power
    private int currentWeek = 1; // stores the current week /16 the user has completed
    private int completedWorkouts = 0; // stores the total number of workouts user has completed
    private boolean[] completed = {false, false, false, false, false}; // completed workouts for the specific week: Monday, Wednesday, Thursday, Friday, Sunday
    // different exercises
    private Exercises[] abs;
    private Exercises[] biceps;
    private Exercises[] bench;
    private Exercises[] calves;
    private Exercises[] chest;
    private Exercises[] deadlift;
    private Exercises[] hamstrings;
    private Exercises[] lats;
    private Exercises[] quads;
    private Exercises[] shoulders;
    private Exercises[] squat;
    private Exercises[] triceps;
    // day exercises
    public Map<String, Exercises> day1Exercises = new HashMap<>();
    public Map<String, Exercises> day2Exercises = new HashMap<>();
    public Map<String, Exercises> day3Exercises = new HashMap<>();
    public Map<String, Exercises> day4Exercises = new HashMap<>();
    public Map<String, Exercises> day5Exercises = new HashMap<>();

    public WorkoutData(String name) {
        this.name = name;

        // initialize all exercises
        abs = new Exercises[] {
                new Exercises("Ab Wheel", new int[]{10, 10, 10}, 0, 0),
                new Exercises("Russian Twists", new int[]{10, 10, 10}, 0, 0),
                new Exercises("V-Ups", new int[]{10, 10, 10}, 0, 0),
                new Exercises("Weighted Situps", new int[]{10, 10, 10}, 0, 0)
        };
        biceps = new Exercises[] {
                new Exercises("DB Alternating Curls", new int[]{10,10,10}, 0, 0),
                new Exercises("Preacher Curls", new int[]{10,10,10}, 0, 0),
                new Exercises("Reverse Barbell Curls", new int[]{10,10,10}, 0, 0),
                new Exercises("Hammer Curls", new int[]{10,10,10}, 0, 0)
        };
        bench = new Exercises[] {
                new Exercises("CloseGrip Bench", new int[]{8,6,5}, 0, 0),
                new Exercises("Comp Bench", new int[]{8,6,5}, 0, 0),
                new Exercises("Incline Bench", new int[]{8,6,5}, 0, 0),
                new Exercises("DB Alternating Press", new int[]{8,8,8}, 0, 0)
        };
        calves = new Exercises[] {
          new Exercises("Bent Knee Calf Raises", new int[]{15,15,15}, 0, 0),
          new Exercises("Calf Raises", new int[]{15,15,15}, 0, 0)
        };
        chest = new Exercises[] {
                new Exercises("Machine Fly", new int[]{10,10,10}, 0, 0),
                new Exercises("Cable Cross Overs", new int[]{10,10,10}, 0, 0)
        };
        deadlift = new Exercises[] {
                new Exercises("Comp Deadlift", new int[]{6,5,4}, 0, 0),
                new Exercises("Halted Deadlift", new int[]{5,5,5}, 0, 0),
                new Exercises("RDL", new int[]{5,5,5}, 0, 0),
                new Exercises("Block Pull", new int[]{5,5,5}, 0, 0)
        };
        hamstrings = new Exercises[] {
                new Exercises("SL Hip Thrusts", new int[]{10,10,10},0,0),
                new Exercises("Hamstring Curls", new int[]{10,10,10}, 0,0)
        };
        lats = new Exercises[] {
                new Exercises("BD Rows", new int[]{10,10,10}, 0, 0),
                new Exercises("Pullups", new int[]{10,10,10}, 0, 0),
                new Exercises("1-Arm Cable Rows", new int[]{10,10,10}, 0,0),
                new Exercises("Cable Pull Overs", new int[]{10,10,10}, 0,0),
                new Exercises("Seated Rows", new int[]{10,10,10}, 0, 0),
                new Exercises("Lat Pulldowns", new int[]{10,10,10}, 0 ,0)
        };
        quads = new Exercises[] {
                new Exercises("Leg Extensions", new int[]{10,10,10}, 0, 0),
                new Exercises("Lunges", new int[]{10,10,10}, 0, 0),
                new Exercises("Reverse Hack Squat", new int[]{10,10,10}, 0, 0)
        };
        shoulders = new Exercises[] {
                new Exercises("Shrugs", new int[]{10,10,10}, 0, 0),
                new Exercises("Face Pulls", new int[]{10,10,10}, 0, 0),
                new Exercises("DB Lateral Raise", new int[]{10,8,8}, 0, 0),
                new Exercises("DB Military Press", new int[]{10,10,10}, 0, 0),
                new Exercises("Leg Extensions", new int[]{10,10,10}, 0, 0),
                new Exercises("Military Press", new int[]{10,8,8}, 0, 0),
                new Exercises("Reverse Fly", new int[]{10,10,10}, 0, 0),
                new Exercises("Machine Lateral Raise", new int[]{10,10,10}, 0, 0)
        };
        squat = new Exercises[] {
                new Exercises("Goblet Squat", new int[]{8,8,8}, 0, 0),
                new Exercises("Leg Press", new int[]{10,10,10}, 0, 0),
                new Exercises("Paused Squat", new int[]{6,6,6}, 0, 0),
                new Exercises("Hack Squat", new int[]{8,8,5}, 0, 0),
                new Exercises("Comp Squat", new int[]{8,5,4}, 0, 0)
        };
        triceps = new Exercises[] {
                new Exercises("1-Arm Tricep Pushdown", new int[]{10,10,10}, 0, 0),
                new Exercises("Pushups", new int[]{10,10,10}, 0, 0),
                new Exercises("Tricep Pushdown", new int[]{10,10,10}, 0, 0),
                new Exercises("Skullcrushers", new int[]{10,10,10}, 0, 0),
                new Exercises("DB Skullcrushers", new int[]{10,10,10}, 0, 0),
        };
        // initialize day 1 exercises
        // exercise list: Lat1, Lat2, Quad, Shoulder, Hamstring, Bicep, Calve
        day1Exercises.put("Lat1", lats[1]);
        day1Exercises.put("Lat2", lats[4]);
        day1Exercises.put("Quad", quads[0]);
        day1Exercises.put("Shoulder", shoulders[6]);
        day1Exercises.put("Hamstring", hamstrings[1]);
        day1Exercises.put("Bicep", biceps[0]);
        day1Exercises.put("Calve", calves[0]);

        // initialize day 2 exercises
        // exercise list: Bench, Shoulder, Chest, Tricep, Bicep, Ab
        day2Exercises.put("Bench", bench[1]);
        day2Exercises.put("Shoulder", shoulders[2]);
        day2Exercises.put("Chest", chest[0]);
        day2Exercises.put("Tricep", triceps[4]);
        day2Exercises.put("Bicep", biceps[2]);
        day2Exercises.put("Ab", abs[0]);

        // initialize day 3 exercises
        // exercise list: Deadlift, Squat, Hamstring, Quad, Ab
        day3Exercises.put("Deadlift", deadlift[0]);
        day3Exercises.put("Squat", squat[2]);
        day3Exercises.put("Hamstring", hamstrings[0]);
        day3Exercises.put("Quad", quads[1]);
        day3Exercises.put("Ab", abs[2]);

        // initialize day 4 exercises
        // exercise list: Bench1, Lat1, Shoulder, Bench2, Lat2, Tricep, Bicep
        day4Exercises.put("Bench1", bench[0]);
        day4Exercises.put("Lat1", lats[5]);
        day4Exercises.put("Shoulder", shoulders[1]);
        day4Exercises.put("Bench2", bench[3]);
        day4Exercises.put("Lat2", lats[3]);
        day4Exercises.put("Tricep", triceps[2]);
        day4Exercises.put("Bicep", biceps[1]);

        // initialize day 5 exeercises
        // exercise list: Squat, Deadlift, Quad, Hamstring, Calve
        day5Exercises.put("Squat", squat[4]);
        day5Exercises.put("Deadlift", deadlift[2]);
        day5Exercises.put("Quad", quads[2]);
        day5Exercises.put("Hamstring", hamstrings[1]);
        day5Exercises.put("Calve", calves[1]);
    }

    // get user name

    public String getName() {
        return name;
    }


    // Setters for all variables

    public void setName(String name) {
        this.name = name;
    }

    public void setAverageBodyWeight(double averageBodyWeight) {
        this.averageBodyWeight = averageBodyWeight;
    }

    public void setAverageReadinessScore(double averageReadinessScore) {
        this.averageReadinessScore = averageReadinessScore;
    }

    public void setCurrentBlock(String currentBlock) {
        this.currentBlock = currentBlock;
    }

    public void setCurrentWeek(int currentWeek) {
        this.currentWeek = currentWeek;
    }

    public void setCompletedWorkouts(int completedWorkouts) {
        this.completedWorkouts = completedWorkouts;
    }

    public void setCompleted(boolean[] completed) {
        this.completed = completed;
    }
    public void setAbs(Exercises[] abs) {
        this.abs = abs;
    }

    public void setBiceps(Exercises[] biceps) {
        this.biceps = biceps;
    }

    public void setBench(Exercises[] bench) {
        this.bench = bench;
    }

    public void setCalves(Exercises[] calves) {
        this.calves = calves;
    }

    public void setChest(Exercises[] chest) {
        this.chest = chest;
    }

    public void setDeadlift(Exercises[] deadlift) {
        this.deadlift = deadlift;
    }

    public void setHamstrings(Exercises[] hamstrings) {
        this.hamstrings = hamstrings;
    }

    public void setLats(Exercises[] lats) {
        this.lats = lats;
    }

    public void setQuads(Exercises[] quads) {
        this.quads = quads;
    }

    public void setShoulders(Exercises[] shoulders) {
        this.shoulders = shoulders;
    }

    public void setSquat(Exercises[] squat) {
        this.squat = squat;
    }

    public void setTriceps(Exercises[] triceps) {
        this.triceps = triceps;
    }


    // Getters for all variables

    public double getAverageBodyWeight() {
        return averageBodyWeight;
    }

    public double getAverageReadinessScore() {
        return averageReadinessScore;
    }

    public String getCurrentBlock() {
        return currentBlock;
    }

    public int getCurrentWeek() {
        return currentWeek;
    }

    public int getCompletedWorkouts() {
        return completedWorkouts;
    }

    public boolean[] getCompleted() {
        return completed;
    }

    public Exercises[] getAbs() {
        return abs;
    }

    public Exercises[] getBiceps() {
        return biceps;
    }

    public Exercises[] getBench() {
        return bench;
    }

    public Exercises[] getCalves() {
        return calves;
    }

    public Exercises[] getChest() {
        return chest;
    }

    public Exercises[] getDeadlift() {
        return deadlift;
    }

    public Exercises[] getHamstrings() {
        return hamstrings;
    }

    public Exercises[] getLats() {
        return lats;
    }

    public Exercises[] getQuads() {
        return quads;
    }

    public Exercises[] getShoulders() {
        return shoulders;
    }

    public Exercises[] getSquat() {
        return squat;
    }

    public Exercises[] getTriceps() {
        return triceps;
    }
}
