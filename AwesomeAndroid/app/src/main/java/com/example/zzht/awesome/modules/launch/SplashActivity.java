package com.example.zzht.awesome.modules.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.zzht.awesome.modules.MainActivity;
import com.example.zzht.awesome.modules.base.BaseActivity;


/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/3/1.
 * PS: Not easy to write code, please indicate.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMainActivity();
    }


    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag","SplashActivity is onDestroy ");
    }
}
