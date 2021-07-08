package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class AreaListResult {
    @SerializedName("count")
    public int count;
    @SerializedName("location")
    public String location;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
