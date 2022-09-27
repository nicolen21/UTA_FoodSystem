package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendorView extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    Database db;
    ArrayList<String> restaurantName, location, openHr, closeHr;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.RVRestaurant);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VendorView.this, RegisterRestaurant.class);
                startActivity(intent);
            }
        });

        db = new Database(VendorView.this);
        restaurantName = new ArrayList<>();
        location = new ArrayList<>();
        openHr = new ArrayList<>();
        closeHr = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(VendorView.this, restaurantName, location, openHr, closeHr);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(VendorView.this));
    }

    void storeDataInArray()
    {
        Cursor cursor = db.readAllDataRestaurant();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(cursor.moveToNext())
            {
                restaurantName.add(cursor.getString(0));
                location.add(cursor.getString(1));
                openHr.add(cursor.getString(2));
                closeHr.add(cursor.getString(3));

            }
        }
    }
}