package com.example.against_pandemic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    Intent intent=getIntent();
    MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.maps_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.gift_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.corona_icon));


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new Maps();
                        break;

                    case 2:
                        fragment = new SelectedNeedyArea();
                        break;

                    case 3:
                        fragment = new CoronaStatus();
                        break;
                }

                loadFragment(fragment);
            }


        });

        bottomNavigation.setCount(1, "10");
        bottomNavigation.show(2, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();

            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Toast.makeText(getApplicationContext(),"released",Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void loadFragment(Fragment fragment) {

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    public void pop(){

    }
}


//        button =(Button)findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pop();
//            }
//        });
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        FragmentContainerView fragmentContainerView = findViewById(R.id.fragmentContainerView);
//
//        Button mapsButton = findViewById(R.id.maps);
//        Button helpButton = findViewById(R.id.dashboard2);
//        Button coronaButton = findViewById(R.id.coronaStatus);
//
//        mapsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });