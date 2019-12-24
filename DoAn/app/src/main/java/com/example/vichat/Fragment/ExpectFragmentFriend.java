package com.example.vichat.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vichat.Model.Contact;
import com.example.vichat.Model.Notifications;
import com.example.vichat.Model.ResultsContact;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.example.vichat.ui.NotificationAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class ExpectFragmentFriend extends Fragment {
    private ExpectFragmentAdapter expectFragmentAdapter;
    private RecyclerView recyclerView;
    private List<Contact> mUser;
    private String Token;
    SharedPreferences sharedpreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =   inflater.inflate(R.layout.fragment_expect_friend, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Token = sharedpreferences.getString(xToken,"");
        InsertContact(Token);
        return view;

    }

    private void InsertContact(final String Token)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);
        System.out.println(Token);
        Call<ResultsContact> call = requestApi.getContactReceived(Token);
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
                            expectFragmentAdapter = new ExpectFragmentAdapter(getContext(),mUser, Token);
                            recyclerView.setAdapter(expectFragmentAdapter);
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
}
