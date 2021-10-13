package com.example.against_pandemic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NeedyDetails extends Fragment implements AdapterView.OnItemClickListener {
    int num=0;
    private ZXingScannerView scannerView;
    private static final int RequestCode=1;

    List<History> HistoryList;

    String[] providerid = new String[50];
    String[] helpdate = new String[50];

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView nidTextView, phoneNoTextView, locationTextView, currentConditionTextView, reasonTextView, typeT;
    Button completeTransaction;
    ListView history;

    private String mParam1;
    private String mParam2;

    NeedyNID needyNID = new NeedyNID();

    private ApiServices apiServices;

    public NeedyDetails() {
    }


    public static NeedyDetails newInstance(String param1, String param2) {
        NeedyDetails fragment = new NeedyDetails();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_needy_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nidTextView = (TextView) view.findViewById(R.id.needyName);
        phoneNoTextView = (TextView)view.findViewById(R.id.needyPhoneNo);
        locationTextView = (TextView)view.findViewById(R.id.needyLocation);
        currentConditionTextView = (TextView)view.findViewById(R.id.needyCurrentCondition);
        reasonTextView = (TextView)view.findViewById(R.id.needyReason);
        completeTransaction=(Button)view.findViewById(R.id.complete_transaction);
        typeT = (TextView)view.findViewById(R.id.type);
        history =(ListView)view.findViewById(R.id.history);

        String needynid = needyNID.getNeedyNID();
        Log.d("needyareaname:",needynid);

        String needyname = needyNID.getNeedyName();

        HashMap<String, String> needyNIDMap = new HashMap<>();
        needyNIDMap.put("nid",needynid);


        Call<NeedyPeopleDetails> call = apiServices.postNeedyPeopleDetails(needyNIDMap);
        HistoryAdapter adapter = new HistoryAdapter (providerid, helpdate, getActivity());


        call.enqueue(new Callback<NeedyPeopleDetails>() {

            @Override
            public void onResponse(Call<NeedyPeopleDetails> call, Response<NeedyPeopleDetails> response) {

                if (response.code() == 200) {

                    NeedyPeopleDetails needyPeopleDetails = response.body();

                    String nidFront = needyname;
                    String phoneNoFront = needyPeopleDetails.getContact_info();
                    String locationFront = needyPeopleDetails.getLocation();
                    String currentConditionFront = needyPeopleDetails.getCurrent_situation();
                    String reasonFront = needyPeopleDetails.getReason();
                    String type = needyPeopleDetails.getType();
                    HistoryList= needyPeopleDetails.getHistoryList();

                    nidTextView.setText(nidFront);
                    phoneNoTextView.setText(phoneNoFront);
                    locationTextView.setText(locationFront);
                    currentConditionTextView.setText(currentConditionFront);
                    reasonTextView.setText(reasonFront);
                    typeT.setText(type);

                    int size = HistoryList.size();
                    Log.d("size",String.valueOf(size));
                    Log.d("sad",HistoryList.get(0).getProvider());
                    for(int i=0; i<+size;i++){

                        providerid[i] = HistoryList.get(i).getProvider();
                       // helpdate[i] = String.valueOf(HistoryList.get(i).getDate());
                        helpdate[i] = "Null";

                    }

                    adapter.notifyDataSetChanged();

                } else if (response.code() == 400) {
                }
            }

            @Override
            public void onFailure(@NotNull Call<NeedyPeopleDetails> call, @NotNull Throwable t) {

                Log.e("last e aisha vejal", "onFailure: "+t.getMessage(),t );
            }
        });

        history.setAdapter(adapter);

        history.setOnItemClickListener(this);

    completeTransaction.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            verifypermission();
            if(num==1) {
                Intent intent = new Intent(getActivity().getApplication(), Scanner.class);
                intent.putExtra("poorNID", needynid);
                startActivity(intent);
            }
        }
    });

    }

    private void verifypermission(){
        String [] permission ={Manifest.permission.CAMERA};
        if(ContextCompat.checkSelfPermission(getContext(),permission[0])== PackageManager.PERMISSION_GRANTED){
            num=1;
        }
        else {
            ActivityCompat.requestPermissions(getActivity(),permission,RequestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifypermission();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }
}