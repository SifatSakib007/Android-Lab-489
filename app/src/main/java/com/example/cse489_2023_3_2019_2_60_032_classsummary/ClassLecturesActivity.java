package com.example.cse489_2023_3_2019_2_60_032_classsummary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
//import android.database.Cursor;

public class ClassLecturesActivity extends AppCompatActivity {
    private String userId;

    @SuppressLint("SetTextI18n")


    @Override


    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_lectures);

        // Initialize the database helper
        EventDB dbHelper = new EventDB(this);

        // Query to retrieve class lecture information
        String query = "SELECT * FROM " + EventDB.ClassSummaryTable;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);

        // Process the query results
        /*while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String topic = cursor.getString(cursor.getColumnIndex("topic"));
            String summary = cursor.getString(cursor.getColumnIndex("summary"));

            // Logging the information
            Log.d("ClassLectureInfo", "ID: " + id + ", Topic: " + topic + ", Summary: " + summary);
        }*/
        cursor.close();
        dbHelper.close();

        // Setup user interface
        setupUI(savedInstanceState);

        // Setup buttons
        setupButtons();
    }

    private void setupUI(Bundle savedInstanceState) {
    }

    private void setupButtons() {
        Button btnBack = findViewById(R.id.btnBack);
        Button btnAddNew = findViewById(R.id.btnAddNew);

        btnBack.setOnClickListener(v -> BackPressed());
        btnAddNew.setOnClickListener(v -> classSummaryPage());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("userId", userId); // Save the userid
    }

    private void classSummaryPage() {
        Intent i = new Intent(this, ClassSummaryActivity.class);
        startActivity(i);
    }

    private void BackPressed() {
        finish();
    }
}