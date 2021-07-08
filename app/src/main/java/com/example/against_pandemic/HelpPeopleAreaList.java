package com.example.against_pandemic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HelpPeopleAreaList extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView helpAreaList;
    private String mParam1;
    private String mParam2;
    private ApiServices apiServices;

    public HelpPeopleAreaList() {
        // Required empty public constructor
    }




    public static HelpPeopleAreaList newInstance(String param1, String param2) {
        HelpPeopleAreaList fragment = new HelpPeopleAreaList();
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

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_help_people_area_list, container, false);
    }

    String[] area = {"Farmgate", "Bashundhara"};
    String[] number = {"154", "45"};

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        helpAreaList = (ListView)view.findViewById(R.id.helpPeopleList);
        AreaNameAdapter adapter = new AreaNameAdapter (getActivity(), area, number);

        helpAreaList.setAdapter(adapter);
        helpAreaList.setOnItemClickListener(this);
        loadHelpList();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedAreaName = area[i];
        Toast.makeText(getActivity(),selectedAreaName,Toast.LENGTH_SHORT).show();

    }

    private void loadHelpList() {
        HashMap<String, String> helpSeekerlist = new HashMap<>();



        Call<AreaListResult> call = apiServices.getHelpSeekerlist(helpSeekerlist);
        Toast.makeText(getContext(), "get post", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<AreaListResult>() {
            @Override
            public void onResponse(Call<AreaListResult> call, Response<AreaListResult> response) {
                Toast.makeText(getContext(), "on response", Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    List<AreaListResult> results= new ArrayList<AreaListResult>();
                    //AreaListResult[] results = new AreaListResult[5];
                    results.add(response.body());


                    Toast.makeText(getActivity(), String.valueOf(response.code()),
                            Toast.LENGTH_LONG).show();

                   // Toast.makeText(getActivity(),  results.get(0).getLocation(),Toast.LENGTH_LONG).show();



                } else if (response.code() == 400) {
                    Toast.makeText(getActivity(), "Not Tested",
                            Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<AreaListResult> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

}