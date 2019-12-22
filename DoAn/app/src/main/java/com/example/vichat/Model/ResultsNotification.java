package com.example.vichat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsNotification {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("mgs")
    @Expose
    private List<Notifications> mgs = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Notifications> getNotification() {
        return mgs;
    }

    public void setMgs(List<Notifications> mgs) {
        this.mgs = mgs;
    }

}