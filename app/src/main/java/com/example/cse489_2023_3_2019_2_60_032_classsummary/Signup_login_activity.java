package com.example.cse489_2023_3_2019_2_60_032_classsummary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class Signup_login_activity extends AppCompatActivity {


    private TextView tvToggleLabel, btnToggle, title;
    private EditText etName, etEmail, etPhone, etUserId, etPassword, etRePassword;
    private TableRow rowName;
    private TableRow rowEmail;
    private TableRow rowPhone;
    private TableRow rowUserId;
    private TableRow rowRePassword;

    private boolean isLoginPage = false;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);

        sharedPreferences = getSharedPreferences("RegistrationData", Context.MODE_PRIVATE);

        Button btnExit = findViewById(R.id.btnExit);
        Button btnGo = findViewById(R.id.btnGo);

        tvToggleLabel = findViewById(R.id.tvToggleLabel);
        btnToggle = findViewById(R.id.btnToggle);
        title = findViewById(R.id.title);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etUserId = findViewById(R.id.etUserId);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        rowName = findViewById(R.id.rowName);
        rowEmail = findViewById(R.id.rowEmail);
        rowPhone = findViewById(R.id.rowPhone);
        rowRePassword = findViewById(R.id.rowRePassword);


        this.changeView();


        btnToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoginPage = !isLoginPage;
                changeView();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle normal press (navigate back) here
                onBackPressed();
            }
        });


        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();
                String userid = etUserId.getText().toString();
                String password = etPassword.getText().toString();
                String RePassword = etRePassword.getText().toString();


                if (isLoginPage) {

                    performLogin(userid, password);
                } else {

                    performRegistration(name, email, phone, userid, password, RePassword);
                }
            }
        });
    }


    private void performLogin(String userid, String password) {
        // Retrieve the stored userid and password
        String savedUserId = sharedPreferences.getString("userid", "");
        String savedPassword = sharedPreferences.getString("password", "");

        // Log for debugging purposes
        Log.d("Signup_login_activity", "Stored UserId: " + savedUserId);
        Log.d("Signup_login_activity", "Stored Password: " + savedPassword);

        // Check if the user has not registered yet
        if (savedUserId.isEmpty() || savedPassword.isEmpty()) {
            showErrorMsg("Please register first.");
            return;
        }

        // Check if the entered userid and password match the stored credentials
        if (userid.equals(savedUserId) && password.equals(savedPassword)) {
            mainpage();
        } else {
            showErrorMsg("Invalid userid or password.");
        }
    }
    private void performRegistration(String name, String email, String phone, String userid, String password, String rePassword) {
        if (name.length() < 4 || name.length() > 12 || !name.matches("[a-zA-Z]+")) {
            showErrorMsg("Name should have 4-12 letters");
        } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            showErrorMsg("Invalid Email");
        } else if (!phone.startsWith("+") || phone.length() < 7 || phone.length() > 17) {
            showErrorMsg("Invalid Phone");
        } else if (!password.equals(rePassword)) {
            showErrorMsg("Passwords do not match");
        } else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userid", userid);
            editor.putString("password", password);
            editor.apply();


            Log.d("Signup_login_activity", "userid: " + userid);
            Log.d("Signup_login_activity","password: "+password);
            mainpage();

        }
    }



    private void showErrorMsg(String errmsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(errmsg);
        builder.setTitle("Error");
        builder.setCancelable(true);
        builder.setPositiveButton("Back", null);
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void mainpage() {
        String userid = etUserId.getText().toString();
        Log.d("Signup_login_activity", "Sending UserId: " + userid);

        Intent i = new Intent(Signup_login_activity.this, ClassLecturesActivity.class);
        i.putExtra("userid", userid);
        startActivity(i);
        finish();
    }

    private void changeView() {
        if (isLoginPage) {
            rowName.setVisibility(View.GONE);
            rowEmail.setVisibility(View.GONE);
            rowPhone.setVisibility(View.GONE);
            rowRePassword.setVisibility(View.GONE);
            title.setText("Login");
            tvToggleLabel.setText("Don't have an account");
            btnToggle.setText("Signup");
        } else {
            rowName.setVisibility(View.VISIBLE);
            rowEmail.setVisibility(View.VISIBLE);
            rowPhone.setVisibility(View.VISIBLE);
            rowRePassword.setVisibility(View.VISIBLE);
            title.setText("Signup");
            tvToggleLabel.setText("Already have an account");
            btnToggle.setText("Login");
        }
    }
}