package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VarificationPopup extends AppCompatActivity {
    Intent intent =getIntent();
    EditText verficationCode;
    Button submit,resend;

    private ApiServices apiServices;
    Validation validation=new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varification_popup);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        verficationCode =(EditText) findViewById(R.id.vCode);
        submit = (Button) findViewById(R.id.ok_code);
        resend = (Button) findViewById(R.id.resend_btn);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String v_code=verficationCode.getText().toString();
                Log.i("Varificatin_code",v_code);
                Toast.makeText(VarificationPopup.this,
                        "Verific"+v_code, Toast.LENGTH_LONG).show();
                HashMap<String, String> verifyCode = new HashMap<>();
                verifyCode.put("verificationCode", v_code);
                Call<Void> call = apiServices.verifyUser(verifyCode);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 200) {
                            Toast.makeText(VarificationPopup.this,
                                    "Email varified", Toast.LENGTH_LONG).show();
                            if(validation.isloggedin()==1) {
                                Intent intent = new Intent(VarificationPopup.this, Dashboard.class);
                                startActivity(intent);
                            }
                            else if(validation.isloggedin()==2){
                                Intent intent = new Intent(VarificationPopup.this, Dashboard.class);
                                startActivity(intent);
                            }
                        } else if (response.code() == 400) {

                            Toast.makeText(VarificationPopup.this,
                                    "Not varified, Try again", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(VarificationPopup.this, t.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}