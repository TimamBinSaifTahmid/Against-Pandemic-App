package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NeedyPeopleDetails {

    public String nid;
    public String location;
    public String current_situation;
    public String reason;
    public String contact_info;
    @SerializedName("history")
    public String historyNumber;
    public String type;

    public NeedyPeopleDetails(String nid, String location, String current_situation, String reason, String contact_info, String historyNumber, String type) {
        this.nid = nid;
        this.location = location;
        this.current_situation = current_situation;
        this.reason = reason;
        this.contact_info = contact_info;

        this.historyNumber=historyNumber;
        this.type=type;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurrent_situation() {
        return current_situation;
    }

    public void setCurrent_situation(String current_situation) {
        this.current_situation = current_situation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

//    public List<History> getHistoryList() {
//        return historyList;
//    }
//
//    public void setHistoryList(List<History> historyList) {
//        this.historyList = historyList;
//    }


    public String getHistoryNumber() {
        return historyNumber;
    }

    public void setHistoryNumber(String historyNumber) {
        this.historyNumber = historyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
