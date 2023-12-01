package com.example.powerliftcoach;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Readiness extends AppCompatActivity {

    EditText weight;
    Button nextBtn;
    RadioGroup tiredness, soreness, motivation, diet;
    RadioButton tiredness5, soreness5, motivation5, diet5;

    // data to hold the checks and scores
    // string to hold the radio values get initialized to 5 since that is the radioGroup starting point
    String tiredString = "5", soreString = "5", motiveString = "5", dietString = "5";
    String weightString;
    double currentWeight, readinessScore;
    private WorkoutData userData = new WorkoutData("John Doe");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readiness);
        // initialize values
        weight = findViewById(R.id.current_weight_value_text);
        nextBtn = findViewById(R.id.next_btn);
        tiredness = findViewById(R.id.tiredness_rating);
        soreness = findViewById(R.id.soreness_rating);
        motivation = findViewById(R.id.soreness_rating);
        diet = findViewById(R.id.diet_rating);
        
        // initialize checked values
        tiredness5 = findViewById(R.id.tiredness_rating_5);
        tiredness5.setChecked(true);
        soreness5 = findViewById(R.id.soreness_rating_5);
        soreness5.setChecked(true);
        motivation5 = findViewById(R.id.motivation_rating_5);
        motivation5.setChecked(true);
        diet5 = findViewById(R.id.diet_rating_5);
        diet5.setChecked(true);

        // get Intent
        String email;
        String daySelected;

        Intent readinessIntent = getIntent();
        email = readinessIntent.getStringExtra("account");
        daySelected = readinessIntent.getStringExtra("dayKey");
        userData = Data.workoutData.get(email);


        // radio group event listeners
        // listener for tiredness
        tiredness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton tiredBtn = findViewById(checkedId);
                if (tiredBtn != null) {
                    tiredString = tiredBtn.getText().toString();
                }
            }
        });

        // listener for soreness
        soreness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton soreBtn = findViewById(checkedId);
                if (soreBtn != null) {
                    soreString = soreBtn.getText().toString();
                }
            }
        });

        // listener for motivation
        tiredness.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton motiveBtn = findViewById(checkedId);
                if (motiveBtn != null) {
                    motiveString = motiveBtn.getText().toString();
                }
            }
        });

        // listener for diet
        diet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton dietBtn = findViewById(checkedId);
                if (dietBtn != null) {
                    dietString = dietBtn.getText().toString();
                }
            }
        });


        // Next button for once all forms are completed
        String finalEmail = email;
        String finalDaySelected = daySelected;
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weightString = weight.getText().toString();
                double currentRedAve;
                double curWeAve;
                if( weightString.equals("")){
                    Toast.makeText(Readiness.this, "You Must Enter Current Weight to Continue", Toast.LENGTH_LONG).show();
                }
                else {
                    // try parsing users entered weight
                    try {
                        currentWeight = Double.parseDouble(weightString);
                        readinessScore = Double.parseDouble(tiredString) + Double.parseDouble(soreString)
                                + Double.parseDouble(soreString) + Double.parseDouble(dietString);

                        // if statement to check if a user  has previously entered a readiness score to calculate average readiness
                        if (userData.getAverageReadinessScore() == 0.0) {
                            //  readiness to = average readiness
                            currentRedAve = readinessScore;
                        }
                        else {
                            // calculate average readiness
                            currentRedAve = userData.getAverageReadinessScore() + readinessScore;
                            currentRedAve /= 2;
                        }
                        // if statement to check if a user  has previously entered there body weight to calculate average body weight
                        if (userData.getAverageBodyWeight() == 0.0 ){
                            // add current weight to average
                            curWeAve = currentWeight;
                        }
                        else {
                            // calculate average weight
                            curWeAve = userData.getAverageBodyWeight() + currentWeight;
                            curWeAve /= 2;
                        }

                        // Create Intent to continue to the workout
                        Intent i = new Intent(Readiness.this, Workout.class);
                        i.putExtra("curAveWeight", curWeAve);
                        i.putExtra("readyScore", readinessScore);
                        i.putExtra("curAveReadiness", currentRedAve);
                        i.putExtra("account", finalEmail);
                        i.putExtra("dayKey", finalDaySelected);
                        startActivity(i);
                    }
                    catch (NumberFormatException e) {
                        Toast.makeText(Readiness.this, "Incorrect data entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}