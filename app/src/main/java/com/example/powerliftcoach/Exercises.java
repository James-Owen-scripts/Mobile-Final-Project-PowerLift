package com.example.powerliftcoach;

public class Exercises {
    private String exName;
    private int[] repRange;
    private int tenRepMax = 0;
    private int oneRepMax = 0;

    public Exercises(String exName, int[] repRange, int tenRepMax, int oneRepMax) {
        this.exName = exName;
        this.repRange = repRange;
        this.tenRepMax = tenRepMax;
        this.oneRepMax = oneRepMax;
    }

    public String getExName() {
        return exName;
    }

    public int[] getRepRange() {
        return repRange;
    }

    public int getTenRepMax() {
        return tenRepMax;
    }

    public int getOneRepMax() {
        return oneRepMax;
    }

    public void setExName(String exName) {
        this.exName = exName;
    }

    public void setRepRange(int[] repRange) {
        this.repRange = repRange;
    }

    public void setTenRepMax(int tenRepMax) {
        this.tenRepMax = tenRepMax;
    }

    public void setOneRepMax(int oneRepMax) {
        this.oneRepMax = oneRepMax;
    }
}
