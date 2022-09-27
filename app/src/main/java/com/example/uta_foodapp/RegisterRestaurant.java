package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterRestaurant extends AppCompatActivity {

    private Button submit;
    private EditText resname_input, location_input;
    private Spinner openHr_input, closeHr_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_restaurant);

        Spinner mySpinner = (Spinner) findViewById(R.id.opHours);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegisterRestaurant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hours));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        Spinner mySpinner2 = (Spinner) findViewById(R.id.clHours);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(RegisterRestaurant.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hours));
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner2.setAdapter(myAdapter2);


        resname_input = (EditText) findViewById(R.id.restaurantNameEdit);
        location_input = (EditText) findViewById(R.id.locationEdit);
        openHr_input = (Spinner) findViewById(R.id.opHours);
        closeHr_input = (Spinner) findViewById(R.id.clHours);



        submit=(Button) findViewById(R.id.submitInfo);
        submit.setOnClickListener(new View.OnClickListener()
        {
            String restaurant_name, location, openHour, closeHour;

            @Override
            public void onClick(View v)
            {
                Database db = new Database(RegisterRestaurant.this);

                restaurant_name = resname_input.getText().toString();
                location = location_input.getText().toString();
                openHour = openHr_input.getSelectedItem().toString();
                closeHour = closeHr_input.getSelectedItem().toString();

                db.addNewRestaurant(restaurant_name, location, openHour, closeHour);
            }
        });
    }
}