package com.example.vichat.Model;

import android.app.Notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class NewsResponse {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("totalResults")
        @Expose
        private Integer totalResults;
        @SerializedName("articles")
        @Expose
        private List<Notifications> Nortification = null;

    public NewsResponse(String status, Integer totalResults, List<Notifications> nortification) {
        this.status = status;
        this.totalResults = totalResults;
        Nortification = nortification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Notifications> getNortification() {
        return Nortification;
    }

    public void setNortification(List<Notifications> nortification) {
        Nortification = nortification;
    }
}
