package com.example.vichat.Model;

public class Results {
    private int Status;
    private String mgs;

    public Results(int status, String mgs, String useId) {
        this.Status = status;
        this.mgs = mgs;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        this.Status = status;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

}

