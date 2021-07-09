package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaListResult {

    @SerializedName("needylist")
    List<Needy> needylistList;

    public AreaListResult(List<Needy> needylistList) {
        this.needylistList = needylistList;
    }

    public List<Needy> getNeedylist() {
        return needylistList;
    }

    public void setNeedylist(List<Needy> needylistList) {

        this.needylistList = needylistList;
    }
}
