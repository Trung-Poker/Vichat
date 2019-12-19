package com.example.vichat.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Message;
import com.example.vichat.Model.ResultsChat;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class MessageActivity extends Activity {

    CircleImageView profile_image;
    TextView username;
    ImageButton btn_send;
    EditText text_send;
    MessageAdapter messageAdapter;
    List<Message> mChat;
    RecyclerView recyclerView;
    SharedPreferences sharedpreferences;
    String UserId, UrlAvatar, UserName;
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

        Intent intent = getIntent();
        UserId = intent.getStringExtra("UserId");
        UrlAvatar = intent.getStringExtra("Url_avatar");
        UserName = intent.getStringExtra("user_name");

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        InsertMessage(sharedpreferences.getString(xToken, ""),UserId);

    }

    private void InsertMessage(String token, String Userid)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);


        Call<ResultsChat> call = requestApi.getMessages(token,Userid);
        call.enqueue(new Callback<ResultsChat>() {
            @Override
            public void onResponse(Call<ResultsChat> call, Response<ResultsChat> response) {
                try {
                    ResultsChat a  = (ResultsChat) response.body();
                    int status = a.getStatus();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("111");
                        }
                    },500);
                    System.out.println(status);

                    if (status == 200) {
                        List<Message> User = a.getMessages();
                        if (!User.isEmpty()){
                            mChat = a.getMessages();
                            for(Message b: mChat){
                                System.out.println(b.getText());
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    messageAdapter = new MessageAdapter(MessageActivity.this, mChat, UrlAvatar, UserId);
                                    recyclerView.setAdapter(messageAdapter);
                                }
                            },500);
                        }
                    } else {
                        System.out.println("Fail");
                    }
                }catch (Exception e)
                {
                    System.out.println("error");
                }
            }

            @Override
            public void onFailure(Call<ResultsChat> call, Throwable t) {

            }
        });
    }

}
