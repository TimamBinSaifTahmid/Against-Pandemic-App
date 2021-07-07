package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelpForm extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_form);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        done =(Button) findViewById(R.id.done_btn);
        reasonBtn =(Button) findViewById(R.id.reason_btn);
        conditionBtn =(Button) findViewById(R.id.condition_btn);
        food =(CheckBox) findViewById(R.id.food_box);
        money = (CheckBox)findViewById(R.id.money_box);
        //reason =(EditText)findViewById(R.id.reason_writer);
        //condition =(EditText)findViewById(R.id.condition_writer);
        choice =new ArrayList<>();
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(food.isChecked()){
                    choice.add(food.getText().toString());
                }
                else{
                    choice.remove(food.getText().toString());
                }
            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(money.isChecked()){
                    choice.add(food.getText().toString());
                }
                else{
                    choice.remove(food.getText().toString());
                }
            }
        });

        listreasons = getResources().getStringArray(R.array.help_reasons);
        checkedreasons = new boolean[listreasons.length];

        reasonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(HelpForm.this);
                mBuilder.setTitle(R.string.reasons_title);
                mBuilder.setMultiChoiceItems(listreasons, checkedreasons, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            reasons.add(position);
                        }else{
                            reasons.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < reasons.size(); i++) {
                            help_reasons.add(listreasons[reasons.get(i)]);
                            item = item + listreasons[reasons.get(i)];
                            if (i != reasons.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        Toast.makeText(HelpForm.this, item, Toast.LENGTH_LONG).show();
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedreasons.length; i++) {
                            checkedreasons[i] = false;
                            reasons.clear();
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        listCondition = getResources().getStringArray(R.array.help_condition);
        checkedCondition = new boolean[listCondition.length];

        conditionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(HelpForm.this);
                mBuilder.setTitle(R.string.conditions_title);
                mBuilder.setMultiChoiceItems(listCondition, checkedCondition, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            conditions.add(position);
                        }else{
                            conditions.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < conditions.size(); i++) {
                            help_conditions.add(listCondition[conditions.get(i)]);
                            item = item + listCondition[conditions.get(i)];
                            if (i != conditions.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        Toast.makeText(HelpForm.this, item, Toast.LENGTH_LONG).show();
                    }
                });

                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedCondition.length; i++) {
                            checkedCondition[i] = false;
                            conditions.clear();
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //help_Reason = reason.getText().toString();
                //user_Condition = condition.getText().toString();

                askingHelp();


            }
        });


    }

    private void askingHelp() {
        HashMap<String, ArrayList<String>> helpfrom = new HashMap<>();
        if(choice.isEmpty()){
            Toast.makeText(HelpForm.this,
                    "choose a helpline", Toast.LENGTH_LONG).show();
        }
        else if(choice.size()>1){
            Toast.makeText(HelpForm.this,
                    "choose either food or money", Toast.LENGTH_LONG).show();
        }
        else {
            helpfrom.put("choice",choice);
        }

        helpfrom.put("reason",help_reasons);
        helpfrom.put("condition",help_conditions);


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