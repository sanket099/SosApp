package com.sanket.safewe;

import com.google.gson.annotations.SerializedName;

public class NearbyClass {
    @SerializedName("uid")
    private String uid;
    @SerializedName("long")
    private String longi;
    @SerializedName("lat")
    private String lat;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
