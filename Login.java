package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Login extends AppCompatActivity {
    private Context context;
    String username, password;

    EditText user_input, pass_input;

    Button submit, LEFT, signUp;

    Database Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database = new Database(Login.this);

        user_input = (EditText) findViewById(R.id.user_input);
        pass_input = (EditText) findViewById(R.id.pass_input);


        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = user_input.getText().toString();
                password = pass_input.getText().toString();

                if(TextUtils.isEmpty(username))
                {
                    user_input.requestFocus();
                    user_input.setError(" Please Enter a Valid Username. ");
                }
                if(TextUtils.isEmpty(password))
                {
                    pass_input.requestFocus();
                    pass_input.setError(" Please Enter a Valid Password. ");
                }
                else {


                   //Database.checkLogin(username, password);
                    Boolean result = Database.checkLogin(username, password);
                    if (result == true)
                    {
                        Toast.makeText(context, "Valid credentials", Toast.LENGTH_SHORT).show();
                        /*submit = findViewById(R.id.submit);
                        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent downloadIntent = new Intent(getApplicationContext(), Review_Rating.class);
                                startService(downloadIntent);
                                startActivity(new Intent(Login.this, Review_Rating.class));
                            }
                        }); */

                    }
                    else
                    {
                        Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }




                }


            }

        });
        LEFT = findViewById(R.id.LEFT);
        LEFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent downloadIntent = new Intent(getApplicationContext(), customerView.class);
                startService(downloadIntent);
                startActivity(new Intent(Login.this, customerView.class));
            }
        });
        signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent downloadIntent = new Intent(getApplicationContext(), Registration.class);
                startService(downloadIntent);
                startActivity(new Intent(Login.this, Registration.class));
            }
        });
    }
}

