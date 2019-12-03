package com.example.vichat.Networking;

import com.example.vichat.Model.Results;
import com.example.vichat.Model.User;
import com.example.vichat.Model.UserResults;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RequestApi {

    @GET("/users/getInfoUser")
    Call<UserResults> getInfoUser(@Field("email") String email);

    @FormUrlEncoded
    @POST("/forgotPassword/getCodeVerify")
    Call<Results> forgotpassword(@Field("email") String email);

    @FormUrlEncoded
    @PUT("/forgotPassword/setPassword")
    Call<Results> setpassword(@Field("email") String email, @Field("code") String code, @Field("newPassword") String newpassword);

    @FormUrlEncoded
    @POST("/resetpassword")
    Call<Results> resetpass(@Field("emai") String email);

    @POST("/register")
    Call<Results> signUp(@Body User.UserData user);

   @FormUrlEncoded
    @POST("/login/local")
    Call<Results> signIn(@Field("keyfinduser") String email, @Field("password") String password);

   //cache
   /*@Headers({
           "Static-Header1: 123"
           Us
   })

    @POST("/user")
    Call<Results> listUser(
            @Header("Cache_Control" String cache),
            @Body User user
   )*/
}
