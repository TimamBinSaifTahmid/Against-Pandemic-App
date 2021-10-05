package com.example.against_pandemic;

import com.google.gson.annotations.SerializedName;

public class QRHash {
    @SerializedName("hashedQrCode")
    public String hashedQrCode;

    public String getHashedQrCode() {
        return hashedQrCode;
    }

    public void setHashedQrCode(String hashedQrCode) {
        this.hashedQrCode = hashedQrCode;
    }
}
