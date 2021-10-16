package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateRequest extends AppCompatActivity {
    Intent intent =getIntent();
    private Button done,reasonBtn,conditionBtn;
    private CheckBox food, money;
    private EditText reason, condition;
    private ArrayList<String> choice;
    String[] listreasons;
    boolean[] checkedreasons;
    ArrayList<Integer> reasons = new ArrayList<>();
    ArrayList<String> help_reasons = new ArrayList<>();
    String[] listCondition;
    boolean[] checkedCondition;
    ArrayList<Integer> conditions = new ArrayList<>();
    ArrayList<String> help_conditions = new ArrayList<>();
    private ApiServices apiServices;

    String help_Reason;
    String user_Condition;
    String partialChoise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_request);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        done =(Button) findViewById(R.id.editdone_btn);
        reasonBtn =(Button) findViewById(R.id.editreason_btn);
        conditionBtn =(Button) findViewById(R.id.editcondition_btn);
        food =(CheckBox) findViewById(R.id.editfood_box);
        money = (CheckBox)findViewById(R.id.editmoney_box);

        choice =new ArrayList<>();
        food.setOnClickListener(v -> {
            if(food.isChecked()){
                choice.add(food.getText().toString());
            }
            else{
                choice.remove(food.getText().toString());
            }
        });

        money.setOnClickListener(v -> {
            if(money.isChecked()){
                choice.add(money.getText().toString());
            }
            else{
                choice.remove(food.getText().toString());
            }
        });

        listreasons = getResources().getStringArray(R.array.help_reasons);
        checkedreasons = new boolean[listreasons.length];

        reasonBtn.setOnClickListener(v -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(UpdateRequest.this);
            mBuilder.setTitle(R.string.reasons_title);
            mBuilder.setMultiChoiceItems(listreasons, checkedreasons, (dialogInterface, position, isChecked) -> {
                if(isChecked){
                    reasons.add(position);
                }else{
                    reasons.remove((Integer.valueOf(position)));
                }
            });

            mBuilder.setCancelable(false);
            mBuilder.setPositiveButton(R.string.ok_label, (dialogInterface, which) -> {
                String item = "";
                for (int i = 0; i < reasons.size(); i++) {

                    item = item + listreasons[reasons.get(i)];
                    if (i != reasons.size() - 1) {
                        item = item + ", ";
                    }
                }
                help_reasons.add(item);
                //Toast.makeText(UpdateRequest.this, item, Toast.LENGTH_LONG).show();
            });

            mBuilder.setNegativeButton(R.string.dismiss_label, (dialogInterface, i) -> dialogInterface.dismiss());

            mBuilder.setNeutralButton(R.string.clear_all_label, (dialogInterface, which) -> {
                for (int i = 0; i < checkedreasons.length; i++) {
                    checkedreasons[i] = false;
                    reasons.clear();
                }
            });

            AlertDialog mDialog = mBuilder.create();
            mDialog.show();

        });

        listCondition = getResources().getStringArray(R.array.help_condition);
        checkedCondition = new boolean[listCondition.length];

        conditionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(UpdateRequest.this);
                mBuilder.setTitle(R.string.conditions_title);
                mBuilder.setMultiChoiceItems(listCondition, checkedCondition, (dialogInterface, position, isChecked) -> {
                    if(isChecked){
                        conditions.add(position);
                    }else{
                        conditions.remove((Integer.valueOf(position)));
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, (dialogInterface, which) -> {
                    String item = "";
                    for (int i = 0; i < conditions.size(); i++) {

                        item = item + listCondition[conditions.get(i)];
                        if (i != conditions.size() - 1) {
                            item = item + ", ";
                        }
                    }
                    if(conditions.size()>1){
                        //Intent intent=new Intent(UpdateRequest.this,UpdateRequest.class);
                        //startActivity(intent);
                        Toast.makeText(UpdateRequest.this, "Please select a single range", Toast.LENGTH_LONG).show();
                    }
                    else {
                        help_conditions.add(item);
                        //Toast.makeText(UpdateRequest.this, item, Toast.LENGTH_LONG).show();
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, (dialogInterface, i) -> dialogInterface.dismiss());

                mBuilder.setNeutralButton(R.string.clear_all_label, (dialogInterface, which) -> {
                    for (int i = 0; i < checkedCondition.length; i++) {
                        checkedCondition[i] = false;
                        conditions.clear();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        done.setOnClickListener(v -> {

            HashMap<String, String> updatehelpfrom = new HashMap<>();
            if(choice.isEmpty()){
                partialChoise="";
            }
            else if(choice.size()>1){
                Toast.makeText(UpdateRequest.this, "Choose either Food or Money", Toast.LENGTH_LONG).show();
            }
            else {
                partialChoise=choice.get(0);
                updatehelpfrom.put("type", partialChoise);
            }

            updatehelpfrom.put("reason",help_reasons.get(0));
            updatehelpfrom.put("condition",help_conditions.get(0));



            Call<Void> call = apiServices.updateHelpRequest(updatehelpfrom);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.code() == 200) {

                       // Intent intent = new Intent(UpdateRequest.this,Dashboard.class );
                        Log.d("dhuro", "onResponse: ");

                       // startActivity(intent);
                        Toast.makeText(UpdateRequest.this, "Submitted Successfully", Toast.LENGTH_LONG).show();

                    } else if (response.code() == 400) {
                        Toast.makeText(UpdateRequest.this, "Database Error", Toast.LENGTH_LONG).show();
                    }
                }


                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(UpdateRequest.this, "Database Error", Toast.LENGTH_LONG).show();
                }
            });

        });

    }
}