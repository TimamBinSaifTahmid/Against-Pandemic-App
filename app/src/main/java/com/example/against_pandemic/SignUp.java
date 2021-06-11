package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {
    SignUp signUpobj=new SignUp();
    Intent intent =getIntent();
    EditText nid;
    EditText name;
    EditText contact_no;
    EditText email;
    EditText password;
    RadioGroup financial_status;
    RadioButton status;
    Button signup;
    String Status,NID,userName,Contact_no,Email,Password;

    private ApiServices apiServices;
    Validation validation=new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nid=findViewById(R.id.nid);
        name=findViewById(R.id.name);
        contact_no=findViewById(R.id.contact_no);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        financial_status=findViewById(R.id.radioGroup);
        signup=findViewById(R.id.signup_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid= financial_status.getCheckedRadioButtonId();
                status =findViewById(radioid);
                Status=status.getText().toString();
                NID=nid.getText().toString();
                userName=name.getText().toString();
                Contact_no=contact_no.getText().toString();
                Email=email.getText().toString();
                Password=password.getText().toString();
                String validEmail=validation.validateEmail(Email);
                String validPassword=validation.validatePassword(Password);
                if(validEmail!="null"&&validPassword!="null") {
                    email.setError(validEmail);
                    password.setError(validPassword);
                }
                else {
                    createPost();
                }
            }
        });

    }



private void createPost() {
    HashMap<String, String> fields = new HashMap<>();
    fields.put("nid", NID);
    fields.put("name",userName);
    fields.put("location","Null");
    fields.put("contact_info", Contact_no);
    fields.put("financial_condition", Status);
    fields.put("email", Email);
    fields.put("password", Password);

    Call<Void> call = apiServices.executeSignup(fields);
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {

            if (response.code() == 200) {
                Toast.makeText(SignUp.this,
                        "Signed up successfully", Toast.LENGTH_LONG).show();
            } else if (response.code() == 400) {
                Toast.makeText(SignUp.this,
                        "Already registered", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(SignUp.this, t.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    });
}
}
