package com.sanket.safewe;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NearByListClass {
    @SerializedName("ans")
    private List<NearbyClass> ans;

    public List<NearbyClass> getAns() {
        return ans;
    }

    public void setAns(List<NearbyClass> ans) {
        this.ans = ans;
    }

}
