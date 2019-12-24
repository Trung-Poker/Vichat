package com.example.vichat.Networking;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.ResultsContact;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.ResultsChat;
import com.example.vichat.Model.ResultsContactAdd;
import com.example.vichat.Model.ResultsNotification;
import com.example.vichat.Model.User;
import com.example.vichat.Model.UserResults;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @FormUrlEncoded
    @POST("/notification/getNotification")
    Call<ResultsNotification> getNotification(@Field("token" ) String token);

    @FormUrlEncoded
    @POST("/login/facebook")
    Call<Results> loginfacebook(@Field("uid" ) String uid, @Field("displayName" ) String displayName, @Field("gender" ) String gender, @Field("accessToken" ) String accessToken, @Field("email" ) String email );

    @FormUrlEncoded
    @POST("/login/google")
    Call<Results> logingoogle(@Field("uid" ) String uid, @Field("displayName" ) String displayName, @Field("gender" ) String gender, @Field("accessToken" ) String accessToken, @Field("email" ) String email );

    @FormUrlEncoded
    @POST("/contact/getContacts")
    Call<ResultsContact> getContact(@Field("token") String token);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/contact/removeContact", hasBody = true)
    Call<Results> deleteContact(@Field("token") String token, @Field("contactId") String contactId);

    @FormUrlEncoded
    @HTTP(method = "POST", path = "/contact/findUsers/{keyword}", hasBody = true)
    Call<ResultsContactAdd> findUser(@Field("token") String token, @Path(value = "keyword") String keyword);

    @FormUrlEncoded
    @POST("/contact/addNew")
    Call<Results> addContact(@Field("token" ) String token, @Field("uid") String uid);

    @FormUrlEncoded
    @POST("/contact/getContactsSent")
    Call<ResultsContact> getContactSent(@Field("token") String token);

    @FormUrlEncoded
    @POST("/contact/getContactsReceived")
    Call<ResultsContact> getContactReceived(@Field("token") String token);

    @FormUrlEncoded
    @POST("/logout")
    Call<Results> logout(@Field("token") String token);

    @FormUrlEncoded
    @PUT("/user/update-info")
    Call<Results> UpdateInfo(@Field("token") String token, @Field("username") String username, @Field("address") String address, @Field("phone") String phone);

    @FormUrlEncoded
    @PUT("/user/update-password")
    Call<Results> UpdatePassword(@Field("token") String token, @Field("currentPassword") String currentPassword, @Field("newPassword") String newPassword, @Field("confirmNewPassword") String confirmNewPassword);

    @FormUrlEncoded
    @PUT("/contact/approve-request-contact-received")
    Call<Results> ApproveContact(@Field("token" ) String token, @Field("uid") String uid);
}
