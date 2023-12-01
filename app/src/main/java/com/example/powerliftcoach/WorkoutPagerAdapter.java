package com.example.powerliftcoach;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// for profile and day tracker tab views
public class WorkoutPagerAdapter extends FragmentStateAdapter {
    public WorkoutPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new ProfileFragment();
            }
            case 1: {
                return new DayTrackerFragment();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
