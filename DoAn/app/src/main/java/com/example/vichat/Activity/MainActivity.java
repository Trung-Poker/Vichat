package com.example.vichat.Activity;


import android.content.Intent;
import android.os.Handler;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vichat.Model.UrlImage;
import com.example.vichat.R;

public class MainActivity extends Activity {

    public static int DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent itDangNhap = new Intent(MainActivity.this,DangNhapActivity.class);
                startActivity(itDangNhap);
                finish();
            }
        },DELAY_TIME);
    }
}

