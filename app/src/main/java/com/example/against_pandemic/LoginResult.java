package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    private String Email;
    private String Password;

    public String getEmail() {
        return Email;
    }

}
