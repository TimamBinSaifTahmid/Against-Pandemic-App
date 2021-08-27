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
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.707310,90.415482)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.700411,90.374992)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.1785,90.7101)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.5721,90.1870)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.3586,90.3317)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.5841,89.9720)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(21.8311,92.3686)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9675,91.1119)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.2321,90.6631)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.3569,91.7832)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(21.4272,92.0058)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.4607,91.1809)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.0159,91.3976)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1117,91.9888)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.9447,90.8282)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.8246,91.1017)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.6533,92.1789)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8103,90.4125)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6019,89.8333)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9999,90.4203)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(23.0130,89.8224)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.4331,90.7866)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1649,90.1940)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8617,90.0003)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.5422,90.5305)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6238,90.5000)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9193,90.7176)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.7639,89.6467)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.2423,90.4348)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(24.2513,89.9167)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));




                googleMap.addCircle(new CircleOptions().center(new LatLng(22.6555,89.7662)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.6161,88.8263)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1778,89.1801)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.5450,89.1726)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.8456,89.5403)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.9088,89.1220)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.4855,89.4198)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.8052,88.6724)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(23.1657,89.4990)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
                googleMap.addCircle(new CircleOptions().center(new LatLng(22.7185,89.0705)).radius(5000).strokeWidth(3f).strokeColor(Color.RED).fillColor(Color.rgb(255,220,220)));
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