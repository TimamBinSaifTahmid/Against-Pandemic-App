package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelpForm extends AppCompatActivity {
    Intent intent =getIntent();
    private Button done;
    private CheckBox food, money;
    private EditText reason, condition;

    private ApiServices apiServices;

    String help_Reason;
    String user_Condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_form);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.104:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        done =(Button) findViewById(R.id.done_btn);
        food =(CheckBox) findViewById(R.id.food_box);
        money = (CheckBox)findViewById(R.id.money_box);
        reason =(EditText)findViewById(R.id.reason_writer);
        condition =(EditText)findViewById(R.id.condition_writer);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help_Reason = reason.getText().toString();
                user_Condition = condition.getText().toString();
               if(help_Reason==null||user_Condition==null){
                    reason.setError("You must Give a reason for asking help");
                    condition.setError("You must Give the description of your current condition to set helpline priority");
               }
               else {
                   askingHelp();
               }

            }
        });


    }

    private void askingHelp() {
        HashMap<String, String> helpfrom = new HashMap<>();
        helpfrom.put("reason",help_Reason);
        helpfrom.put("condition",user_Condition);

        Call<Void> call = apiServices.submitHelpForm(helpfrom);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(HelpForm.this,
                            "Submitted successfully", Toast.LENGTH_LONG).show();

                } else if (response.code() == 400) {
                    Toast.makeText(HelpForm.this,
                            "already on list", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(HelpForm.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}