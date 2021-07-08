package com.example.against_pandemic;
import com.google.gson.annotations.SerializedName;
public class CoronaResult {
    @SerializedName("nid")
    public String nid;
    @SerializedName("result")
    public String result;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
