package com.example.cse489_2023_3_2019_2_60_032_classsummary;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.RadioGroup;
import com.example.cse489_2023_3_2019_2_60_032_classsummary.EventDB;



public class ClassSummaryActivity extends AppCompatActivity {

    private EditText etName, etID, etDate, etLecture, etTopic, etSummary;
    private RadioGroup rgCourse, rgType;
    private String id; // Assuming this is a field in your class
    private EventDB eventDB; // Assuming EventDB is your database handler class

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_summary);


        // Initialize all your views
        etName = findViewById(R.id.tvname); // Make sure you have EditText instead of TextView in your XML for name and ID
        etID = findViewById(R.id.tvID);
        etDate = findViewById(R.id.etdate);
        etLecture = findViewById(R.id.etLecture);
        etTopic = findViewById(R.id.ettopic);
        etSummary = findViewById(R.id.etSummary); // Add this EditText for summary in your XML
        rgCourse = findViewById(R.id.rgCourse); // Add id to your RadioGroup for Course in your XML
        rgType = findViewById(R.id.rgType); // Add id to your RadioGroup for Type in your XML

        Button btnSave = findViewById(R.id.btnSave);
        Button btnCancel = findViewById(R.id.btnCancel);

        eventDB = new EventDB(this); // Initialize your database handler, adjust as per your actual implementation


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    saveData();
                } else {
                    Toast.makeText(ClassSummaryActivity.this, "Please fill all fields and make selections", Toast.LENGTH_SHORT).show();
                }
            }
        });






        //this.changeView();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle normal press (navigate back) here
                BackPressed();
            }
        });

    }
    private void saveData() {
        // Retrieve data from EditTexts and RadioButtons
        String name = etName.getText().toString();
        String id = etID.getText().toString(); // Assuming 'place' corresponds to 'ID'

        RadioGroup rgCourse = findViewById(R.id.rgCourse);
        int selectedRadioButtonId = rgCourse.getCheckedRadioButtonId();

        String course = ""; // Initialize the variable

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            course = selectedRadioButton.getText().toString();
        }
        RadioGroup rgtype = findViewById(R.id.rgType);
        int selectedRadioButtonId2 = rgType.getCheckedRadioButtonId();

        String type = ""; // Initialize the variable

        if (selectedRadioButtonId2 != -1) {
            RadioButton selectedRadioButton2 = findViewById(selectedRadioButtonId2);
            type = selectedRadioButton2.getText().toString();
        }
        String date = etDate.getText().toString();
        String lecture = etLecture.getText().toString(); // Assuming 'lecture' corresponds to 'capacity'
        String topic = etTopic.getText().toString();
        String summary = etSummary.getText().toString(); // Assuming 'summary' corresponds to 'desc'

        // Additional variables like 'email', 'phone', and 'budget' should be retrieved similarly

        // Check if eventID is empty and insert or update accordingly
        if (id.isEmpty()) {
            id = topic + System.currentTimeMillis();
            eventDB.insertEvent(id, name,course,type, date, lecture,topic, summary);
        } else {
            eventDB.updateEvent(id, name,course,type, date, lecture,topic, summary);
        }

        // Maybe navigate back or show a success message
        Toast.makeText(ClassSummaryActivity.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
    }
    private boolean validateInputs() {
        // Check if any of the EditTexts are empty
        if (TextUtils.isEmpty(etName.getText().toString()) ||
                TextUtils.isEmpty(etID.getText().toString()) ||
                TextUtils.isEmpty(etDate.getText().toString()) ||
                TextUtils.isEmpty(etLecture.getText().toString()) ||
                TextUtils.isEmpty(etTopic.getText().toString()) ||
                TextUtils.isEmpty(etSummary.getText().toString())) {
            return false;
        }

        // Check if a RadioButton in each RadioGroup is selected
        if (rgCourse.getCheckedRadioButtonId() == -1 ||
                rgType.getCheckedRadioButtonId() == -1) {
            return false;
        }

        return true;
    }


        /*btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classSummaryPage();
            }
        });*/



    private void BackPressed() {
        // Just finish the current activity. This will return to the previous activity in the stack.
        finish();
    }
}