package com.example.uta_foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class Search extends AppCompatActivity {

    private Button foodButton, restaurantButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        foodButton = (Button) findViewById(R.id.foodbutton);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodSearchQuery();
            }
        });

        restaurantButton = (Button) findViewById(R.id.restaurantbutton);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRestaurantSearchQuery();
            }
        });
    }

    public void openFoodSearchQuery() {
        Intent intent = new Intent(this, SearchFood.class);
        startActivity(intent);
    }

    public void openRestaurantSearchQuery() {
        Intent intent = new Intent(this, SearchRestaurant.class);
        startActivity(intent);
    }

}