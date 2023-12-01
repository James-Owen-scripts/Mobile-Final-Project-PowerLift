package com.example.powerliftcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ExerciseSwap extends AppCompatActivity {

    Button cancelBtn;
    ListView exercisesToSwap;
    private WorkoutData userData = new WorkoutData("John Doe");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_swap);
        //Initialize button
        cancelBtn = findViewById(R.id.cancel_btn);
        exercisesToSwap = findViewById(R.id.exercsies_options);

        // get intent items
        Intent swapIntent = getIntent();
        double aveWeight = swapIntent.getDoubleExtra("curAveWeight", 0);
        double readyScore = swapIntent.getDoubleExtra("readyScore", 0);
        double readyAve = swapIntent.getDoubleExtra("curAveReadiness",0);
        String email = swapIntent.getStringExtra("account");
        String daySelected = swapIntent.getStringExtra("dayKey");
        String exType = swapIntent.getStringExtra("exTypeKey");
        userData = Data.workoutData.get(email);

        // create list of possible exercises
        ArrayList<String> exArray = new ArrayList<>();
        Exercises[] exList;
        // search through to find the exercises that are related to the ones the user wants to swap
        if(exType.equals("Ab") || exType.equals("Ab1")) {
            exList = userData.getAbs();
        } else if (exType.equals("Bicep") || exType.equals("Bicep1")) {
            exList = userData.getBiceps();
        }
        else if (exType.equals("Bench")) {
            exList = userData.getBench();
        }
        else if (exType.equals("Chest") || exType.equals("Chest1") || exType.equals("Chest2")) {
            exList = userData.getChest();
        }
        else if (exType.equals("Deadlift")) {
            exList = userData.getDeadlift();
        }
        else if (exType.equals("Hamstring")) {
            exList = userData.getHamstrings();
        }
        else if (exType.equals("Lat") || exType.equals("Lat1") || exType.equals("Lat2")) {
            exList = userData.getLats();
        }
        else if (exType.equals("Quad")) {
            exList = userData.getQuads();
        }
        else if (exType.equals("Shoulder")) {
            exList = userData.getShoulders();
        }
        else if (exType.equals("Squat")) {
            exList = userData.getSquat();
        }
        else {
            exList = userData.getTriceps();
        }

        // add names of all related exercises to a String array list
        for( int i = 0; i < exList.length; i++ ) {
            exArray.add(exList[i].getExName());
        }

        // Create arrayAdapter for list
        ArrayAdapter<String> exSwapAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.days, exArray);
        // Set the adapter for the exerciseToSwap list view
        exercisesToSwap.setAdapter(exSwapAdapter);

        // swap exercise
        exercisesToSwap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // replace the exercise in the day to have the swapped workout so future workouts
                // accusing on those days will also have the correct workout list
                if (daySelected.equals("Day 1 - Monday")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Data.workoutData.get(email).day1Exercises.replace(exType, exList[position]);
                    }
                }
                else if (daySelected.equals("Day 2 - Wednesday")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Data.workoutData.get(email).day2Exercises.replace(exType, exList[position]);
                    }
                }
                else if (daySelected.equals("Day 3 - Thursday")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Data.workoutData.get(email).day3Exercises.replace(exType, exList[position]);
                    }
                }
                else if (daySelected.equals("Day 4 - Friday")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Data.workoutData.get(email).day4Exercises.replace(exType, exList[position]);
                    }
                }
                else if (daySelected.equals("Day 5 - Sunday")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Data.workoutData.get(email).day5Exercises.replace(exType, exList[position]);
                    }
                }
                // add intent to go back to workout
                Intent i = new Intent(ExerciseSwap.this, Workout.class);
                i.putExtra("curAveWeight", aveWeight);
                i.putExtra("readyScore", readyScore);
                i.putExtra("curAveReadiness", readyAve);
                i.putExtra("account", email);
                i.putExtra("dayKey", daySelected);
                startActivity(i);
            }
        });

        // cancel swapping exercises
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add intent to go back to workout
                Intent i = new Intent(ExerciseSwap.this, Workout.class);
                i.putExtra("curAveWeight", aveWeight);
                i.putExtra("readyScore", readyScore);
                i.putExtra("curAveReadiness", readyAve);
                i.putExtra("account", email);
                i.putExtra("dayKey", daySelected);
                startActivity(i);
            }
        });
    }
}