package com.example.against_pandemic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dashboard extends Fragment {
    private Button askHelp;
    private Button generateQr;
    public String hashQr;
    private ApiServices apiServices;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.activity_dashboard, container, false);


        init(view);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);

        askHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), HelpForm.class);
                startActivity(intent);
            }
        });


        verifyNeedy();
        generateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<QRHash> call = apiServices.getQrCode();

                call.enqueue(new Callback<QRHash>() {

                    @Override
                    public void onResponse(Call<QRHash> call, Response<QRHash> response) {
                        Log.d("resposeCode",String.valueOf(response.code()));
                        if (response.code() == 200) {

                            QRHash qrHash = response.body();
                            Log.d("qrhash",qrHash.getQrcode());

                            hashQr = qrHash.getQrcode();

                            Intent intent = new Intent(getActivity().getApplication(), QrGenerator.class);
                            intent.putExtra("qrKey",hashQr);
                            startActivity(intent);


                        } else if (response.code() == 400) {
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<QRHash> call, @NotNull Throwable t) {

                        Log.e("last e aisha vejal", "onFailure: "+t.getMessage(),t );
                    }
                });

            }
        });

        return view;
    }

    private void verifyNeedy() {
        Call<Void> call = apiServices.isAskHelp();

        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d("resposeCode",String.valueOf(response.code()));
                if (response.code() == 200) {
                    generateQr.setVisibility(View.VISIBLE);
                }
                else if (response.code() == 400) {
                    generateQr.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {

                Log.e("last e aisha vejal", "onFailure: "+t.getMessage(),t );
            }
        });

    }

    public void init(View view){
        askHelp =view.findViewById(R.id.askHelp_btn);
        generateQr = view.findViewById(R.id.generateQr_btn);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}