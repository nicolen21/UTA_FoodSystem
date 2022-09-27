package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    String first_name, last_name, email, netID, username, password; //need to add a place for username. unless we talk to him about using netID for username.

    EditText firstname_input, lastname_input, emailInput, netInput, userInput, passInput;

    Button submit;

    Database Database;
    // TOAST: GO OVER USAGE + IMPLEMENTATION

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Database = new Database(Registration.this);

//        commented out because EditText is not initialized in the .xml file
//        firstname_input = (EditText) findViewById(R.id.firstname_input);
//        lastname_input = (EditText) findViewById(R.id.lastname_input);
//        emailInput = (EditText) findViewById(R.id.emailInput);
//        netInput = (EditText) findViewById(R.id.netInput);
//        userInput = (EditText) findViewById(R.id.userInput);
//        passInput = (EditText) findViewById(R.id.passInput);
//
//        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                first_name = firstname_input.getText().toString();
                last_name = lastname_input.getText().toString();
                email = emailInput.getText().toString();
                netID = netInput.getText().toString();
                username = userInput.getText().toString();
                password = passInput.getText().toString();

                Database.addNewRegistration(first_name, last_name, email, netID, username, password);

                Toast.makeText(Registration.this, "New User has been added.", Toast.LENGTH_SHORT).show();

            }
        });

    }
}