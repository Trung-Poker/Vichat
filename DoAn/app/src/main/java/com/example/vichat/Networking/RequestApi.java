package com.example.vichat.Networking;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.User;
import com.example.vichat.Model.UserResults;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RequestApi {

    @FormUrlEncoded
    @POST("/users/getInfoUser")
    Call<UserResults> getInfoUser(@Field("token") String token);

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
    Call<Results> signIn(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/conversation/getConversation")
    Call<ChatList> getConversation(@Field("token" ) String token);

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
