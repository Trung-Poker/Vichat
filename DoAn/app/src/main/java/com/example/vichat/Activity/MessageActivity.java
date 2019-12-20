package com.example.vichat.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Message;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.ResultsChat;
import com.example.vichat.Model.UrlImage;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class MessageActivity extends Activity{

    CircleImageView profile_image;
    TextView username;
    ImageButton btn_send;
    EditText text_send;
    MessageAdapter messageAdapter;
    List<Message> mChat;
    RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    String UserId, UrlAvatar, UserName, UToken;
    //ValueEventListener seenListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        //setting recyclerView
        Intent intent = getIntent();
        UserId = intent.getStringExtra("UserId");
        UrlAvatar = intent.getStringExtra("Url_avatar");
        UserName = intent.getStringExtra("user_name");
        username = findViewById(R.id.username);
        profile_image = findViewById(R.id.profile_image);
        btn_send = findViewById(R.id.btn_send);
        text_send = findViewById(R.id.text_send);
        //anhxa
        username.setText(UserName);
        Picasso.get().load("http://192.168.0.106:8017/download/images/"+UrlAvatar).into(profile_image);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        UToken = sharedpreferences.getString(xToken, "");
        InsertMessage(UToken,UserId);
        //SentMessage(sharedpreferences.getString(xToken, ""),UserId,username.getText().toString(),0);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = APIClient.getClient();

                final RequestApi requestApi = retrofit.create(RequestApi.class);

                Call<Results> call = requestApi.sentMessages(UToken, UserId, text_send.getText().toString(),0);
                text_send.setText("");
                try {
                    call.enqueue(new Callback<Results>() {
                        @Override
                        public void onResponse(Call<Results> call, Response<Results> response) {
                            try {
                                Results a  =  response.body();
                                int status = (int) a.getStatus();
                                if (status == 200) {
                                    System.out.println("Sent Success!");
                                    InsertMessage(UToken,UserId);
                                } else {
                                    System.out.println("Fail");
                                }
                            }catch (Exception e)
                            {
                                System.out.println("error:" + e);
                            }
                        }
                        @Override
                        public void onFailure(Call<Results> call, Throwable t) {
                            System.out.println("error:" + t);
                        }
                    });
                }
                catch (Exception e)
                {
                    System.out.println("error:" + e);
                }

            }
        });
    }

    private void InsertMessage(String token, String uid)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<ResultsChat> call = requestApi.getMessages(uid,token);

        try {
            call.enqueue(new Callback<ResultsChat>() {
                @Override
                public void onResponse(Call<ResultsChat> call, Response<ResultsChat> response) {
                    try {
                        ResultsChat a  =  response.body();
                        int status = (int) a.getStatus();
                        if (status == 200) {
                            List<Message> User = a.getMessages();
                            if (!User.isEmpty()){
                                mChat = a.getMessages();
                                messageAdapter = new MessageAdapter(MessageActivity.this, mChat, UrlAvatar, UserId);
                                recyclerView.setAdapter(messageAdapter);
                            }
                        } else {
                            System.out.println("Fail");
                        }
                    }catch (Exception e)
                    {
                        System.out.println("error:" + e);
                    }
                }
                @Override
                public void onFailure(Call<ResultsChat> call, Throwable t) {
                    System.out.println("error:" + t);
                }
            });
            }
        catch (Exception e)
        {
            System.out.println("error:" + e);
        }
    }
    private void SentMessage( final String token, final String uid, final String text, final int conversationType) {

    }
}
