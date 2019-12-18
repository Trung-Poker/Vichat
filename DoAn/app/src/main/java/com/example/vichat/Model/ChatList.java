package com.example.vichat.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatList {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("conversations")
    @Expose
    private List<UserChat> conversations = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<UserChat> getConversations() {
        return conversations;
    }

    public void setConversations(List<UserChat> conversations) {
        this.conversations = conversations;
    }

}
