package com.example.vichat;

import com.example.vichat.Model.Results;
import com.example.vichat.Model.User;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestApi {

    @GET("user/")
    Call<User> getInfoUser(@Query("user_id") String userId);

    @POST("/register")
    Call<Results> signUp(@Body User.UserData user);

   @FormUrlEncoded
    @POST("/login/local")
    Call<Results> signIn(@Field("keyfinduser") String email, @Field("password") String password);
}
