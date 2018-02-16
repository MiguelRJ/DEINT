package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.widget.Chronometer;

/**
 * Created by usuario on 16/02/18.
 * ofrece un servicio a auna activity
 * tiene que ser finito
 * debe haber comunicacion
 *
 */

public class BoundService extends Service{

    private Chronometer chronometer;

    @Override
    public void onCreate() {
        super.onCreate();
        chronometer = new Chronometer(this);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        chronometer.start();
        return START_NOT_STICKY; // no finito en el tiempo
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chronometer.stop();
    }

    /**
     * El servicio ofrece la hora actual a una activity
     * @return
     */
    public String getTimestamp(){
        long elapsedMillis = SystemClock.elapsedRealtime()-chronometer.getBase();
        int hour =  (int)(elapsedMillis/3600000);
        int minutes = (int)(elapsedMillis-hour*3600000)/60000;
        int seconds = (int)(elapsedMillis-hour*3600000-minutes*60000)/1000;
        int millis = (int)(elapsedMillis-hour*3600000-minutes*60000-seconds*1000);
        return hour+":"+minutes+":"+seconds+":"+millis;
    }

}
