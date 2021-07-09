package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NeedyPeopleList {
    @SerializedName("needylistarea")
    List<NeedyPeopleListDetails> needyArealist;

    public NeedyPeopleList(List<NeedyPeopleListDetails> needyArealist) {
        this.needyArealist = needyArealist;
    }

    public List<NeedyPeopleListDetails> getNeedyArealist() {
        return needyArealist;
    }

    public void setNeedyArealist(List<NeedyPeopleListDetails> needyArealist) {
        this.needyArealist = needyArealist;
    }
}
