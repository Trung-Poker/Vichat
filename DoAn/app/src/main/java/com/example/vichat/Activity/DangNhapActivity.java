package com.example.vichat.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.vichat.Networking.APIClient;
import com.example.vichat.Model.Results;
import com.example.vichat.R;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.menuActivity;
import com.example.vichat.ui.UserProfileFragment;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DangNhapActivity extends AppCompatActivity implements View.OnClickListener {

    TextView editEmailDN, editPasswordDN;
    Button btnDangKy, btnDangNhap;
    CheckBox chkMK;
    JSONObject json;
    ProgressBar pbLoading;
    TextView forgot_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        editEmailDN = findViewById(R.id.editEmailDN);
        editPasswordDN = findViewById(R.id.editPasswordDN);
        chkMK = findViewById(R.id.chkMK);
        pbLoading = findViewById(R.id.pbLoading);
        btnDangKy = findViewById(R.id.btnDangKy);
        forgot_pass = findViewById(R.id.forget_pass);
        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, ResetMkActivity.class);
                startActivity(intent);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
                startActivity(intent);
            }
        });
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnDangNhap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        signIn();
    }

    private void signIn() {
        //btnDangNhap.setVisibility(View.GONE);
        //pbLoading.setVisibility(View.VISIBLE);
        final String email = editEmailDN.getText().toString();
        String pws = editPasswordDN.getText().toString();

        if (email.isEmpty()) {
            editEmailDN.setError("Bạn chưa nhập Họ tên");
            editEmailDN.requestFocus();
            return;
        }
        if (pws.isEmpty()) {
            editPasswordDN.setError("Bạn chưa nhập Mật khẩu");
            editPasswordDN.requestFocus();
            return;
        }

        //Intent intent = new Intent(DangNhapActivity.this, menuActivity.class);
        //startActivity(intent);
        //tu dang nhap

        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.signIn(email, pws);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                try {
                    Results a = (Results) response.body();
                    int status = a.getStatus();
                    if (status == 200) {
                        signInSucceed(email);
                        System.out.println(email);
                    } else {
                        signInFailed(a.getMgs());
                    }
                }catch (Exception e)
                {
                    System.out.println(e);
                }


                btnDangNhap.setVisibility(View.VISIBLE);
                pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

                signInFailed(null  );
            }
        });
    }
    public void signInSucceed(String email) {
        new AlertDialog.Builder(this).setTitle("Đăng nhập thành công").show();
        Intent intent = new Intent(DangNhapActivity.this, menuActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }
    public void signInFailed(String b) {
        new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage(b)
                .setPositiveButton("OK", null)
                .setCancelable(true)
                .show();
    }
}

