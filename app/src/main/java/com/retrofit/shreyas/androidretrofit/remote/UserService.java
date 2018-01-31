package com.retrofit.shreyas.androidretrofit.remote;

import com.retrofit.shreyas.androidretrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by User on 31-01-2018.
 */

public interface UserService {
    @GET("/read.php")
    Call<List<User>> getUsers();


    @POST("/create.php")
    Call<User> addUser(@Body User user);

    @PUT("/update.php/(name)")
    Call<User> updateUser(@Path("name")String name,@Body User user);

    @DELETE("/delete.php/(name)")
    Call<User> deleteUser(@Path("name") String name);
}
