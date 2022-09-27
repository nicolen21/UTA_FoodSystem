package com.example.uta_food_app;

import androidx.appcompat.app.AppCompatActivity;

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
    String username, password;

    EditText user_input, pass_input;

    Button submit, LEFT, signUp;

    Database Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Database db = new Database(Login.this);

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
//                if(!(db.checkLogin(username, password)))
//                {
//                    Toast.makeText(getApplicationContext(), "Username and password do not match", Toast.LENGTH_LONG).show();
//                }
                else {
                    //proceed with operation
                    Boolean checkLogin = db.checkLogin(username,password);
                    if (checkLogin == true){
                        Toast.makeText(getApplicationContext(), "Successful Login!", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Wrong email or password", Toast.LENGTH_LONG).show();
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

/*  if (username == something in database && password == something in database)
                {
                    successful login, move them to the next screen
                }
                 else if (login == something in db && password != something in db)
                 {
                    print ("wrong password")
                    count input....after 3 tries, print "would you like to reset your password? do the whole forgot password thing
                  }
                  else (login !=something in db && password == something in db)
                  {
                  wrong username, try again. // IF we have time, we can do something similar to the forgot password thing
                  };

                */