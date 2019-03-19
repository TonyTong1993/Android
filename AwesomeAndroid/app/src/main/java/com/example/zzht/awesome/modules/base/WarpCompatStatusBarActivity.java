package com.example.zzht.awesome.modules.base;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.zzht.awesome.R;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OSUtils;


/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/2/28.
 * PS: Not easy to write code, please indicate.
 */
public class WarpCompatStatusBarActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏适配
        ImmersionBar.with(this)
                .fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimary)
                .init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 非必加
        // 如果你的app可以横竖屏切换，适配了华为emui3系列系统手机，并且navigationBarWithEMUI3Enable为true，
        // 请在onResume方法里添加这句代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、华为emui3系列系统手机；3、navigationBarWithEMUI3Enable为true）
        // 否则请忽略
        if (OSUtils.isEMUI3_x()) {
            ImmersionBar.with(this).init();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 非必加
        // 如果你的app可以横竖屏切换，适配了4.4或者华为emui3.1系统手机，并且navigationBarWithKitkatEnable为true，
        // 请务必在onConfigurationChanged方法里添加如下代码（同时满足这三个条件才需要加上代码哦：1、横竖屏可以切换；2、android4.4或者华为emui3.1系统手机；3、navigationBarWithKitkatEnable为true）
        // 否则请忽略
        ImmersionBar.with(this).init();
    }

}
