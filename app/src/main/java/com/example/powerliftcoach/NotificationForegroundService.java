package com.example.powerliftcoach;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NotificationForegroundService extends Service {
    public static final String CHANNEL_ID = "PowerLiftServiceChannel";
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // array for the days of the week the user shall complete a workout
        String workoutDays[] = {"Monday", "Wednesday", "Thursday", "Friday", "Sunday"};
        // Simple date format to contain the current day that will be checked incase the user should
        // workout that day
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        // switch statement to send notification depending on day and if user workouts out
        switch (day) {
            case Calendar.MONDAY:
                startNotification(workoutDays[0]);
                break;

            case Calendar.WEDNESDAY:
                startNotification(workoutDays[1]);
                break;

            case Calendar.THURSDAY:
                startNotification(workoutDays[2]);
                break;

            case Calendar.FRIDAY:
                startNotification(workoutDays[3]);
                break;

            case Calendar.SUNDAY:
                startNotification(workoutDays[4]);
                break;
        }



        return START_NOT_STICKY;
    }

    // notification data to execute
    private void startNotification(String dayOfWeek) {
        createNotificationChannel();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, notificationIntent, PendingIntent.FLAG_MUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Workout Day!")
                .setContentText("Remember " + dayOfWeek +
                        " is a workout day make sure to complete a workout!")
                .setSmallIcon(R.drawable.logopl)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);
    }

    private void createNotificationChannel() {

        // Check for oreo version
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManager manager = getSystemService(NotificationManager.class);

            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "PowerLift Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );

            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
