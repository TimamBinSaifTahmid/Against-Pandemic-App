package com.example.against_pandemic;

public class NeedyPeopleListDetails {

    public String name;
    public String contact_info;
    public String type;
    public String nid;

    public NeedyPeopleListDetails(String name, String contact_info, String type, String nid) {
        this.name = name;
        this.contact_info = contact_info;
        this.type = type;
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }
}