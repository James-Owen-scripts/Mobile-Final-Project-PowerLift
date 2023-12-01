package com.example.powerliftcoach;

import static android.content.Intent.getIntent;

import static androidx.core.content.ContextCompat.startForegroundService;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {
    TextView header, weight, readiness, curBlock, curWeek, compWork;
    private WorkoutData userData = new WorkoutData("John Doe");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        // initialize textviews
        header = view.findViewById(R.id.hello_text);
        weight = view.findViewById(R.id.average_weight_value_text);
        readiness = view.findViewById(R.id.average_readiness_value_text);
        curBlock = view.findViewById(R.id.current_block_value_text);
        curWeek = view.findViewById(R.id.current_week_value_text);
        compWork = view.findViewById(R.id.completed_workouts_value_text);

        // get intent
        String email;
        if (getActivity() != null) {
            Intent profileIntent = getActivity().getIntent();
            email = profileIntent.getStringExtra("account");
            userData = Data.workoutData.get(email);
        }
        // variable weekOfBlock holds the current week in the block
        int weekOfBlock = userData.getCurrentWeek() % 4;
        // change weekOfBlock to equal 4 to show week 4/4
        if (weekOfBlock == 0) {
            weekOfBlock = 4;
        }
        // Set all data
        header.setText("Hello " + userData.getName());
        weight.setText(Double.toString(Math.round(userData.getAverageBodyWeight())));
        readiness.setText(Double.toString(Math.round(userData.getAverageReadinessScore())) + "/20");
        curBlock.setText("Week " + weekOfBlock + "/4 of " + userData.getCurrentBlock());
        curWeek.setText(Integer.toString(userData.getCurrentWeek()) + getText(R.string.outOf16));
        compWork.setText(Integer.toString(userData.getCompletedWorkouts()));

        return view;
    }
}
