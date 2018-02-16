package com.example.ficherosfinal.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by usuario on 15/02/18.
 * cuando el origen es una notificaicon el borad quien
 * recibe el intent
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case MyIntentService.INTENT_ACTION_FAILURE:
                Log.e(TAG,"Descarga fallida");
                break;
            case MyIntentService.INTENT_ACTION_SUCCESS:
                Log.e(TAG,"Descarga con exito");
                break;
        }
    }
}
