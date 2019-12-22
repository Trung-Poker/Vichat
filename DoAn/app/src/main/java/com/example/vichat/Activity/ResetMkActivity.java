package com.example.vichat.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vichat.Model.ResultsLogin;
import com.example.vichat.Networking.APIClient;
import com.example.vichat.Networking.RequestApi;
import com.example.vichat.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ResetMkActivity extends AppCompatActivity implements View.OnClickListener {
    EditText send_email;
    Button btn_resetpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_mk);
        send_email = findViewById(R.id.send_email);
        btn_resetpassword = findViewById(R.id.btnResetPass);
        btn_resetpassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            rePass();
    }


    private void rePass(){

                //Intent intent = new Intent(this,XacNhanResetPassActivity.class);
                //startActivity(intent);

                final String email = send_email.getText().toString().trim();
                if (email.isEmpty()) {
                    Toast.makeText(ResetMkActivity.this, "Bạn cần nhập email khôi phục", Toast.LENGTH_SHORT).show();
                } else {
                    Retrofit retrofit = APIClient.getClient();

                    RequestApi requestApi = retrofit.create(RequestApi.class);

                    Call<ResultsLogin> call = requestApi.forgotpassword(email);

                    call.enqueue(new Callback<ResultsLogin>() {
                        @Override
                        public void onResponse(Call<ResultsLogin> call, Response<ResultsLogin> response) {
                            try {
                                ResultsLogin a = (ResultsLogin) response.body();
                                int status = a.getStatus();
                                System.out.println(status);
                                if (status == 200) {
                                    Succeed(a.getMgs(), email);

                                } else {
                                    Failed(a.getMgs());
                                }
                            }catch (Exception e)
                            {
                                System.out.println(e);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResultsLogin> call, Throwable t) {
                                Failed(null);
                        }

                    });
                }
    }

    public   void Succeed(String message, final String email)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ResetMkActivity.this, XacNhanResetPassActivity.class);
                        intent.putExtra("email", email);
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
