package com.example.zzht.awesome.modules.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/3/19.
 * PS: Not easy to write code, please indicate.
 */
public class MainPageAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;

    public MainPageAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int i) {
        return datas.get(i);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
