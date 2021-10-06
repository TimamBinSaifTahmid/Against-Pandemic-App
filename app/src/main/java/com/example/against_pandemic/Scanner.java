package com.example.against_pandemic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;
import java.util.Random;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler  {
    Intent intent =getIntent();
    ZXingScannerView ScannerView;
    String qrCode;

    private ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView=new ZXingScannerView(Scanner.this);
        setContentView(ScannerView);





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);

        String poorNID = getIntent().getStringExtra("poorNID");
       // sendValidationData(poorNID);

    }




    public void sendValidationData(String poorNID){
        HashMap<String, String> qrInfo = new HashMap<>();

        qrInfo.put("poorNid",poorNID);
        qrInfo.put("qrCode",qrCode);


        Call<Void> call = apiServices.isValidQrCode(qrInfo);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {

                    Toast.makeText(Scanner.this,
                            "Transaction Succesful", Toast.LENGTH_LONG).show();

                } else if (response.code() == 400) {
                    Toast.makeText(Scanner.this,
                            "Not the same profile", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Scanner.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void handleResult(Result result) {
        qrCode=result.getText();
        if(!qrCode.isEmpty()){
            Log.d("QrCodescanned",qrCode);
            Toast.makeText(Scanner.this, qrCode,
                    Toast.LENGTH_LONG).show();
        }
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(Scanner.this);
        ScannerView.startCamera();
    }


}