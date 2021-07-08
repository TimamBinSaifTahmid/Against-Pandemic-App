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
//    LogIn logInobj=new LogIn();
    Intent intent=getIntent();
    EditText email;
    EditText password;
    Button login;
    Button signup;
    String Email,Password;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText verficationCode;
    private Button submit,resend;
    private ApiServices apiServices;
    Validation validation=new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        validation.setFlag(1);
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login_button);
        signup=findViewById(R.id.signup_question);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
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
        Toast.makeText(getApplicationContext(),"get post",Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Toast.makeText(getApplicationContext(),"on response",Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    LoginResult result = response.body();




                    Intent intent=new Intent(LogIn.this,MainActivity.class);
                    startActivity(intent);

                } else if (response.code() == 400) {
                    Toast.makeText(LogIn.this, "Wrong Credentials",
                            Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 405) {
                    //verifyEmail();

                    Intent intent=new Intent(LogIn.this,VarificationPopup.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Toast.makeText(LogIn.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    /*public void verifyEmail(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View codePopupView = getLayoutInflater().inflate(R.layout.varification_code_popup,null);
        verficationCode =(EditText) findViewById(R.id.verificationCode);
        submit = (Button) findViewById(R.id.ok_code);
        resend = (Button) findViewById(R.id.resend_btn);
        String v_code = verficationCode.getText().toString();

        dialogBuilder.setView(codePopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> verifyCode = new HashMap<>();
                verifyCode.put("verficationCode", v_code);
                Call<Void> call = apiServices.verifyUser(verifyCode);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(LogIn.this,
                                    "login successfully", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(LogIn.this,Dashboard.class);
                            startActivity(intent);
                        } else if (response.code() == 400) {
                            Toast.makeText(LogIn.this,
                                    "Not varified, Try again", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(LogIn.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }*/
}