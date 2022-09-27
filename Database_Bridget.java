package com.example.uta_food_app;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.os.CpuUsageInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;

class Database extends SQLiteOpenHelper {
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
    //VENDOR REGISTRATION Table name
    private static final String TABLE_VENDOR = "Vendor_Table";

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
    private static final String DESC_COL = "foodDescription";

    //RESTAURANT table column names
    private static final String RES_NAME_COL = "restaurantName";
    private static final String LOCATION_COL = "location";
    private static final String OPENHR_COL = "openingHour";
    private static final String CLOSEHR_COL = "closingHour";

    //VENDOR Column Names
    private static final String RES_COL = "resName";
    private static final String MAIL_COL = "mail";
    private static final String VEN_COL = "vendorID";
    private static final String USR_COL = "usrname";
    private static final String PAS_COL = "pword";


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
            + PRICE_COL + " TEXT, "
            + DESC_COL + " TEXT);";

    //RESTAURANT Create Statement
    private static final String CREATE_TABLE_RESTAURANT = "CREATE TABLE " + TABLE_RESTAURANT + " ("
            + RES_NAME_COL + " TEXT, "
            + LOCATION_COL + " TEXT, "
            + OPENHR_COL + " TEXT, "
            + CLOSEHR_COL + " TEXT);";

    //VENDOR Create Statement
    private static final String CREATE_TABLE_VENDOR = "CREATE TABLE " + TABLE_VENDOR + " ("
            + RES_COL + " TEXT, "
            + MAIL_COL + " TEXT, "
            + VEN_COL + " TEXT, "
            + USR_COL + " TEXT, "
            + PAS_COL + " TEXT);";


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
        db.execSQL(CREATE_TABLE_VENDOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //on upgrade, drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VENDOR);

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
    public void addNewMenuItem(String foodName, String restaurantName, String foodPrice, String foodDescription)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FOOD_COL, foodName);
        values.put(RESTAURANT_COL, restaurantName);
        values.put(PRICE_COL, foodPrice);
        values.put(DESC_COL, foodDescription);

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

    void updateDataMenu(String OGfoodName, String foodName, String restaurantName, String foodPrice, String foodDescription)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FOOD_COL, foodName);
        values.put(RESTAURANT_COL, restaurantName);
        values.put(PRICE_COL, foodPrice);
        values.put(DESC_COL, foodDescription);

        long result = db.update(TABLE_FOOD, values, "foodName=?", new String[]{OGfoodName});

        //check if updated correctly
        if(result==-1)
        {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    void deleteMenuItem(String foodName)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_FOOD, "foodName=?", new String[]{foodName});

        //check if deleted correctly
        if(result==-1)
        {
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }


    Cursor readAllDataFood()
    {
        String query = "SELECT * FROM " + TABLE_FOOD;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllMenuForRes(String resName)
    {
        String query = "SELECT * FROM " + TABLE_FOOD + " WHERE " +
                RESTAURANT_COL + " = " + "'" + resName + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
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

    //for recycler view
    //read data from database
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

    Cursor getInfoForRes(String rNameToFind)
    {
        String query = "SELECT * FROM " + TABLE_RESTAURANT+ " WHERE " +
                RES_NAME_COL + " = " + "'" + rNameToFind + "'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateDataRestaurant(String OGrestaurantName, String restaurantName, String location, String openingHour, String closingHour)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RES_NAME_COL, restaurantName);
        values.put(LOCATION_COL, location);
        values.put(OPENHR_COL, openingHour);
        values.put(CLOSEHR_COL, closingHour);

        long result = db.update(TABLE_RESTAURANT, values, "restaurantName=?", new String[]{OGrestaurantName});

        //check if updated correctly
        if(result==-1)
        {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //search data from database
    Cursor searchDataRestaurant(String searchText)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RESTAURANT + " WHERE " +
                RES_NAME_COL + " LIKE '%" + searchText + "%' ";

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    //------------------ REGISTRATION TABLE METHODS -----------------------------------------------

    //add new registration info to our sqlite database
    public void addVendorRegistration(String restaurantName, String mail, String vendorID, String usrname, String pasword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RES_COL, restaurantName);
        values.put(MAIL_COL, mail);
        values.put(VEN_COL, vendorID);
        values.put(USR_COL, usrname);
        values.put(PAS_COL, pasword);

        long result = db.insert(TABLE_VENDOR, null, values);

        //check if added correctly
        if (result == -1) {
            Toast.makeText(context, "Failed to insert", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }

        // reading data from registration table
        db.close();
    }


}
