package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    public String Email;
    @SerializedName("password")
    public String Password;

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

}
