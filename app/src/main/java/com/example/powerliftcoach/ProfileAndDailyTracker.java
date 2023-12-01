package com.example.powerliftcoach;

import static androidx.core.content.ContextCompat.startForegroundService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class ProfileAndDailyTracker extends AppCompatActivity {

    TabLayout workoutTabs;
    ViewPager2 workoutViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_daily_tracker);

        Intent notificationIntent = new Intent(ProfileAndDailyTracker.this, NotificationForegroundService.class);

        // check if day is a workout day
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        // check if version is >= oreo and day is a workout day
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && (day == Calendar.MONDAY ||
                day == Calendar.WEDNESDAY || day == Calendar.THURSDAY || day == Calendar.FRIDAY ||
                day == Calendar.SUNDAY)) {
            startForegroundService(notificationIntent);
        }

        // Initialize values
        workoutTabs = findViewById(R.id.user_tabs);
        workoutViewPager = findViewById(R.id.user_page);

        //      Creating tabs
        // Profile tab
        TabLayout.Tab profileTab = workoutTabs.newTab();
        profileTab.setText(getText(R.string.profileHeader).toString());
        workoutTabs.addTab(profileTab);
        // day tracker tab
        TabLayout.Tab dayTrackerTab = workoutTabs.newTab();
        dayTrackerTab.setText(getText(R.string.dayTrackerText).toString());
        workoutTabs.addTab(dayTrackerTab);

        // Adapter for workoutPager
        WorkoutPagerAdapter workoutAdapter = new WorkoutPagerAdapter(getSupportFragmentManager(), getLifecycle());
        workoutViewPager.setAdapter(workoutAdapter);

        // Listen to switch between workout tabs
        workoutTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                workoutViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Not Used
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Not Used
            }
        });

        // callback for workoutViewPager change of pages
        workoutViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                workoutTabs.selectTab(workoutTabs.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    // destroy method to destroy notification
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent notificationIntent = new Intent(ProfileAndDailyTracker.this, NotificationForegroundService.class);
        stopService(notificationIntent);
    }
}