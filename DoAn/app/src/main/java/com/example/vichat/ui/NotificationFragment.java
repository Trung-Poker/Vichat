package com.example.vichat.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vichat.Model.Notifications;
import com.example.vichat.Model.ResultsNotification;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class NotificationFragment extends Fragment {
    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private List<Notifications> mNotification;
    SharedPreferences sharedpreferences;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        InsertNotification(sharedpreferences.getString(xToken,""));

        // add du lieu()
        return view;

    }
    private void InsertNotification(String Token)
    {
        Retrofit retrofit = APIClient.getClient();

        final RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<ResultsNotification> call = requestApi.getNotification(Token);
        call.enqueue(new Callback<ResultsNotification>() {
            @Override
            public void onResponse(Call<ResultsNotification> call, Response<ResultsNotification> response) {
                try {
                    ResultsNotification a =response.body();
                    int status = a.getStatus();
                    if (status == 200) {
                        List<Notifications> Nor = a.getNotification();
                        if (!Nor.isEmpty()){
                            mNotification = (List<Notifications>)a.getNotification();
                            notificationAdapter = new NotificationAdapter(getContext(),mNotification,false);
                            recyclerView.setAdapter(notificationAdapter);
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
            public void onFailure(Call<ResultsNotification> call, Throwable t) {

            }
        });
    }
}
