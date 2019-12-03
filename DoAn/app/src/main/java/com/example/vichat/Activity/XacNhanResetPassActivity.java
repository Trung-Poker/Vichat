package com.example.vichat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.vichat.Model.Results;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class XacNhanResetPassActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_password, edit_repassword, edit_code;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_reset_pass);
        edit_password = findViewById(R.id.edit_matkhau);
        edit_repassword = findViewById(R.id.edit_matkhau_xacnhan);
        edit_code = findViewById(R.id.edit_ma_xac_nhan);
        btn_send = findViewById(R.id.btn_rePass);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        rePass();
    }

    private void rePass(){
        String pass = edit_password.getText().toString();
        String repass = edit_repassword.getText().toString();
        String code = edit_code.getText().toString();
        final Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        System.out.println(email);
        System.out.println(pass);
        if (!pass.equals(repass)) {
            Toast.makeText(XacNhanResetPassActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.isEmpty()) {
            edit_password.setError("Bạn chưa nhập mật khẩu");
            edit_password.requestFocus();
            return;
        }
        if (repass.isEmpty()) {
            edit_repassword.setError("Bạn chưa xác nhận mật khẩu");
            edit_repassword.requestFocus();
            return;
        }
        if (code.isEmpty()) {
            edit_code.setError("Bạn chưa nhập mã xác nhận");
            edit_code.requestFocus();
            return;
        }
        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.setpassword(email, code, pass);

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                try {
                    Results a = (Results) response.body();
                    int status = a.getStatus();
                    System.out.println(status);
                    if (status == 200) {
                        Succeed(a.getMgs());

                    } else {
                        Failed(a.getMgs());
                    }
                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Failed(null);
            }

        });


    }
    public   void Succeed(String message)
    {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(XacNhanResetPassActivity.this, DangNhapActivity.class);
                        startActivity(intent);
                    }
                })
                .setCancelable(true)
                .show();
    }
    public   void Failed(String err)
    {
        new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage(err)
                .setPositiveButton("OK", null)
                .setCancelable(true)
                .show();
    }
}
