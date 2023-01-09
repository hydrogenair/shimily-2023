package com.example.shmily;

import java.util.Date;

public class User {
    private String mEmail;
    private String mPassword;
    private String mGender;
    private String mMotto;
    private Date mBirthday;

    public User(String email) {
        mEmail = email;
    }


    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getMotto() {
        return mMotto;
    }

    public void setMotto(String motto) {
        mMotto = motto;
    }

    public Date getBirthday() {
        return mBirthday;
    }

    public void setBirthday(Date birthday) {
        mBirthday = birthday;
    }

    public void setEmail(String email) {
        mEmail = email;
    }
}
