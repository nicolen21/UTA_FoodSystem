package com.example.foodsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Checkout extends AppCompatActivity {

    Button btnPayNow;
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText promoCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btnPayNow = findViewById(R.id.PayNow);
        radioGroup = findViewById(R.id.radio_btns);
        promoCode = (EditText) findViewById(R.id.UserPromoCode);

        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent downloadIntent = new Intent(getApplicationContext(), Payment.class);
                startService(downloadIntent);
                startActivity(new Intent(Checkout.this, Payment.class));

            }
        });

    }


}