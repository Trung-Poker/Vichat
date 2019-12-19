package com.example.vichat.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.vichat.Model.Results;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.example.vichat.menuActivity;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DangNhapActivity extends Activity implements View.OnClickListener {

    TextView editEmailDN, editPasswordDN;
    Button btnDangKy, btnDangNhap;
    CheckBox chkMK;
    JSONObject json;
    ProgressBar pbLoading;
    TextView forgot_pass;
    public static final String MyPREFERENCES = "MyVichat";
    public static final String xToken = "TokenId";
    SharedPreferences sharedpreferences; //tao cac doi tuong SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dang_nhap);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        initWidgets();//anh xa du lieu
        Toast.makeText(getApplicationContext(),sharedpreferences.getString(xToken,""),Toast.LENGTH_SHORT);
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
        //btnDangNhap.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
    }

    private void initWidgets() {
        editEmailDN = findViewById(R.id.editEmailDN);
        editPasswordDN = findViewById(R.id.editPasswordDN);
        chkMK = findViewById(R.id.chkMK);
        pbLoading = findViewById(R.id.pbLoading);
        btnDangKy = findViewById(R.id.btnDangKy);
        forgot_pass = findViewById(R.id.forget_pass);
        btnDangNhap = findViewById(R.id.btnDangNhap);
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

        final Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.signIn(email, pws);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                try {
                    Results a = (Results) response.body();
                    int status = a.getStatus();
                    if (status == 200) {
                        clearData();
                        saveData(a.getMgs());
                        signInSucceed();
                    } else {
                        signInFailed(a.getMgs());
                    }
                }catch (Exception e)
                {
                    System.out.println("con me may");
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
    public void signInSucceed() {
        new AlertDialog.Builder(this).setTitle("Đăng nhập thành công").show();
        Intent intent = new Intent(DangNhapActivity.this, menuActivity.class);
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

    private void clearData() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void saveData(String token) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(xToken, token );
        editor.commit();
    }
}

