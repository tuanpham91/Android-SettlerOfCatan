package com.example.tuan.myapplication.model;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Tuan on 16.07.2017.
 */

public class CommunicatorService extends IntentService{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public CommunicatorService(String name) {
        super("CommunicatorService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        sendBroadcast();
    }


}
