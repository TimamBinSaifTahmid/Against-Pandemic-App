package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogIn extends AppCompatActivity {
    LogIn logInobj=new LogIn();
    Intent intent=getIntent();
    EditText email;
    EditText password;
    Button login;
    Button signup;
    String Email,Password;
    private ApiServices apiServices;
    Validation validation=new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login_button);
        signup=findViewById(R.id.signup_question);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email=email.getText().toString();
                Password=password.getText().toString();
                String validEmail=validation.validateEmail(Email);
                String validPassword=validation.validatePassword(Password);
                if(validEmail!="null"&&validPassword!="null") {
                    email.setError(validEmail);
                    password.setError(validPassword);
                }
                else {
                    getPosts();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LogIn.this,SignUp.class);
                startActivity(intent);
            }
        });
    }


    private void getPosts() {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("email", Email);
        parameters.put("password",Password);

        Call<LoginResult> call = apiServices.executeLogin(parameters);

        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                if (response.code() == 200) {

                    LoginResult result = response.body();

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(LogIn.this);
                    builder1.setMessage("LogIn Successfull");
                    builder1.setMessage(result.getEmail());

                    builder1.show();

                } else if (response.code() == 400) {
                    Toast.makeText(LogIn.this, "Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LogIn.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
}