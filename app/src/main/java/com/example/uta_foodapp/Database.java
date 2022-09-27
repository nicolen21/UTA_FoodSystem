package com.example.uta_foodapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private Context context;

    //Database Version
    private static final int DB_VERSION = 1;

    //Database Name
    private static final String DB_NAME = "FoodApp";


    //Table Names
    //REGISTRATION Table name
    private static final String TABLE_REGISTRATION = "Registration_Table";
    //FOOD Table name
    private static final String TABLE_FOOD = "Food_Table";
    //RESTAURANT Table name
    private static final String TABLE_RESTAURANT = "Restaurant_Table";

    //REGISTRATION Column Names
    private static final String FIRST_COL = "firstName";
    private static final String LAST_COL = "lastName";
    private static final String EMAIL_COL = "email";
    private static final String NET_COL = "netID";
    private static final String USER_COL = "username";
    private static final String PASS_COL = "password";

    //FOOD table column names
    private static final String FOOD_COL = "foodName";
    private static final String RESTAURANT_COL = "restaurantName";
    private static final String PRICE_COL = "foodPrice";

    //REGISTRATION table column names
    private static final String RES_NAME_COL = "restaurantName";
    private static final String LOCATION_COL = "location";
    private static final String OPENHR_COL = "openingHour";
    private static final String CLOSEHR_COL = "closingHour";


    //TABLE CREATE STATEMENTS
    //REGISTRATION Create Statement
    private static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE " + TABLE_REGISTRATION + " ("
            + FIRST_COL + " TEXT, "
            + LAST_COL + " TEXT, "
            + EMAIL_COL + " TEXT, "
            + NET_COL + " TEXT, "
            + USER_COL + " TEXT, "
            + PASS_COL + " TEXT);"
            ;
    //FOOD Create Statement
    private static final String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + " ("
            + FOOD_COL + " TEXT, "
            + RESTAURANT_COL + " TEXT, "
            + PRICE_COL + " TEXT);";

    //RESTAURANT Create Statement
    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT + " ("
            + RES_NAME_COL + " TEXT, "
            + LOCATION_COL + " TEXT, "
            + OPENHR_COL + " TEXT, "
            + CLOSEHR_COL + " TEXT);";


    //creating constructor for database handler
    public Database(@Nullable Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    //method for creating a database
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_REGISTRATION);
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_RESTAURANT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //on upgrade, drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);

        //create new tables
        onCreate(db);
    }

    //------------------ REGISTRATION TABLE METHODS -----------------------------------------------

    //add new registration info to our sqlite database
    public void addNewRegistration (String firstname, String lastname, String email, String netID, String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_COL, firstname);
        values.put(LAST_COL, lastname);
        values.put(EMAIL_COL, email);
        values.put(NET_COL, netID);
        values.put(USER_COL, username);
        values.put(PASS_COL, password);

        db.insert(TABLE_REGISTRATION, null, values);

        db.close();

    }

    //--------------- FOOD TABLE METHODS ----------------------------------------------------------
    public void addNewMenuItem(String foodName, String restaurantName, String foodPrice)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FOOD_COL, foodName);
        values.put(RESTAURANT_COL, restaurantName);
        values.put(PRICE_COL, foodPrice);

        long result = db.insert(TABLE_FOOD, null, values);

        //check if added correctly
        if(result==-1)
        {
            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //--------------- RESTAURANT TABLE METHODS ----------------------------------------------------
    public void addNewRestaurant(String restaurantName, String location, String openingHour, String closingHour)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RES_NAME_COL, restaurantName);
        values.put(LOCATION_COL, location);
        values.put(OPENHR_COL, openingHour);
        values.put(CLOSEHR_COL, closingHour);

        long result = db.insert(TABLE_RESTAURANT, null, values);

        //check if added correctly
        if(result==-1)
        {
            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    Cursor readAllDataRestaurant()
    {
        String query = "SELECT * FROM " + TABLE_RESTAURANT;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}

