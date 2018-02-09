package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by usuario on 9/02/18.
 */

public class CallBroadcast extends BroadcastReceiver {

    public static final String TAG = "com.example.broadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"se ha recogido el intent");
    }

}
