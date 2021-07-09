package com.example.against_pandemic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class SelectedNeedyArea extends Fragment implements AdapterView.OnItemClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView helpFoodList;
    private ListView helpMoneyList;

    Location location = new Location();

    private String mParam1;
    private String mParam2;

    public SelectedNeedyArea() {
    }


    public static SelectedNeedyArea newInstance(String param1, String param2) {
        SelectedNeedyArea fragment = new SelectedNeedyArea();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Users users=new Users();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String area = location.getLocation();

        Log.d("needyareaname:",area);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_selected_needy_area, container, false);
    }

    String[] needFood = {"Tahmid", "Bashundhara"};
    String[] phoneNoFood = {"154", "45"};

    String[] needMoney = {"Utsha", "this is the 2nd list"};
    String[] phoneNomoney = {"154", "45"};

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






        helpFoodList = (ListView)view.findViewById(R.id.foodNeedList);
        NeedFoodAdapter adapter = new NeedFoodAdapter (getActivity(), needFood, phoneNoFood);

        helpFoodList.setAdapter(adapter);
        helpFoodList.setOnItemClickListener(this);



        helpMoneyList = (ListView)view.findViewById(R.id.moneyNeedList);
        NeedMoneyAdapter adapter2 = new NeedMoneyAdapter (getActivity(), needMoney, phoneNomoney);

        helpMoneyList.setAdapter(adapter2);
        helpMoneyList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedAreaName = needFood[i];
        Toast.makeText(getActivity(),selectedAreaName,Toast.LENGTH_SHORT).show();
    }

}