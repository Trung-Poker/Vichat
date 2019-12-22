package com.example.vichat.Networking;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.ResultsLogin;
import com.example.vichat.Model.ResultsChat;
import com.example.vichat.Model.ResultsNotification;
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
    Call<ResultsLogin> forgotpassword(@Field("email") String email);

    @FormUrlEncoded
    @PUT("/forgotPassword/setPassword")
    Call<ResultsLogin> setpassword(@Field("email") String email, @Field("code") String code, @Field("newPassword") String newpassword);

    @FormUrlEncoded
    @POST("/resetpassword")
    Call<ResultsLogin> resetpass(@Field("emai") String email);

    @POST("/register")
    Call<ResultsLogin> signUp(@Body User.UserData user);

   @FormUrlEncoded
    @POST("/login/local")
    Call<ResultsLogin> signIn(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("/conversation/getConversation")
    Call<ChatList> getConversation(@Field("token" ) String token);

    @FormUrlEncoded
    @POST("/messages/getMessages")
    Call<ResultsChat> getMessages(@Field("uid" ) String uid,@Field("token") String token);

    @FormUrlEncoded
    @POST("messages/sentMessage")
    Call<ResultsLogin> sentMessages(@Field("token" ) String token, @Field("uid") String uid, @Field("text") String text, @Field("conversationType") int conversationType);

    @FormUrlEncoded
    @POST("/notification/getNotification")
    Call<ResultsNotification> getNotification(@Field("token" ) String token);

    @FormUrlEncoded
    @POST("/login/facebook")
    Call<ResultsLogin> loginfacebook(@Field("uid" ) String uid, @Field("displayName" ) String displayName, @Field("gender" ) String gender, @Field("accessToken" ) String accessToken, @Field("email" ) String email );

    @FormUrlEncoded
    @POST("/login/facebook")
    Call<ResultsLogin> logingoogle(@Field("uid" ) String uid, @Field("displayName" ) String displayName, @Field("gender" ) String gender, @Field("accessToken" ) String accessToken, @Field("email" ) String email );

}
