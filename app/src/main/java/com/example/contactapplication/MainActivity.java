package com.example.contactapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView txt;
    EditText username,password;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt=findViewById(R.id.textView2);
        username=findViewById(R.id.editTextTextPersonName);
        password=findViewById(R.id.editTextTextPersonName2);
        button=findViewById(R.id.button);
        db=new DBHelper(this);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user.equals("")||pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter the Credential", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result=  db.checkusenamepassword(user,pass);
                    if(result){
                        Toast.makeText(MainActivity.this,"Logged in successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,Contact.class);
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                    }

                }
            }


        });
    }
}