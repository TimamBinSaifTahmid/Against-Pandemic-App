package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SignUp extends AppCompatActivity {
    Intent intent =getIntent();
    EditText nid;
    EditText contact_no;
    EditText email;
    EditText password;
    RadioGroup financial_status;
    RadioButton status;
    Button signup;
    String Status,NID,Contact_no,Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nid=findViewById(R.id.nid);
        contact_no=findViewById(R.id.contact_no);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        financial_status=findViewById(R.id.radioGroup);
        signup=findViewById(R.id.signup_button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid= financial_status.getCheckedRadioButtonId();
                status =findViewById(radioid);
                Status=status.getText().toString();
                NID=nid.getText().toString();
                Contact_no=contact_no.getText().toString();
                Email=email.getText().toString();
                Password=password.getText().toString();

                Intent intent=new Intent(SignUp.this,LogIn.class);
                startActivity(intent);
            }
        });

    }
}