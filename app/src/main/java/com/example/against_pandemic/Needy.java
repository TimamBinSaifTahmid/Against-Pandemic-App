package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class Needy {
    public int count;
    public String location;

    public Needy(int count, String location) {
        this.count = count;
        this.location = location;
    }

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
