package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("email")
    public String Email;
    @SerializedName("password")
    public String Password;
    @SerializedName("financial_condition")
    public String financial_condition;
    @SerializedName("nid")
    public String nid;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getFinancial_condition() {
        return financial_condition;
    }

    public void setFinancial_condition(String financial_condition) {
        this.financial_condition = financial_condition;
    }

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
