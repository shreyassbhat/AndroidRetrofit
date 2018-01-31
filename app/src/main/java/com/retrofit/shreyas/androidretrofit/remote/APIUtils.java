package com.retrofit.shreyas.androidretrofit.remote;

/**
 * Created by User on 31-01-2018.
 */

public class APIUtils {
    private APIUtils(){

    };
    public static final String API_URL = "http://swara.epizy.com/read/";

    public static UserService getUserService(){
        return  RetrofitClient.getClient(API_URL).create(UserService.class);
    }
}
