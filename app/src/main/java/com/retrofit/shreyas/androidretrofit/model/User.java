package com.retrofit.shreyas.androidretrofit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 31-01-2018.
 */

public class User {
    @SerializedName("name")
    @Expose
    private  String name;

    @SerializedName("num")
    @Expose
    private  int num;

    @SerializedName("password")
    @Expose
    private  String password;



    public User() {
    }

    public User(String name, int num, String password) {
        this.name = name;
        this.num = num;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
