package com.example.zzht.awesome.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zzht.awesome.R;

/**
 * Author by zzht, Email tongwanhua1993@163.com, Date on 2019/3/19.
 * PS: Not easy to write code, please indicate.
 */
public class MessageFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message,null);
        return view;
    }
}
