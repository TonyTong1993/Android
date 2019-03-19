package com.example.zzht.awesome.views;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzht.awesome.R;

/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/2/28.
 * PS: Not easy to write code, please indicate.
 */
public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base,null);
        return view;
    }


}
