package com.example.against_pandemic;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.jetbrains.annotations.NotNull;


public class Maps extends Fragment {
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    Button currentLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        currentLocation = view.findViewById(R.id.currentLocation);
        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
                FetchCoronaDatabase fetchCoronaDatabase=new FetchCoronaDatabase();
                String[] str=fetchCoronaDatabase.getStringData();
                googleMap.addMarker(new MarkerOptions().position(new LatLng(23.810331,90.412521)));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(23.810331,90.412521),
                        10f
                ));

                int[] coronaRadius=new int[64];
                int radCounter=0;

                for (int iccha = 3; iccha<67;iccha++){

                    if (Integer.parseInt(str[iccha]) < 600){
                        coronaRadius[radCounter] = Integer.parseInt(str[iccha])*3;
                        radCounter++;
                    }
                    else if (Integer.parseInt(str[iccha]) < 1000){
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*2.5);
                        radCounter++;
                    }

                    else if (Integer.parseInt(str[iccha]) < 4000){
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*1.8);
                        radCounter++;
                    }

                    else if (Integer.parseInt(str[iccha]) < 8000){
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*1.5);
                        radCounter++;
                    }
                    else if (Integer.parseInt(str[iccha]) < 12000){
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*1.2);
                        radCounter++;
                    }

                    else if (Integer.parseInt(str[iccha]) < 20000){
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*0.8);
                        radCounter++;
                    }
                    else{
                        coronaRadius[radCounter] = (int) (Integer.parseInt(str[iccha])*0.25);
                        radCounter++;
                    }

                }


                googleMap.addCircle(new CircleOptions().center(new LatLng(23.7073,90.4154)).radius(coronaRadius[0]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.7004,90.3749)).radius(coronaRadius[1]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.1785,90.7101)).radius(coronaRadius[2]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.5721,90.1870)).radius(coronaRadius[3]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.3586,90.3317)).radius(coronaRadius[4]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.5841,89.9720)).radius(coronaRadius[5]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(21.8311,92.3686)).radius(coronaRadius[6]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9675,91.1119)).radius(coronaRadius[7]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.2321,90.6631)).radius(coronaRadius[8]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.3569,91.7832)).radius(coronaRadius[9]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(21.4272,92.0058)).radius(coronaRadius[10]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.4607,91.1809)).radius(coronaRadius[11]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.0159,91.3976)).radius(coronaRadius[12]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1117,91.9888)).radius(coronaRadius[13]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.9447,90.8282)).radius(coronaRadius[14]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.8246,91.1017)).radius(coronaRadius[15]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.6533,92.1789)).radius(coronaRadius[16]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8103,90.4125)).radius(coronaRadius[17]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6019,89.8333)).radius(coronaRadius[18]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9999,90.4203)).radius(coronaRadius[19]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(23.0130,89.8224)).radius(coronaRadius[20]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.4331,90.7866)).radius(coronaRadius[21]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1649,90.1940)).radius(coronaRadius[22]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8617,90.0003)).radius(coronaRadius[23]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.5422,90.5305)).radius(coronaRadius[24]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6238,90.5000)).radius(coronaRadius[25]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9193,90.7176)).radius(coronaRadius[26]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.7639,89.6467)).radius(coronaRadius[27]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.2423,90.4348)).radius(coronaRadius[28]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.2513,89.9167)).radius(coronaRadius[29]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(22.6555,89.7662)).radius(coronaRadius[30]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6161,88.8263)).radius(coronaRadius[31]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1778,89.1801)).radius(coronaRadius[32]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.5450,89.1726)).radius(coronaRadius[33]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.8456,89.5403)).radius(coronaRadius[34]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9088,89.1220)).radius(coronaRadius[35]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.4855,89.4198)).radius(coronaRadius[36]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8052,88.6724)).radius(coronaRadius[37]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1657,89.4990)).radius(coronaRadius[38]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.7185,89.0705)).radius(coronaRadius[39]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(24.9196,89.9481)).radius(coronaRadius[40]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.7563,90.4064)).radius(coronaRadius[41]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.8835,90.7289)).radius(coronaRadius[42]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.0188,90.0175)).radius(coronaRadius[43]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.8509,89.3710)).radius(coronaRadius[44]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.7413,88.2912)).radius(coronaRadius[45]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.0968,89.0227)).radius(coronaRadius[46]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.7936,88.9318)).radius(coronaRadius[47]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.4260,89.0179)).radius(coronaRadius[48]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.0129,89.2591)).radius(coronaRadius[49]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(24.3745,88.6042)).radius(coronaRadius[50]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.4526,89.6816)).radius(coronaRadius[51]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.6279,88.6332)).radius(coronaRadius[52]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.3290,89.5415)).radius(coronaRadius[53]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.8103,89.6487)).radius(coronaRadius[54]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.9923,89.2847)).radius(coronaRadius[55]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.9363,88.8407)).radius(coronaRadius[56]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(26.2709,88.5952)).radius(coronaRadius[57]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.7439,89.2752)).radius(coronaRadius[58]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(26.0418,88.4283)).radius(coronaRadius[59]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(24.3840,91.4169)).radius(coronaRadius[60]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.4809,91.7643)).radius(coronaRadius[61]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(25.0667,91.4072)).radius(coronaRadius[62]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.8949,91.8687)).radius(coronaRadius[63]).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.argb(50,255,0, 0)));


                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

                    @Override
                    public void onMapClick(@NonNull @NotNull LatLng latLng) {
                        MarkerOptions markerOptions1=new MarkerOptions();
                        markerOptions1.position(latLng);
                        markerOptions1.title(latLng.latitude+" : "+latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                      googleMap.addMarker(markerOptions1);
                    }

                });
            }
        });
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        currentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getActivity()
                        , Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();



                } else {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                    ,Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
                }
            }
        });




        return view;
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            client.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {

                                Log.d("asd","asd");
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                MarkerOptions options = new MarkerOptions().position(latLng).title("current location");
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                googleMap.addMarker(options);
                            }
                        });
                    } else {
                        LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(10000)
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback() {
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                            }
                        };
                        client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }else {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if(requestCode==44){
            if(grantResults.length>0 && grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}