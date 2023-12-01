package com.example.powerliftcoach;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.Objects;

public class DayTrackerFragment extends Fragment {
    ListView workoutsList;
    Button completeWeekBtn;

    // string of days
    private String[] daysArray;
    private WorkoutData userData = new WorkoutData("John Doe");
    private boolean[] completed;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_tracker_fragment, container, false);
        // initialize items
        workoutsList = view.findViewById(R.id.workouts_week_list_view);
        completeWeekBtn = view.findViewById(R.id.complete_week_btn);
        daysArray = getResources().getStringArray(R.array.days);

        // get intent email
        String email = "u";
        if (getActivity() != null) {
            Intent profileIntent = getActivity().getIntent();
            email = profileIntent.getStringExtra("account");
            userData = Data.workoutData.get(email);
        }
        // initialize completed array
        completed = userData.getCompleted();

        // array items
        CustomDayTrackAdapter daysOfWeek = new CustomDayTrackAdapter(requireContext(), daysArray, completed);
        workoutsList.setAdapter(daysOfWeek);

        // selected item listener
        String finalEmail = email;
        workoutsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workoutHandler(daysArray[position], position, finalEmail);
            }
        });

        // Long click to skip a workout
        String finalEmail2 = email;
        workoutsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if(!userData.getCompleted()[position])
                    skipWorkout(position, finalEmail2);
                return true;
            }
        });

        // Complete Week Button
        String finalEmail1 = email;
        completeWeekBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Arrays.equals(userData.getCompleted(), new boolean[]{true, true, true, true, true})) {
                    int currentWeek = userData.getCurrentWeek();
                    // reset week values
                    Data.workoutData.get(finalEmail1).setCompleted(new boolean[]{false, false, false, false, false});

                    // increase the week count if week != 16
                    if (currentWeek < 16) {
                        Toast.makeText(requireContext(), "Congratulations on completing week " + currentWeek, Toast.LENGTH_SHORT).show();
                        currentWeek++;
                        Data.workoutData.get(finalEmail1).setCurrentWeek(currentWeek);
                        // check if the user has moved onto the Strength block
                        if (currentWeek > 8 && currentWeek <= 12) {
                            Data.workoutData.get(finalEmail1).currentBlockNum = 1;
                            Data.workoutData.get(finalEmail1).setCurrentBlock("Strength");
                        }
                        // check if user has moved on to the Power block
                        else if (currentWeek > 12) {
                            Data.workoutData.get(finalEmail1).currentBlockNum = 2;
                            Data.workoutData.get(finalEmail1).setCurrentBlock("Power");
                        }

                    }
                    else {
                        // user has completed the 16 week program
                        Toast.makeText(requireContext(), "Congratulations on finishing the program!", Toast.LENGTH_SHORT).show();
                        Data.workoutData.get(finalEmail1).setCurrentWeek(1);
                        Data.workoutData.get(finalEmail1).setCompletedWorkouts(0);
                        Data.workoutData.get(finalEmail1).currentBlockNum = 0;
                        Data.workoutData.get(finalEmail1).setCurrentBlock("Hypertrophy");
                    }
                    // reset the screen
                    Intent i = new Intent(requireContext(), ProfileAndDailyTracker.class);
                    i.putExtra("account", finalEmail1);
                    startActivity(i);

                }
                // make suer user has completed all workouts
                else {
                    Toast.makeText(requireContext(), "Complete all workouts before continuing", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    // method to start a workout or inform user the workout has been completed
    private void workoutHandler(String daySelected, int index, String email) {
        if (!completed[index]) {
            Intent i = new Intent(requireContext(), Readiness.class);
            i.putExtra("dayKey",daySelected);
            i.putExtra("account", email);
            startActivity(i);
        }
        else {
            Toast.makeText(requireContext(), "Workout Has Already Been Completed", Toast.LENGTH_SHORT).show();
        }
    }

    // method to handle user skipping a workout
    private void skipWorkout( int position, String email ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        // Set the icon
        builder.setIcon(R.drawable.logopl);
        // Set Title
        builder.setTitle("Skip Workout");
        builder.setMessage("Are you sure, you want to skip this workout?");
        builder.setCancelable(false);

        // user confirms skip workout
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // skip workout
                boolean[] skipArr = userData.getCompleted();
                skipArr[position] = true;
                Data.workoutData.get(email).setCompleted(skipArr);
                // intent
                Intent i = new Intent(requireContext(), ProfileAndDailyTracker.class);
                i.putExtra("account", email);
                // inform user
                Toast.makeText(requireContext(), "Workout Skipped", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        // chancel workout
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(requireContext(), "Skip Canceled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
}
