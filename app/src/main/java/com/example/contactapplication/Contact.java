package com.example.contactapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Contact extends AppCompatActivity {
  Button button;
  DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        button=findViewById(R.id.button3);
        DB=new DBHelper(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= DB.getdata();
                if (res.getCount()==0)
                    Toast.makeText(Contact.this, "No Entry Found", Toast.LENGTH_SHORT).show();

                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name:" + res.getString(1)+ "\n");
                    buffer.append("PHONE NUMBER:" + res.getString(3)+ "\n");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(Contact.this);
                builder.setCancelable(true);
                builder.setTitle("AVAILABLE Contacts");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}