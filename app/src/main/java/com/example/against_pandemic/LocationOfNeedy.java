package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocationOfNeedy extends AppCompatActivity {
    Intent intent =getIntent();
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_of_needy);
        next = (Button) findViewById(R.id.set_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LocationOfNeedy.this,HelpForm.class);
                startActivity(intent);
            }
        });

    }
}