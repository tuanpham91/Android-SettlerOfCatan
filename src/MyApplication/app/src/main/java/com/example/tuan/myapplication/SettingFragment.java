package com.example.tuan.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Tuan on 09.06.2017.
 */

public class SettingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_fragment_scrollview,container,false);

        return view;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}