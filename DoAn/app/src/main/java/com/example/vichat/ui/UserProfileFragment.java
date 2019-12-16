package com.example.vichat.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vichat.Activity.DangNhapActivity;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.User;
import com.example.vichat.Model.UserResults;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Url;

import static android.content.Intent.*;

public class UserProfileFragment extends Fragment {

    CircleImageView image_profile;
    EditText username, email, phone, address, update_status;
    Button btn_RePassword, btn_Save;
    private Uri imageUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.user_profile_fragment, container, false);
         image_profile = view.findViewById(R.id.profile_image);
         username = view.findViewById(R.id.name_user);
         email = view.findViewById(R.id.email_user);
         phone = view.findViewById(R.id.phone_user);
         address = view.findViewById(R.id.address_user);
         //update_status = view.findViewById(R.id.update_status);
         btn_RePassword = view.findViewById(R.id.button_Repassword);
         btn_Save = view.findViewById(R.id.button_save);
         btn_Save.setVisibility(View.INVISIBLE);
         //String emailUser = this.getArguments().getString("email");
         //System.out.println(emailUser);
         //getInfoUser(emailUser);
         return view;

    }
    /*
    private void getInfoUser(@NonNull String emailUser)
    {
        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<UserResults> call = requestApi.getInfoUser(emailUser);
        call.enqueue(new Callback<UserResults>() {
            @Override
            public void onResponse(Call<UserResults> call, Response<UserResults> response) {
                System.out.println(response.body().getUsername());
                email.setText(response.body().getEmail());
                username.setText(response.body().getUsername());
                address.setText(response.body().getAddress());
                phone.setText(response.body().getPhone());

            }
            @Override
            public void onFailure(Call<UserResults> call, Throwable t) {

            }
    });
    }
    */
}
