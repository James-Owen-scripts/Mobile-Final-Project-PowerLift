package com.example.powerliftcoach;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    EditText name, email, password, rePassword;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // initialize the id's
        name = findViewById(R.id.name_edit_text);
        email = findViewById(R.id.create_email_edit_text);
        password = findViewById(R.id.create_password_edit_text);
        rePassword = findViewById(R.id.re_create_password_edit_text);
        submit = findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String createdName = name.getText().toString();
                String createdEmail = email.getText().toString();
                String createdPassword = password.getText().toString();
                String createdRePassword = rePassword.getText().toString();

                // check for empty data
                if(createdName.equals("")) {
                    Toast.makeText(CreateAccount.this, "Cannot Leave Name Empty", Toast.LENGTH_SHORT).show();
                }
                else if(createdEmail.equals("")) {
                    Toast.makeText(CreateAccount.this, "Cannot Leave Email Empty", Toast.LENGTH_SHORT).show();
                }
                else if(createdPassword.equals("")) {
                    Toast.makeText(CreateAccount.this, "Cannot Leave Password Empty", Toast.LENGTH_SHORT).show();
                }
                else if(createdRePassword.equals("")) {
                    Toast.makeText(CreateAccount.this, "You Must Re-Enter the Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    // check for a valid email address
                    if(!correctEmail(createdEmail)) {
                        Toast.makeText(CreateAccount.this, "You Must Enter a Valid Email Address", Toast.LENGTH_SHORT).show();
                    }
                    // create user
                    else if(correctPassword(createdPassword, createdRePassword)) {
                        // add all new information to correct lists and maps
                        Toast.makeText(CreateAccount.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(CreateAccount.this, MainActivity.class);
                        // add user data to static types
                        Data.emails.add(createdEmail);
                        Data.passwords.add(createdPassword);
                        Data.workoutData.put(createdEmail, new WorkoutData(createdName));
                        startActivity(i);
                    }
                }
            }
        });
    }

    // method to iterate through user entered email and check if it is correct
    private boolean correctEmail(String createdEmail) {
        boolean correct = false;
        for(int i = 0; i < createdEmail.length()-1; i++) {
            if(createdEmail.charAt(i) == '@' ){
                for(int j = i + 1; j < createdEmail.length(); j++){
                    if(createdEmail.charAt(j) == '.') {
                        correct = true;
                    }
                }
            }
        }
        return correct;
    }

    // method to check if user password is correct
    private boolean correctPassword(String createdPassword, String createdRePassword) {
        // Patterns for special key
        Pattern specialKey = Pattern.compile("[~!@#$%^&*_><,:;]");
        Matcher isKey = specialKey.matcher(createdPassword);

        // Patterns for capital letters
        Pattern allCapitals = Pattern.compile("[A-Z]");
        Matcher isCapital = allCapitals.matcher(createdPassword);

        // Patterns for all lowercase letter
        Pattern allLows = Pattern.compile("[a-z]");
        Matcher isLowercase = allLows.matcher(createdPassword);

        // Check if password is correct length
        if (createdPassword.length() < 8) {
            Toast.makeText(CreateAccount.this, "Password Must Contain at Least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check if password contains a special key
        else if (!isKey.find()) {
            Toast.makeText(CreateAccount.this, "Password Must Contain at Least\n1 Special Character: ~!@#$%^&*_><,:;", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check if password contains a capital letter
        else if (!isCapital.find()) {
            Toast.makeText(CreateAccount.this, "Password Must Contain at Least\n1 Capital Letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Check if password contains a lowercase letter
        else if (!isLowercase.find()) {
            Toast.makeText(CreateAccount.this, "Password Must Contain at Least\n1 Lowercase Letter", Toast.LENGTH_SHORT).show();
            return false;
        }
        // check if passwords match
        else if(!createdPassword.equals(createdRePassword)) {
            Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        // for loop to check if user has an number in there password
        for (int i = 0; i < 10; i++) {
            String num = Integer.toString(i);
            if(createdPassword.contains(num)){
                return true;
            }
        }
        Toast.makeText(CreateAccount.this, "Password must have at least one numeric value", Toast.LENGTH_SHORT).show();
        return false;
    }
}