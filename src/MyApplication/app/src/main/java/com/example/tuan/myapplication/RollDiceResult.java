package com.example.tuan.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RollDiceResult extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.roll_dice_result_fragment, container, false);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
