package com.example.against_pandemic;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {
    @POST("/signin")
    Call<LoginResult> executeLogin(@Body HashMap<String, String> parameters);

    @POST("/register")
    Call<Void> executeSignup (@Body HashMap<String, String> fields);

    @POST("/emailVerification")
    Call<Void> verifyUser (@Body HashMap<String, String> verifyCode);

}
