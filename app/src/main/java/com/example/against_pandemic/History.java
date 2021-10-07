package com.example.against_pandemic;

public class History {
    public String provider;
    public String date;

    public History(String provider, String date) {
        this.provider = provider;
        this.date = date;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
