package com.example.vichat.ui.Contact;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.ChatList;
import com.example.vichat.Model.Contact;
import com.example.vichat.Model.ContactAdd;
import com.example.vichat.Model.ResultsContact;
import com.example.vichat.Model.ResultsContactAdd;
import com.example.vichat.Model.UserChat;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.example.vichat.ui.Message.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class ContactFragment extends Fragment {

    private ContactAdapter contactAdapter;
    private ContactAdapterAdd contactAdapterAdd;
    private EditText edit_text;
    private ImageButton image_find;
    private RecyclerView recyclerView;
    private List<ContactAdd> mUserAdd;
    private List<Contact> mUser;
    private String Token,usernamefind;
    SharedPreferences sharedpreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =   inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        edit_text = view.findViewById(R.id.edit_text);
        image_find = view.findViewById(R.id.image_find);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Token = sharedpreferences.getString(xToken,"");
        InsertContact(Token);
        image_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernamefind = edit_text.getText().toString();
                InsertUserFind(Token,usernamefind);
            }
        });
             return view;

    }

    private void InsertContact(final String Token)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);
        System.out.println(Token);
        Call<ResultsContact> call = requestApi.getContact(Token);
        call.enqueue(new Callback<ResultsContact>() {
            @Override
            public void onResponse(Call<ResultsContact> call, Response<ResultsContact> response) {
                try {
                    ResultsContact a = (ResultsContact) response.body();
                    int status = a.getStatus();
                    System.out.println(status);
                    if (status == 200) {
                        List<Contact> User = a.getContacts();
                        if (!User.isEmpty()){
                            mUser = a.getContacts();
                            contactAdapter = new ContactAdapter(getContext(),mUser, Token);
                            recyclerView.setAdapter(contactAdapter);
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
            public void onFailure(Call<ResultsContact> call, Throwable t) {

            }
        });
    }
    private void InsertUserFind(String token, String usernamefind)
    {
        Retrofit retrofit = APIClient.getClient();
        final RequestApi requestApi = retrofit.create(RequestApi.class);
        System.out.println(usernamefind);
        Call<ResultsContactAdd> call = requestApi.findUser(Token, usernamefind);
        call.enqueue(new Callback<ResultsContactAdd>() {


            @Override
            public void onResponse(Call<ResultsContactAdd> call, Response<ResultsContactAdd> response) {
                try {
                    ResultsContactAdd a = (ResultsContactAdd) response.body();
                    int status = a.getStatus();
                    System.out.println(status);
                    if (status == 200) {
                        List<ContactAdd> User = a.getMgs();
                        if (!User.isEmpty()){
                            mUserAdd = a.getMgs();
                            contactAdapterAdd = new ContactAdapterAdd(getContext(),mUserAdd, Token);
                            recyclerView.setAdapter(contactAdapterAdd);
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
                public void onFailure(Call<ResultsContactAdd> call, Throwable t) {

                }
            });
    }
}