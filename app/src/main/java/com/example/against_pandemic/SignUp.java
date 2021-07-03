package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
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
   /* private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText verficationCode;
    private Button submit,resend;
*/
    private ApiServices apiServices;
    Validation validation=new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        validation.setFlag(2);
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
        nid=findViewById(R.id.nid);
        name=findViewById(R.id.name);
        contact_no=findViewById(R.id.contact_no);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        financial_status=findViewById(R.id.radioGroup);
        signup=findViewById(R.id.signup_button);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
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
                //createPopup();
                Intent intent=new Intent(SignUp.this,VarificationPopup.class);
                startActivity(intent);
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

/*public void createPopup(){
    dialogBuilder = new AlertDialog.Builder(SignUp.this);
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
                        Toast.makeText(SignUp.this,
                                "Email varified", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(SignUp.this,LogIn.class);
                        startActivity(intent);
                    } else if (response.code() == 400) {
                        Toast.makeText(SignUp.this,
                                "Not varified, Try again", Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SignUp.this, t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    });
}*/
}
