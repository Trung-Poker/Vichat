package com.example.vichat.Model;

public class result2 {
    private String UserId;

    private String mgs ;

    public result2(String userId, String mgs) {
        UserId = userId;
        this.mgs = mgs;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }
}

