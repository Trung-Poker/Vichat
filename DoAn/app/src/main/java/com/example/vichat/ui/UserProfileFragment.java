package com.example.vichat.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vichat.Activity.ChangePasswordActivity;
import com.example.vichat.Activity.DangNhapActivity;
import com.example.vichat.Model.Results;
import com.example.vichat.Model.UserResults;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class UserProfileFragment extends Fragment {

    CircleImageView image_profile;
    EditText username, phone, address;
    TextView email, update_status;
    Button btn_RePassword, btn_logout;
    SharedPreferences sharedpreferences;
    String Token;
    private Uri imageUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
         sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

         image_profile = view.findViewById(R.id.profile_image);
         username = view.findViewById(R.id.name_user);
         email = view.findViewById(R.id.email_user);
         phone = view.findViewById(R.id.phone_user);
         address = view.findViewById(R.id.address_user);
         btn_RePassword = view.findViewById(R.id.button_Repassword);
         btn_logout = view.findViewById(R.id.button_logout);
         update_status = view.findViewById(R.id.update_status);
         Token = sharedpreferences.getString(xToken,"");
         //anh xa du lieu
         getInfoUser(Token);
         address.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btn_RePassword.setText("Lưu");
                 btn_logout.setText("Hủy bỏ");
             }
         });
         username.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btn_RePassword.setText("Lưu");
                 btn_logout.setText("Hủy bỏ");
             }
         });
         phone.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 btn_RePassword.setText("Lưu");
                 btn_logout.setText("Hủy bỏ");
             }
         });
         btn_logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(btn_logout.getText().toString().equals("Hủy bỏ")){
                     btn_RePassword.setText("Đổi mật khẩu");
                     btn_logout.setText("Đăng xuất");
                 }else{
                     logout(Token);
                 }
             }
         });

         btn_RePassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(btn_RePassword.getText().toString() == "Lưu"){
                     String eteusername = username.getText().toString();
                     String etaddress = address.getText().toString();
                     String etphone = phone.getText().toString();
                     UpdateInfoUser(Token,eteusername,etaddress,etphone);
                 }else{
                        Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                        startActivity(intent);
                 }
             }
         });
         image_profile.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
         System.out.println(sharedpreferences.getString(xToken,""));
         return view;

    }


    private void getInfoUser(String emailUser)
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
                final String UrlImage = response.body().getAvartar();
                Picasso.get().load(com.example.vichat.Networking.UrlImage.getUrlImage() + UrlImage).into(image_profile);
            }
            @Override
            public void onFailure(Call<UserResults> call, Throwable t) {

            }
        });


    }

    private void logout(String token)
    {
        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.logout(token);
        try {
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    try {
                        Results a  =  response.body();
                        int status = (int) a.getStatus();
                        if (status == 200) {
                            Intent intent = new Intent(getActivity(), DangNhapActivity.class);
                            startActivity(intent);
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
    private void UpdateInfoUser(String token, String username, String address, String phone){
        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.UpdateInfo(token, username, address, phone);
        try {
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {
                    try {
                        Results a  =  response.body();
                        int status = (int) a.getStatus();
                        if (status == 200) {
                            update_status.setText(a.getMgs().toString());
                        } else {
                            update_status.setText(a.getMgs().toString());
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
    /*
    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);

}*/}

