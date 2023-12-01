package com.example.powerliftcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FitnessLevel extends AppCompatActivity {
    TextView exName;
    EditText tenRepMax, oneRepMax;
    Button doneBtn;
    private WorkoutData userData = new WorkoutData("John Doe");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_level);
        // initialize values
        tenRepMax = findViewById(R.id.ten_rep_max_value_text);
        oneRepMax = findViewById(R.id.one_rep_max_value_text);
        doneBtn = findViewById(R.id.done_btn);
        exName = findViewById(R.id.exercise_calculate_text);

        // get intent
        // get intent items
        Intent swapIntent = getIntent();
        double aveWeight = swapIntent.getDoubleExtra("curAveWeight", 0);
        double readyScore = swapIntent.getDoubleExtra("readyScore", 0);
        double readyAve = swapIntent.getDoubleExtra("curAveReadiness",0);
        String email = swapIntent.getStringExtra("account");
        String daySelected = swapIntent.getStringExtra("dayKey");
        String exType = swapIntent.getStringExtra("exTypeKey");
        String exNameData = swapIntent.getStringExtra("exNameKey");
        // set exercise name text
        exName.setText(exNameData);
        userData = Data.workoutData.get(email);


        // button click listener
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRepMax(daySelected, email, exType, exNameData);
                // set Intent
                Intent i = new Intent(FitnessLevel.this, Workout.class);
                i.putExtra("curAveWeight", aveWeight);
                i.putExtra("readyScore", readyScore);
                i.putExtra("curAveReadiness", readyAve);
                i.putExtra("account", email);
                i.putExtra("dayKey", daySelected);
                startActivity(i);
            }
        });
    }
    private void setRepMax(String daySelected, String email, String exType, String exNameData) {
        // ten and one rep maxes initialized to 1
        int entered10RM = 0;
        int entered1RM = 0;
        // exercises variable to hold the data to swap
        Exercises newExRep;

        // check if user entered a ten rep max
        if (!tenRepMax.getText().toString().equals("")) {
            // try to parse user data into 10 rep max
            try {
                entered10RM = Integer.parseInt(tenRepMax.getText().toString());
            }
            catch (NumberFormatException e) {
                // make sure 10rm = 0 if no data entered
                entered10RM = 0;
            }
        }
        // check if user entered a one rep max
        if (!oneRepMax.getText().toString().equals("")) {
            // try to parse user data into 1 rep max
            try {
                entered1RM = Integer.parseInt(oneRepMax.getText().toString());
            }
            catch (NumberFormatException e) {
                // make sure 1rm = 0 if no data entered
                entered1RM = 0;
            }
        }
        // check if day is monday to add maxes to the correct list
        if (daySelected.equals("Day 1 - Monday")) {
            newExRep = userData.day1Exercises.get(exType);
            if( entered10RM != 0) {
                newExRep.setTenRepMax(entered10RM);
            }
            if (entered1RM != 0) {
                newExRep.setOneRepMax(entered1RM);
            }
            // set the data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Data.workoutData.get(email).day1Exercises.replace(exType, newExRep);
            }
        }
        // check if day is Wednesday to add maxes to the correct list
        else if (daySelected.equals("Day 2 - Wednesday")) {
            newExRep = userData.day2Exercises.get(exType);
            if( entered10RM != 0) {
                newExRep.setTenRepMax(entered10RM);
            }
            if (entered1RM != 0) {
                newExRep.setOneRepMax(entered1RM);
            }
            // set the data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Data.workoutData.get(email).day2Exercises.replace(exType, newExRep);
            }
        }
        // check if day is Thursday to add maxes to the correct list
        else if (daySelected.equals("Day 3 - Thursday")) {
            newExRep = userData.day3Exercises.get(exType);
            if( entered10RM != 0) {
                newExRep.setTenRepMax(entered10RM);
            }
            if (entered1RM != 0) {
                newExRep.setOneRepMax(entered1RM);
            }
            // set the data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Data.workoutData.get(email).day3Exercises.replace(exType, newExRep);
            }
        }
        // check if day is Friday to add maxes to the correct list
        else if (daySelected.equals("Day 4 - Friday")) {
            newExRep = userData.day4Exercises.get(exType);
            if( entered10RM != 0) {
                newExRep.setTenRepMax(entered10RM);
            }
            if (entered1RM != 0) {
                newExRep.setOneRepMax(entered1RM);
            }
            // set the data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Data.workoutData.get(email).day4Exercises.replace(exType, newExRep);
            }
        }
        // Else statement means data is set to Sunday
        else {
            newExRep = userData.day5Exercises.get(exType);
            if( entered10RM != 0) {
                newExRep.setTenRepMax(entered10RM);
            }
            if (entered1RM != 0) {
                newExRep.setOneRepMax(entered1RM);
            }
            // set the data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Data.workoutData.get(email).day5Exercises.replace(exType, newExRep);
            }
        }

        // find the exercises and change swap it
        if(exType.equals("Ab") || exType.equals("Ab1")) {
            for ( int i = 0; i < userData.getAbs().length; i++ ) {
                if (userData.getAbs()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getAbs()[i] = newExRep;
                    break;
                }
            }
        } else if (exType.equals("Bicep") || exType.equals("Bicep1")) {
            for ( int i = 0; i < userData.getBiceps().length; i++ ) {
                if (userData.getBiceps()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getBiceps()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Bench")) {
            for ( int i = 0; i < userData.getBench().length; i++ ) {
                if (userData.getBench()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getBench()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Chest") || exType.equals("Chest1") || exType.equals("Chest2")) {
            for ( int i = 0; i < userData.getChest().length; i++ ) {
                if (userData.getChest()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getChest()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Deadlift")) {
            for ( int i = 0; i < userData.getDeadlift().length; i++ ) {
                if (userData.getDeadlift()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getDeadlift()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Hamstring")) {
            for ( int i = 0; i < userData.getHamstrings().length; i++ ) {
                if (userData.getHamstrings()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getHamstrings()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Lat") || exType.equals("Lat1") || exType.equals("Lat2")) {
            for ( int i = 0; i < userData.getLats().length; i++ ) {
                if (userData.getLats()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getLats()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Quad")) {
            for ( int i = 0; i < userData.getQuads().length; i++ ) {
                if (userData.getQuads()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getQuads()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Shoulder")) {
            for ( int i = 0; i < userData.getShoulders().length; i++ ) {
                if (userData.getShoulders()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getShoulders()[i] = newExRep;
                    break;
                }
            }
        }
        else if (exType.equals("Squat")) {
            for ( int i = 0; i < userData.getSquat().length; i++ ) {
                if (userData.getSquat()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getSquat()[i] = newExRep;
                    break;
                }
            }
        }
        else {
            for ( int i = 0; i < userData.getTriceps().length; i++ ) {
                if (userData.getTriceps()[i].getExName().equals(exNameData)){
                    Data.workoutData.get(email).getTriceps()[i] = newExRep;
                    break;
                }
            }
        }
    }
}