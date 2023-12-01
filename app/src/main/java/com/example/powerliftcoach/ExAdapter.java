package com.example.powerliftcoach;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExAdapter extends RecyclerView.Adapter<ExAdapter.ViewHolder> {

    Context context;
    ArrayList<Exercise_Model> exercises;

    public ExAdapter(Context context, ArrayList<Exercise_Model> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exercise_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Exercises curExercise = exercises.get(position).exercise;
        // initialize data
        holder.set1.setText(exercises.get(position).weight[0] + " lbs");
        holder.set2.setText(exercises.get(position).weight[1] + " lbs");
        holder.set3.setText(exercises.get(position).weight[2] + " lbs");
        holder.set4.setText(exercises.get(position).weight[3] + " lbs");

        // data to pass explicitly
        double curWeight = exercises.get(position).curWeight;
        double readyScore = exercises.get(position).readyScore;
        double readyAve = exercises.get(position).readyAve;
        String email = exercises.get(position).email;
        String daySelected = exercises.get(position).daySelected;
        String exType = exercises.get(position).exType;
        // initialize exercise name and rep range
        holder.exercise.setText(exercises.get(position).exercise.getExName());
        holder.repCount.setText(Integer.toString(exercises.get(position).exercise.getRepRange()[Data.workoutData.get(email).currentBlockNum]));

        // itemView click listener to swap exercises
        holder.swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ExerciseSwap.class);
                i.putExtra("curAveWeight", curWeight);
                i.putExtra("readyScore", readyScore);
                i.putExtra("curAveReadiness", readyAve);
                i.putExtra("account", email);
                i.putExtra("dayKey", daySelected);
                i.putExtra("exTypeKey", exType);
                context.startActivity(i);
            }
        });

        // set weight button will take the user to the set weight screen to
        // change there 1 or 10 rep max
        holder.setWeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FitnessLevel.class);
                i.putExtra("curAveWeight", curWeight);
                i.putExtra("readyScore", readyScore);
                i.putExtra("curAveReadiness", readyAve);
                i.putExtra("account", email);
                i.putExtra("dayKey", daySelected);
                i.putExtra("exTypeKey", exType);
                i.putExtra("exNameKey", exercises.get(position).exercise.getExName());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView exercise, repCount, set1, set2, set3, set4;
        Button setWeightBtn;
        ImageView swap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize values
            exercise = itemView.findViewById(R.id.exercise_title);
            repCount = itemView.findViewById(R.id.rep_count_text);
            set1 = itemView.findViewById(R.id.firstSet);
            set2 = itemView.findViewById(R.id.secondSet);
            set3 = itemView.findViewById(R.id.thirdSet);
            set4 = itemView.findViewById(R.id.fourthSet);
            setWeightBtn = itemView.findViewById(R.id.set_weight_btn);
            swap = itemView.findViewById(R.id.swap);
        }
    }
}
