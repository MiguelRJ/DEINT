package com.example.ficherosfinal.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;

import com.example.ficherosfinal.R;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import java.io.File;
import cz.msebera.android.httpclient.Header;

/**
 * Created by usuario on 15/02/18.
 */

public class ServiceAsyncHttpResponseHandler extends IntentService {

    private Context context = this;
    private int FOREGROUND_ID=1338;
    private static final String CHANNEL = "canal";
    public static int NOTIFY_ID=1337;


    public ServiceAsyncHttpResponseHandler() {
        super("ServiceAsyncHttpResponseHandler");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        NotificationManager nf = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O && nf.getNotificationChannel(CHANNEL) == null ) {
            nf.createNotificationChannel( new NotificationChannel(CHANNEL, "canal", NotificationManager.IMPORTANCE_DEFAULT));
        }

        Bundle bundle = intent.getExtras();
        final String origin = bundle.getString(MyIntentService.INTENT_DATA_SOURCE);



        String destination = bundle.getString(MyIntentService.INTENT_DATA_DESTINATION);
        File downloadFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + destination);

        startForeground(FOREGROUND_ID, buildForegroundNotification(downloadFile.getName()));

        SyncHttpClient cliente = new SyncHttpClient();
        cliente.setMaxRetriesAndTimeout(3,3000);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cliente.get(origin, new FileAsyncHttpResponseHandler(downloadFile) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                Intent newIntent = new Intent(MyIntentService.INTENT_ACTION_FAILURE);
                newIntent.putExtra(MyIntentService.INTENT_DATA_SOURCE,origin);
                raiseNotification(new Exception("Error en la descarga "+statusCode), file.getName());
                LocalBroadcastManager.getInstance(context).sendBroadcast(newIntent);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, File response) {
                raiseNotification(null,file.getName());
                Intent newIntent = new Intent(MyIntentService.INTENT_ACTION_SUCCESS);
                newIntent.putExtra(MyIntentService.INTENT_DATA_SOURCE,origin);
                LocalBroadcastManager.getInstance(context).sendBroadcast(newIntent);
            }

        });

        stopForeground(true);
    }

    private void raiseNotification(Exception e, String name) {

        NotificationCompat.Builder b=
                new NotificationCompat.Builder(this, CHANNEL);

        b.setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis());

        if (e == null) {
            b.setContentTitle("Descarga completa")
                    .setContentText(name)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done);
        }
        else {
            b.setContentTitle("Error")
                    .setContentText(e.getMessage())
                    .setSmallIcon(android.R.drawable.stat_notify_error);
        }

        NotificationManager mgr=
                (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        mgr.notify(NOTIFY_ID, b.build());
    }

    private Notification buildForegroundNotification(String descargando) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(this, CHANNEL);

        b.setOngoing(true)
                .setContentTitle("Descargando")
                .setContentText(descargando)
                .setSmallIcon(android.R.drawable.stat_sys_download)
                .setTicker("descargando");

        return (b.build());
    }

}
