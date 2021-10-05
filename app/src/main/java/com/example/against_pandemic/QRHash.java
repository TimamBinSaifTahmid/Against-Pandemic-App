package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class QRHash {
    @SerializedName("qrcode")
    public String qrcode;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
