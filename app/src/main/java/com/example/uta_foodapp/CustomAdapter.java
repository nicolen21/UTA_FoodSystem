package com.example.uta_foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder>
{
    Context context;
    ArrayList restaurantName, location, openHr, closeHr;

    CustomAdapter(Context context, ArrayList restaurantName, ArrayList location, ArrayList openHr, ArrayList closeHr)
    {
        this.context=context;
        this.restaurantName=restaurantName;
        this.location=location;
        this.openHr=openHr;
        this.closeHr=closeHr;
    }



    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_row, parent, false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position)
    {
        holder.restaurantNameTV.setText(String.valueOf(restaurantName.get(position)));
        holder.locationTV.setText(String.valueOf(location.get(position)));
        holder.openHrTV.setText(String.valueOf(openHr.get(position)));
        holder.closeHrTV.setText(String.valueOf(closeHr.get(position)));
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateRestaurant.class);
//                intent.putExtra("restaurantName", String.valueOf(restaurantName.get(position)));
//                intent.putExtra("location", String.valueOf(location.get(position)));
//                intent.putExtra("openingHr", String.valueOf(openHr.get(position)));
//                intent.putExtra("closingHr", String.valueOf(closeHr.get(position)));
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount()
    {
        //theyre all the same size so return any
        return restaurantName.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView restaurantNameTV, locationTV, openHrTV, closeHrTV;
        //       LinearLayout mainLayout;

        public myViewHolder(@NonNull View itemView)
        {
            super(itemView);
            restaurantNameTV = itemView.findViewById(R.id.restaurantNameTV);
            locationTV = itemView.findViewById(R.id.locationTV);
            openHrTV = itemView.findViewById(R.id.openHrTV);
            closeHrTV = itemView.findViewById(R.id.closeHrTV);
            //          mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
