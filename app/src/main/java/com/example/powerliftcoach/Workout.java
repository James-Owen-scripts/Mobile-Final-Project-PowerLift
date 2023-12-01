package com.example.powerliftcoach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

public class Workout extends AppCompatActivity {

    Button backBtn, completeWorkoutBtn;

    ArrayList<Exercise_Model> exerciseModels = new ArrayList<>();

    private WorkoutData userData = new WorkoutData("John Doe");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        RecyclerView exercises = findViewById(R.id.workout_rec_view);
        backBtn = findViewById(R.id.back_btn);
        completeWorkoutBtn = findViewById(R.id.complete_workout_btn);

        // get Intent
        double curWeight;
        double readyScore;
        double readyAve;
        String email;
        String daySelected;

        Intent workoutIntent = getIntent();
        curWeight = workoutIntent.getDoubleExtra("curAveWeight",0.0);
        readyScore = workoutIntent.getDoubleExtra("readyScore",0.0);
        readyAve = workoutIntent.getDoubleExtra("curAveReadiness",0.0);
        email = workoutIntent.getStringExtra("account");
        daySelected = workoutIntent.getStringExtra("dayKey");
        userData = Data.workoutData.get(email);

        // initialize exercises
        String[] exList;
        Map<String, Exercises> dayEx;
        // day 1 exercises:
        //      exercise list: Lat1, Lat2, Quad, Shoulder, Hamstring, Bicep, Calve
        if (daySelected.equals("Day 1 - Monday")) {
            exList = new String[]{"Lat1", "Lat2", "Quad", "Shoulder", "Hamstring", "Bicep", "Calve"};
            dayEx = userData.day1Exercises;
        }
        // day 2 exercises:
        //      exercise list: Bench, Shoulder, Chest, Tricep, Bicep, Ab
        else if (daySelected.equals("Day 2 - Wednesday")) {
            exList = new String[]{"Bench", "Shoulder", "Chest", "Tricep", "Bicep", "Ab"};
            dayEx = userData.day2Exercises;
        }
        // day 3 exercises:
        //      exercise list: Deadlift, Squat, Hamstring, Quad, Ab
        else if (daySelected.equals("Day 3 - Thursday")) {
            exList = new String[]{"Deadlift", "Squat", "Hamstring", "Quad", "Ab"};
            dayEx = userData.day3Exercises;
        }
        // day 4 exercises:
        //      exercise list: Bench1, Lat1, Shoulder, Bench2, Lat2, Tricep, Bicep
        else if (daySelected.equals("Day 4 - Friday")) {
            exList = new String[]{"Bench1", "Lat1", "Shoulder", "Bench2", "Lat2", "Tricep", "Bicep"};
            dayEx = userData.day4Exercises;
        }
        // day 5 exercises:
        //      exercise list: Squat, Deadlift, Quad, Hamstring, Calve
        else {
            exList = new String[]{"Squat", "Deadlift", "Quad", "Hamstring", "Calve"};
            dayEx = userData.day5Exercises;
        }
        // weight temp to hold the weight of the majority or the user reps they will do
        int weightTemp;
        // get completed workouts to find how difficult each exercise should be depending on block
        int completedWorkouts = userData.getCompletedWorkouts();
        // exercise difficulty depending on which week of the block the user has completed
        double workoutDiff[] = {0.75, 0.75, 0.80, 0.80};
        // compound exercise (squat, bench, deadlift) difficulty depending on which week of the block user has completed
        double workoutDiffCompound[] = {0.85, 0.90, 0.95, 1.0};
            // iterate through each exercise
        for (String key : exList) {
            Exercises value = dayEx.get(key);
            // initialize the weights
            // check to see if one rep max is greater than the 10 rep max
            int tenReps = 10;
            if (value.getOneRepMax() > (value.getTenRepMax() / ((1.0278) - (0.0278 * tenReps)))) {

                // Calculate current exercise projected rep count depending on there rep range set count and entered readiness
                weightTemp = (int) ((value.getOneRepMax() * ((1.0278) - (0.0278 * value.getRepRange()[userData.currentBlockNum]))) * (readyScore / 20) * workoutDiff[(userData.getCurrentWeek() - 1 )%4]);
            }
            // else statement executed if 10 rep max is greater than 1 rep max
            else {
                // Calculate current exercise projected rep count depending on there rep range set count and entered readiness
                double temp = value.getTenRepMax() * ((1.0278) - (0.0278 * value.getRepRange()[userData.currentBlockNum]));
                temp /= ((1.0278) - (0.0278 * 10));
                weightTemp = (int) (temp * (readyScore / 20) * workoutDiff[(userData.getCurrentWeek() - 1 )%4]);
            }
            // weight values in gyms are multiples of 5 only
            weightTemp = weightTemp - (weightTemp % 5); // this line makes sure the weight is divisible by 5

            // weight array to store the weight for each set
            int[] Weight = new int[4];

            // for loop to add each weight to Weight array
            for (int i = 0; i < 4; i++) {

                // Set top set for compound movements
                if(i == 0 && (key.equals("Squat") || key.equals("Squat1") || key.equals("Squat2") ||
                        key.equals("Bench") || key.equals("Bench1") || key.equals("Bench2") ||
                        key.equals("Deadlift") || key.equals("Deadlift1") || key.equals("Deadlift2"))) {
                    // Math for top set
                    Weight[i] = (int)((weightTemp/workoutDiff[(userData.getCurrentWeek() - 1 )%4]) * workoutDiffCompound[(userData.getCurrentWeek() - 1 )%4]);
                    Weight[i] = Weight[i] - (Weight[i] % 5);
                    continue;
                }
                // add weight to array
                Weight[i] = weightTemp;
            }
            exerciseModels.add(new Exercise_Model(value, Weight, curWeight, readyScore, readyAve, email, daySelected, key));
        }

        // create and add exercises to recycled list adapter
        ExAdapter exAdapter = new ExAdapter(this, exerciseModels);
        exercises.setAdapter(exAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        exercises.setLayoutManager(linearLayoutManager);

        // back button to return to user profile without user completing workout
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Workout.this, ProfileAndDailyTracker.class);
                i.putExtra("account", email);
                startActivity(i);
            }
        });

        // complete workout button completes users workouts and sets all changed data
        completeWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // assign all data
                Data.workoutData.get(email).setAverageReadinessScore(readyAve);
                Data.workoutData.get(email).setAverageBodyWeight(curWeight);
                Data.workoutData.get(email).setCompletedWorkouts(1 + userData.getCompletedWorkouts());
                // submit that the day has been completed
                boolean workoutArr[] = userData.getCompleted();
                if(daySelected.equals("Day 1 - Monday")) workoutArr[0] = true;
                else if (daySelected.equals("Day 2 - Wednesday")) workoutArr[1] = true;
                else if (daySelected.equals("Day 3 - Thursday")) workoutArr[2] = true;
                else if (daySelected.equals("Day 4 - Friday")) workoutArr[3] = true;
                else if (daySelected.equals("Day 5 - Sunday")) workoutArr[4] = true;
                // add array to static array
                Data.workoutData.get(email).setCompleted(workoutArr);

                // intent to return user to profile
                Intent i = new Intent(Workout.this, ProfileAndDailyTracker.class);
                i.putExtra("account", email);
                startActivity(i);
            }
        });
    }
}