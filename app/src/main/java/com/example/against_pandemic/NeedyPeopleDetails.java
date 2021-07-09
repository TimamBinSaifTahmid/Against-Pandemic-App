package com.example.against_pandemic;

public class NeedyPeopleDetails {

    public String nid;
    public String location;
    public String current_situation;
    public String reason;
    public String contact_info;

    public NeedyPeopleDetails(String nid, String location, String current_situation, String reason, String contact_info) {
        this.nid = nid;
        this.location = location;
        this.current_situation = current_situation;
        this.reason = reason;
        this.contact_info = contact_info;
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
}
