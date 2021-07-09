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

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SelectedNeedyArea extends Fragment implements AdapterView.OnItemClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView helpFoodList;
    private ListView helpMoneyList;

    Location location = new Location();

    private ApiServices apiServices;

    private String mParam1;
    private String mParam2;
    List<NeedyPeopleListDetails> needyPeopleList;

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



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.baseURL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiServices = retrofit.create(ApiServices.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_selected_needy_area, container, false);
    }

    String[] needFood = new String[3];
    String[] phoneNoFood = new String[3];

    String[] needMoney = new String[3];
    String[] phoneNomoney = new String[3];

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String area = location.getLocation();
        Log.d("needyareaname:",area);

        HashMap<String, String> areaNameMap = new HashMap<>();
        areaNameMap.put("location",area);

        helpFoodList = (ListView)view.findViewById(R.id.foodNeedList);
        helpMoneyList = (ListView)view.findViewById(R.id.moneyNeedList);

        Call<NeedyPeopleList> call = apiServices.posthelpseekerDetails(areaNameMap);

        NeedFoodAdapter adapter = new NeedFoodAdapter (getActivity(), needFood, phoneNoFood);
        NeedMoneyAdapter adapter2 = new NeedMoneyAdapter (getActivity(), needMoney, phoneNomoney);

        call.enqueue(new Callback<NeedyPeopleList>() {

            @Override
            public void onResponse(Call<NeedyPeopleList> call, Response<NeedyPeopleList> response) {
                // Toast.makeText(getContext(), "on response", Toast.LENGTH_SHORT).show();
                if (response.code() == 200) {
                    needyPeopleList = response.body().getNeedyArealist();

                    int size = needyPeopleList.size();

                    int j=0,k=0;
                    for(int i=0; i<+size;i++){


                        String typename = needyPeopleList.get(i).getType();

                        if (typename.equals("Food")){
                            needFood[j] = needyPeopleList.get(i).getName();
                            phoneNoFood[j] = String.valueOf(needyPeopleList.get(i).getContact_info());
                            j++;
                        }
                        else {
                            needMoney[k] = needyPeopleList.get(i).getName();
                            phoneNomoney[k] = String.valueOf(needyPeopleList.get(i).getContact_info());
                            k++;
                        }

                    }

                    adapter.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();

                } else if (response.code() == 400) {
                }
            }

            @Override
            public void onFailure(@NotNull Call<NeedyPeopleList> call, @NotNull Throwable t) {

                Log.e("helppeoplelist", "onFailure: "+t.getMessage(),t );
            }
            });

        helpFoodList.setAdapter(adapter);
        helpFoodList.setOnItemClickListener(this);



        helpMoneyList.setAdapter(adapter2);
        helpMoneyList.setOnItemClickListener(this);






    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedAreaName = needFood[i];
        Toast.makeText(getActivity(),selectedAreaName,Toast.LENGTH_SHORT).show();
    }

}