package com.example.cse489_2023_3_2019_2_60_032_classsummary;

import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDB extends SQLiteOpenHelper {

    public static final String ClassSummaryTable = "ClassSummaryTable";

    // Define database-related constants and methods for database operations

    // Constructor
    public EventDB(Context context) {
        super(context, "ClassSummaryTable.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define the table structure and create the database
        String createTableQuery = "CREATE TABLE IF NOT EXISTS ClassSummaryTable (" +
                "name TEXT, " +
                "id TEXT PRIMARY KEY, " +
                "course TEXT, " +
                "type TEXT, " +
                "date TEXT, " +
                "lecture TEXT, " +
                "topic TEXT," +
                "summary TEXT " +
                ")";
        db.execSQL(createTableQuery);
    }
    // Add this method to select events and return a Cursor


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }

    // Insert a new record into the database
    public void insertEvent(String name, String id,String course,String type, String date, String lecture,String topic, String summary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("id", id);
        values.put("course", course);
        values.put("type", type);
        values.put("date", date);
        values.put("lecture", lecture);
        values.put("topic", topic);
        values.put("summary", summary);
        db.insert("ClassSummaryTable", null, values);
        db.close();
    }

    // Update an existing record in the database
    public void updateEvent(String name, String id,String course,String type, String date, String lecture,String topic, String summary) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("id", id);
        values.put("course", course);
        values.put("type", type);
        values.put("date", date);
        values.put("lecture", lecture);
        values.put("topic", topic);
        values.put("summary", summary);
        db.update("ClassSummaryTable", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteEvent(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete("events", "ID = ?", new String[] {ID});
        } finally {
            db.close();
        }
    }

    /*public Cursor selectEvents(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Do not close the database here if you are returning the cursor.
            // The calling function should handle closing both the cursor and the database.
        }
        return res;
    }*/




    // Add other database operations as needed
}