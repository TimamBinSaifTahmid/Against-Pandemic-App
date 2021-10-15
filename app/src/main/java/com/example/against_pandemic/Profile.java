package com.example.against_pandemic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
    TextView nidTextview, phoneNoTextview, locationTextview, currentConditionTextview, emailTextview, nameTextview;
    Button edit;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    private ApiServices apiServices;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nidTextview = (TextView) view.findViewById(R.id.mynid);
        phoneNoTextview = (TextView)view.findViewById(R.id.myPhoneNo);
        locationTextview = (TextView)view.findViewById(R.id.myLocation);
        currentConditionTextview = (TextView)view.findViewById(R.id.MyCurrentCondition);
        emailTextview= (TextView)view.findViewById(R.id.MyEmail);
        edit=(Button)view.findViewById(R.id.edit);
        nameTextview = (TextView)view.findViewById(R.id.myname);

        Call<MyProfileDetails> call = apiServices.getProfile();

        call.enqueue(new Callback<MyProfileDetails>() {

            @Override
            public void onResponse(Call<MyProfileDetails> call, Response<MyProfileDetails> response) {

                if (response.code() == 200) {

                    MyProfileDetails myProfileDetails = response.body();

                    String mynid = myProfileDetails.getNid();
                    String myphoneNo = myProfileDetails.getContact_info();
                    String mylocation = myProfileDetails.getLocation();
                    String mycurrentCondition = myProfileDetails.getFinancial_condition();
                    String myemail = myProfileDetails.getEmail();
                    String myname = myProfileDetails.getName();

                    nameTextview.setText(myname);
                    nidTextview.setText(mynid);
                    phoneNoTextview.setText(myphoneNo);
                    locationTextview.setText(mylocation);
                    currentConditionTextview.setText(mycurrentCondition);
                    emailTextview.setText(myemail);

                } else if (response.code() == 400) {
                }
            }

            @Override
            public void onFailure(@NotNull Call<MyProfileDetails> call, @NotNull Throwable t) {

                Log.e("last e aisha vejal", "onFailure: "+t.getMessage(),t );
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplication(), UpdateProfile.class);
                startActivity(intent);
            }
        });


    }
}