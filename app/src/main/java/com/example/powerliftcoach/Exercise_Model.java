package com.example.powerliftcoach;

public class Exercise_Model {

    // data
    Exercises exercise;
    int[] weight;
    double curWeight;
    double readyScore;
    double readyAve;
    String email;
    String daySelected;
    String exType;

    public Exercise_Model(Exercises exercise, int[] weight, double curWeight, double readyScore, double readyAve, String email, String daySelected, String exType) {
        // initialize all data from parameters in constructor
        this.exercise = exercise;
        this.weight = weight;
        this.curWeight = curWeight;
        this.readyScore = readyScore;
        this.readyAve = readyAve;
        this.email = email;
        this.daySelected = daySelected;
        this.exType = exType;
    }
}
