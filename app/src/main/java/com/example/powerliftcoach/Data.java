package com.example.powerliftcoach;

import android.app.Application;
import android.app.NotificationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// static method to store data while app is running
public class Data {
    public static ArrayList<String> emails = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();

    // Map to set up a key value pair that value locates WorkoutData user alters in app
    public static Map<String,WorkoutData> workoutData = new HashMap<>();
}
