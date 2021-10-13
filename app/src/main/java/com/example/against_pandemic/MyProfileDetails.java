package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class MyProfileDetails {
    @SerializedName("nid")
    public String nid;
    @SerializedName("name")
    public String name;
    @SerializedName("location")
    public String location;
    @SerializedName("financial_condition")
    public String financial_condition;
    @SerializedName("email")
    public String email;
    @SerializedName("contact_info")
    public String contact_info;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFinancial_condition() {
        return financial_condition;
    }

    public void setFinancial_condition(String financial_condition) {
        this.financial_condition = financial_condition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }
}
