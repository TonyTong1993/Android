package com.example.zzht.awesome.modules;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.zzht.awesome.R;
import com.example.zzht.awesome.views.MainFragment;
import com.example.zzht.awesome.views.MallFragment;
import com.example.zzht.awesome.views.MessageFragment;
import com.example.zzht.awesome.views.ProfileFragment;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationBar mBottomNavigationBar;
    private LocationManager locationManager;
    private String provider;
    private MainFragment mainFragment;
    private MallFragment mallFragment;
    private MessageFragment messageFragment;
    private ProfileFragment profileFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Make sure this line comes before calling super.onCreate().
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
//        启动时间的优化:它将在log里报告从apk初始化（和前面Displayed的时间是一样的）到reportFullyDrawn() 方法被调用用了多长时间
        try {
            reportFullyDrawn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    private void initView() {
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        mainFragment = new MainFragment();
        transaction.replace(R.id.layFrame,mainFragment);
        transaction.commit();
        mBottomNavigationBar = findViewById(R.id.bnb_tabBar);
        BottomNavigationItem item = new BottomNavigationItem(R.drawable.main_tab_main, R.string.tab_main);
        mBottomNavigationBar.addItem(item);
        item = new BottomNavigationItem(R.drawable.main_tab_messege, R.string.tab_message);
        mBottomNavigationBar.addItem(item);
        item = new BottomNavigationItem(R.drawable.main_tab_manage, R.string.tab_mall);
        mBottomNavigationBar.addItem(item);
        item = new BottomNavigationItem(R.drawable.main_tab_my, R.string.tab_profile);
        mBottomNavigationBar.addItem(item);
        mBottomNavigationBar.initialise();
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                 fragmentManager = getSupportFragmentManager();
                 transaction = fragmentManager.beginTransaction();

              switch (position){
                  case 0:
                      if (null == mainFragment) mainFragment = new MainFragment();
                      transaction.replace(R.id.layFrame, mainFragment);
                      break;
                  case 1:
                      if (null == mallFragment) mallFragment = new MallFragment();
                      transaction.replace(R.id.layFrame, mallFragment);
                      break;
                  case 2:
                      if (null == messageFragment) messageFragment = new MessageFragment();
                      transaction.replace(R.id.layFrame, messageFragment);
                      break;
                  case 3:
                      if (null == profileFragment) profileFragment = new ProfileFragment();
                      transaction.replace(R.id.layFrame, profileFragment);
                      break;
                      default:
                          break;
              }
              transaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }


    private void showLocation(Location location) {

    }


    private void requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, getResources().getInteger(R.integer.success));
        } else {
            Toast.makeText(MainActivity.this, "已开启定位权限", Toast.LENGTH_LONG).show();
            startLocation();
        }

    }

    private void startLocation() {
        //获取定位管理器
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        //获取定位数据的供应商
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            Toast.makeText(this, "No location provider can be used",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //检测用户权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //获取当前设备的位置信息
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);
        }
        //请求持续更新位置
        locationManager.requestLocationUpdates(provider, 5000, 1.0f, locationListener);

    }

    //位置更新的监听器
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            double accuracy = location.getAccuracy();
            System.err.print("lat" + latitude + "lon" + longitude + "acc" + accuracy);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            System.out.print("onStatusChanged" + s);
        }

        @Override
        public void onProviderEnabled(String s) {
            System.out.print("onProviderEnabled" + s);
        }

        @Override
        public void onProviderDisabled(String s) {
            System.out.print("onProviderDisabled" + s);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == getResources().getInteger(R.integer.success)) {
            int result = grantResults[0];
            if (result == PackageManager.PERMISSION_GRANTED) {
                startLocation();
            } else {
                showGuideToResetPermission();
            }
        } else if (requestCode == getResources().getInteger(R.integer.failure)) {

        }

    }

    private void showGuideToResetPermission() {
        Toast.makeText(MainActivity.this, "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
    }
}
