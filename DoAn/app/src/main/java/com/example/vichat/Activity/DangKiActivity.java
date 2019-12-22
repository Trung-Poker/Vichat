package com.example.vichat.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vichat.Model.ResultsLogin;
import com.example.vichat.Model.User;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DangKiActivity extends AppCompatActivity implements View.OnClickListener, Callback<ResultsLogin> ,DialogInterface.OnClickListener{

    private EditText editName, editEmail, editPassword, editRePassword;
    private ProgressBar pbLoading;
    Button btnDK;
    RadioButton gender;
    private String userId;
    private TextView textSignUpStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        gender = findViewById(R.id.radio_male);
        //editName = (EditText) findViewById(R.id.editName);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editRePassword = (EditText) findViewById(R.id.editRePassword);
        textSignUpStatus = findViewById(R.id.text_sign_up_status);
        btnDK = (Button) findViewById(R.id.btnDK);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        btnDK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDK: {
                sendSignUp();
            }
        }
        //Dang ki cho server phan hoi ID
    }

    @Override
    public void onClick(DialogInterface dialog, int which){
        Intent intent = new Intent(this, DangNhapActivity.class);
        startActivity(intent);
        finish();
        //OnClick Dialog.
    }


    private void sendSignUp() {
        User.UserData userData = new User.UserData();

        final String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String repassword = editRePassword.getText().toString();
        if (!password.equals(repassword)) {
            Toast.makeText(DangKiActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty()) {
            editEmail.setError("Bạn chưa nhập Email");
            editEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPassword.setError("Bạn chưa nhập mật khẩu");
            editPassword.requestFocus();
            return;
        }
        if (repassword.isEmpty()) {
            editRePassword.setError("Bạn chưa xác nhận mật khẩu");
            editRePassword.requestFocus();
            return;
        }

        userData.setEmail(editEmail.getText().toString());
        userData.setPassword(editPassword.getText().toString());
        userData.setGender(gender.isChecked() ? "male" : "female");
        userData.setPassword_confirmation(editRePassword.getText().toString());
        Retrofit retrofit = APIClient.getClient();

        RequestApi callApi = retrofit.create(RequestApi.class);

        Call<ResultsLogin> call = callApi.signUp(userData);

        call.enqueue(this);
        //khai bao Retrofit gui du lieu cua user data
    }

    @Override
    public void onResponse(Call<ResultsLogin> call, Response<ResultsLogin> response) {
        ResultsLogin result = (ResultsLogin) response.body();
        int status = result.getStatus();
        if(result.getMgs() != null)
        System.out.println(status);
        if (status == 200) {
           // userId = result.getId();
            //saveCookie();
            signUpSucceed();
        } else {
            signUpFailed(result.getMgs());
        }
    }

    @Override
    public void onFailure(Call<ResultsLogin> call, Throwable t) {
        signUpFailed(null);
    }

    private void signUpSucceed() {
        new AlertDialog.Builder(this)
                .setTitle("Notice")
                .setMessage("Đăng nhập thành công.\nVui lòng kiểm tra hộp thư email của bạn")
                .setCancelable(false)
                .setPositiveButton("NEXT",  this)
                .show();
    }


    public void saveCookie() {
        SharedPreferences preferences = getSharedPreferences("PREF", Context.MODE_PRIVATE);
        preferences.edit().putString("USER_ID", userId).apply();
    }

    private void signUpFailed(String mgs) {
        textSignUpStatus.setText(mgs);

        if (mgs == null) {
            textSignUpStatus.setText("Sign up failed.....");
        }

        textSignUpStatus.setVisibility(View.VISIBLE);
    }





}