package com.example.vichat.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vichat.Model.Results;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;
import com.example.vichat.menuActivity;
import com.example.vichat.ui.UserProfileFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.vichat.Activity.DangNhapActivity.MyPREFERENCES;
import static com.example.vichat.Activity.DangNhapActivity.xToken;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    private EditText edit_password_change, edit_passwordconfrim_change, edit_password;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        edit_password = findViewById(R.id.edit_mat_khau_cu);
        edit_password_change = findViewById(R.id.edit_matkhau_moi);
        edit_passwordconfrim_change = findViewById(R.id.edit_mk_xac_nhan);
        btn_send = findViewById(R.id.btn_rePass);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        rePass();
    }

    private void rePass(){
        String pass_old = edit_password.getText().toString();
        String pass = edit_password_change.getText().toString();
        String passcofirm= edit_passwordconfrim_change.getText().toString();
        final Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        System.out.println(email);
        System.out.println(pass);
        if (!pass.equals(passcofirm)) {
            Toast.makeText(ChangePasswordActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.isEmpty()) {
            edit_password_change.setError("Bạn chưa nhập mật khẩu mới");
            edit_password_change.requestFocus();
            return;
        }
        if (passcofirm.isEmpty()) {
            edit_passwordconfrim_change.setError("Bạn chưa xác nhận mật khẩu mới");
            edit_passwordconfrim_change.requestFocus();
            return;
        }
        if (pass_old.isEmpty()) {
            edit_password.setError("Bạn chưa nhập mật khẩu cũ");
            edit_password.requestFocus();
            return;
        }
        Retrofit retrofit = APIClient.getClient();

        RequestApi requestApi = retrofit.create(RequestApi.class);

        Call<Results> call = requestApi.UpdatePassword(sharedPreferences.getString(xToken,""),pass_old, pass, passcofirm);

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
                        Intent intent = new Intent(ChangePasswordActivity.this, menuActivity.class);
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
