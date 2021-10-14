package com.example.against_pandemic;


import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("/signin")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> parameters);

    @POST("/register")
    Call<Void> executeSignup (@Body HashMap<String, String> fields);

    @POST("/emailVerification")
    Call<Void> verifyUser (@Body HashMap<String, String> verifyCode);

    @POST("/helpForm")
    Call<Void> submitHelpForm(@Body HashMap<String,String> helpform);

    @POST("/coronaResultshow")
    Call<CoronaResult> getCoronaResult(@Body HashMap<String, String> id);

    @GET("/helpSeekerList")
    Call<AreaListResult> getHelpSeekerlist();

    @POST("/helpSeekerDetails")
    Call<NeedyPeopleList> posthelpseekerDetails(@Body HashMap<String, String> areaNameMap);


    @POST("/helpSeekerProfile")
    Call<NeedyPeopleDetails> postNeedyPeopleDetails(@Body HashMap<String, String> needyNIDMap);

    @GET("/generateQrCode")
    Call<QRHash> getQrCode();

    @POST("/isValidQrCode")
    Call<Void> isValidQrCode(@Body HashMap<String,String> qrInfo);

    @GET("/isAskHelp")
    Call<Void> isAskHelp();

    @GET("/getProfile")
    Call<MyProfileDetails> getProfile();

    @POST("/updateProfile")
    Call<Void> updateProfile (@Body HashMap<String, String> editInfo);

}
