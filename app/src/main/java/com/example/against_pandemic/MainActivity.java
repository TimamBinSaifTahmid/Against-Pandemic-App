package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });

    }

    public void pop(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        final View codePopupView = getLayoutInflater().inflate(R.layout.varification_code_popup,null);
                builder1.setView(codePopupView);
                builder1.create();
                builder1.show();
         Button submit;
        submit = (Button)findViewById(R.id.ok_code);
         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(MainActivity.this,
                         "varified", Toast.LENGTH_LONG).show();
             }
         });

    }
}