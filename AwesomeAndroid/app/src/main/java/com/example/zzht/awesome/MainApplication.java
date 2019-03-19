package com.example.zzht.awesome;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.gyf.barlibrary.ImmersionBar;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/2/28.
 * PS: Not easy to write code, please indicate.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //使用Bugly进行运用统计
        try {
            ApplicationInfo appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            String BUGLY_APPID = appInfo.metaData.getString("BUGLY_APPID");
            CrashReport.initCrashReport(getApplicationContext(),BUGLY_APPID,BuildConfig.IS_DEBUG_ON);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }
}
