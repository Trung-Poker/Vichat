package com.example.vichat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsContactAdd {
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("mgs")
    @Expose
    private List<ContactAdd> mgs = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ContactAdd> getMgs() {
        return mgs;
    }

    public void setMgs(List<ContactAdd> mgs) {
        this.mgs = mgs;
    }
}
