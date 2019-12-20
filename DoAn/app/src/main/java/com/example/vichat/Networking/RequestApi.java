package com.example.vichat.Networking;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.ResultsChat;
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

    @FormUrlEncoded
    @POST("/messages/getMessages")
    Call<ResultsChat> getMessages(@Field("uid" ) String uid,@Field("token") String token);

    @FormUrlEncoded
    @POST("messages/sentMessage")
    Call<Results> sentMessages(@Field("token" ) String token, @Field("uid") String uid, @Field("text") String text, @Field("conversationType") int conversationType);

}
