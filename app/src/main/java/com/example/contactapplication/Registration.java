package com.example.contactapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
     Button button;
     DBHelper myDB;
     EditText username,password,repassword,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = findViewById(R.id.editTextTextPersonName3);
        password = findViewById(R.id.editTextTextPersonName4);
        repassword = findViewById(R.id.editTextTextPersonName5);
        phone = findViewById(R.id.editTextTextPersonName6);
        button = findViewById(R.id.button2);
        myDB = new DBHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String phone1 = phone.getText().toString();
                if (user.equals("") || pass.equals("") || repass.equals("")||phone1.equals("")) {
                    Toast.makeText(Registration.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean userCheckResult = myDB.checkusername(user);
                        if (userCheckResult == false) {
                            Boolean regResult = myDB.insertData(user, pass,phone1);
                            if (regResult == true) {

                                Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                                Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Registration.this, MainActivity.class);
                                startActivity(intent);


                            } else {
                                Toast.makeText(Registration.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Registration.this, "User already Exists.\n Please Sign in", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Registration.this, "Password  not matching", Toast.LENGTH_SHORT).show();
                    }


                }

            }


        });
    }



}