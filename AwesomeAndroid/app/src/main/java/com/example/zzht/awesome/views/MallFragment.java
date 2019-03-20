package com.example.zzht.awesome.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzht.awesome.R;
import com.example.zzht.awesome.widgets.NavigationBar.NavigationBar;

/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/3/19.
 * PS: Not easy to write code, please indicate.
 */
public class MallFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mall,null);
        NavigationBar titleBar = view.findViewById(R.id.nb_titleBar);
        titleBar.setTitle(getResources().getString(R.string.tab_mall));
        return view;
    }
}
