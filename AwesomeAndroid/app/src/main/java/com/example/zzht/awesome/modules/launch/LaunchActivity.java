package com.example.zzht.awesome.modules.launch;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zzht.awesome.R;
import com.example.zzht.awesome.modules.base.BaseActivity;
import com.example.zzht.awesome.widgets.NavigationBar.NavigationBar;


/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/3/1.
 * PS: Not easy to write code, please indicate.
 */
public class LaunchActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        NavigationBar titleBar = findViewById(R.id.nb_titleBar);
    }
}
