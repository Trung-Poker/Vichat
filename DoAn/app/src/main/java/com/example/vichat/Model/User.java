package com.example.vichat.Model;

import android.service.autofill.UserData;

public class User {
    private String result_code;

    private String userId;

    private UserData info;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResultCode() {
        return result_code;
    }

    public void setResultCode(String resultCode) {
        this.result_code = resultCode;
    }

    public UserData getInfo() {
        return info;
    }

    public void setInfo(UserData info) {
        this.info = info;
    }

    public static class UserData {
        private String email;
        private String gender;
        private String password;
        private String password_confirmation;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword_confirmation() {
            return password_confirmation;
        }

        public void setPassword_confirmation(String password_confirmation) {
            this.password_confirmation = password_confirmation;
        }
        @Override
        public String toString() {
            return
                    "email: " + email +
                    "\ngender: " + gender +
                            "\npassword: " + password +
                            "\npassword_confirmation: " + password_confirmation;
    }

    }
}
