package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateProfile extends AppCompatActivity {
    Intent intent =getIntent();

    EditText name;
    EditText currentlocation;
    EditText contact_no;
    EditText email;
    RadioGroup financial_status;
    RadioButton status;
    Button submit;
    String Status,NID,userName,Contact_no,Email,Password,location;
    private ApiServices apiServices;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        name=(EditText) findViewById(R.id.editname);
        currentlocation = (EditText) findViewById(R.id.editcurrentlocation);
        contact_no =(EditText) findViewById(R.id.editcontact_no);
        email=(EditText) findViewById(R.id.editemail);
        submit=(Button) findViewById(R.id.submit);
        financial_status=findViewById(R.id.editradioGroup);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid= financial_status.getCheckedRadioButtonId();
                status =findViewById(radioid);
                Status=status.getText().toString();
                NID="";
                userName=name.getText().toString();
                location=currentlocation.getText().toString();
                Contact_no=contact_no.getText().toString();
                Email=email.getText().toString();
                if(userName.length()==0){
                    userName="";
                }
                if(location.length()==0){
                    location="";
                }
                if(Contact_no.length()==0){
                    Contact_no="";
                }
                if(Email.length()==0){
                    Email="";
                }
                postUpdate();
            }
        });

    }

    public void postUpdate(){
        HashMap<String, String> editInfo = new HashMap<>();
        editInfo.put("nid", NID);
        editInfo.put("name",userName);
        editInfo.put("location",location);
        editInfo.put("contact_info", Contact_no);
        editInfo.put("financial_condition", Status);
        editInfo.put("email", Email);

        Log.d("NID",NID.toString());
        Log.d("location",location.toString());


        Call<Void> call = apiServices.updateProfile(editInfo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(UpdateProfile.this,
                            "successfully", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(UpdateProfile.this,LogIn.class);
                    startActivity(intent);

                } else if (response.code() == 400) {
                    Toast.makeText(UpdateProfile.this,
                            "not succesful", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateProfile.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}