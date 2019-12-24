package com.example.vichat.ui.Message;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.UserChat;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.APISocket;
import com.example.vichat.Networking.ListenerSocket;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class MessageFragment extends Fragment {

    //public MessageFragment(){};
    private RecyclerView recyclerView;
    private UserAdapter UserAdapter;
    private  List<UserChat> mUser;
    private Socket socket;
    private String status = "false";
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView( LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        //ArrayList<UserAdapter> arrayList = new ArrayList<>();
        InsertUser(sharedpreferences.getString(xToken, ""));
       // status = new ListenerSocket().ListenerSocketOn(getActivity(),"response-chat-text");
       // System.out.println(status);
       // if(status.compareTo("true") == 1 ){
         //   InsertUser(sharedpreferences.getString(xToken, ""));
       // }


        // add du lieu()
        //ArrayList;
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                UserAdapter = new UserAdapter(getContext(),mUser,false);
                recyclerView.setAdapter(UserAdapter);
            }
        },500);*/
        //UserAdapter = new UserAdapter(getContext(),mUser,false);
        //recyclerView.setAdapter(UserAdapter);
        return view;

    }
    private void InsertUser(String Token)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<ChatList> call = requestApi.getConversation(Token);
        call.enqueue(new Callback<ChatList>() {
            @Override
            public void onResponse(Call<ChatList> call, Response<ChatList> response) {
                try {
                    ChatList a = (ChatList) response.body();
                    int status = a.getStatus();
                    if (status == 200) {
                        List<UserChat> User = (List<UserChat>)a.getConversations();
                        if (!User.isEmpty()){
                            mUser = (List<UserChat>)a.getConversations();
                            UserAdapter = new UserAdapter(getContext(),mUser,false);
                            recyclerView.setAdapter(UserAdapter);
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
            public void onFailure(Call<ChatList> call, Throwable t) {

            }
        });
    }
    /*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        socket.disconnect();
    }
     */

}