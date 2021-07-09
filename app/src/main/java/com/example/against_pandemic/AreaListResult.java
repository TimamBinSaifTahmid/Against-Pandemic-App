package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AreaListResult {
    @SerializedName("needylist")
  List<Needy> needylist;

    public List<Needy> getNeedylist() {
        return needylist;
    }

    public void setNeedylist(List<Needy> needylist) {
        this.needylist = needylist;
    }
}
