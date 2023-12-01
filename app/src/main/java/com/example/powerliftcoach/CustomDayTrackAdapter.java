package com.example.powerliftcoach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class CustomDayTrackAdapter extends ArrayAdapter<String> {
    private Context context;
    private boolean[] completed;
    public CustomDayTrackAdapter(Context context, String[] daysArr, boolean[] completed) {
        super(context, R.layout.days, daysArr);
        this.context = context;
        this.completed = completed;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.days, parent, false);

        // initialize data and set data
        TextView textView = view.findViewById(R.id.days_text_view);
        textView.setText(getItem(position));

        // Background color to change if workout is completed
        if (completed[position]){
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.midBlue));
        }
        return view;
    }
}
