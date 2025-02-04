package com.example.vichat.Model;

public class UserResults {
    String username, email, phone, address, avatar;

    public UserResults()
    {
        return;
    }
    public UserResults(String username, String email, String phone, String address, String avatar) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvartar() {
        return avatar;
    }

    public void setAvartar(String avartar) {
        this.avatar = avartar;
    }
}
