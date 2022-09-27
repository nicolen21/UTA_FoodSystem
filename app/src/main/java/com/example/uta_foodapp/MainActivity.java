package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //checking if there are any saved states. if there are: load that
        setContentView(R.layout.activity_main); //links the java file and the xml file

        Button LOGIN, REGISTRATION;

        LOGIN= findViewById(R.id.LOG_IN);
        REGISTRATION = findViewById(R.id.REGISTRATION);

        REGISTRATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent downloadIntent = new Intent(getApplicationContext(), Registration.class);
                startService(downloadIntent);
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent downloadIntent = new Intent(getApplicationContext(), Login.class);
                startService(downloadIntent);
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }
}