package com.example.ficherosfinal.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.example.ficherosfinal.RestClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * Created by usuario on 15/02/18.
 */

public class ServiceAsyncHttpResponseHandler extends IntentService {

    public ServiceAsyncHttpResponseHandler() {
        super("ServiceAsyncHttpResponseHandler");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle bundle = intent.getExtras();
        String origin = bundle.getString(MyIntentService.INTENT_DATA_SOURCE);
        String destination = bundle.getString(MyIntentService.INTENT_DATA_DESTINATION);
        File downloadFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + destination);
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.get(origin, new FileAsyncHttpResponseHandler(downloadFile) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Intent newIntent = new Intent(MyIntentService.INTENT_ACTION_FAILURE);
                sendBroadcast(newIntent);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                Intent newIntent = new Intent(MyIntentService.INTENT_ACTION_SUCCESS);
                sendBroadcast(newIntent);
            }
        });

    }

}
