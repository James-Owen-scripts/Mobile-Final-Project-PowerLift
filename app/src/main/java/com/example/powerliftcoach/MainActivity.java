package com.example.powerliftcoach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import androidx.core.app.NotificationCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button login, createAccount;
    EditText enteredEmail, enteredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: delete the following test code
        Data.emails.add("u");
        Data.passwords.add("p");
        Data.workoutData.put("u", new WorkoutData("James"));

        // initialize the id's
        login = findViewById(R.id.login_btn);
        createAccount = findViewById(R.id.create_account_btn);
        enteredEmail = findViewById(R.id.Email_edit_text);
        enteredPassword = findViewById(R.id.password_edit_text);

        // Check for correct user login then send to the user profile screen
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProfileAndDailyTracker.class);
                String email = enteredEmail.getText().toString();
                String password = enteredPassword.getText().toString();

                // check if the user has entered a registered email and password
                if (Data.emails.contains(email) && Data.passwords.contains(password)){
                    i.putExtra("account", email);
                    startActivity(i);
                }
                else if (Data.passwords.contains(password)){
                    Toast.makeText(MainActivity.this, "Incorrect Email entry", Toast.LENGTH_SHORT).show();
                }
                else if (Data.emails.contains(email)) {
                    Toast.makeText(MainActivity.this, "Incorrect Password Entry", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Incorrect Password and Email Entry", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Send user to the create account screen
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(i);
            }
        });
    }
}