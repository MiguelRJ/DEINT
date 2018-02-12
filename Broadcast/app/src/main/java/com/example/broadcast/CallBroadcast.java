package com.example.broadcast;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by usuario on 9/02/18.
 */

public class CallBroadcast extends BroadcastReceiver {

    public static final String TAG = "broadcast";
    public static final int CALLNOTIFICAION=1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"se ha recogido el intent");

        Bundle bundle = intent.getExtras();

        // 1. Recoger datos del intent
        if (bundle != null){
            String state = bundle.getString(TelephonyManager.EXTRA_STATE); // cambio de estado
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

                // 1.1. Recoger el numero de telefono
                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                // 2. ¿A quien le pasamos la informacion?
                Intent newIntent = new Intent(context,CallInformation.class);
                newIntent.putExtra("number",number);
                newIntent.putExtra("idNotification",CALLNOTIFICAION);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, CALLNOTIFICAION,newIntent,PendingIntent.FLAG_ONE_SHOT|PendingIntent.FLAG_UPDATE_CURRENT);

                // 3. Crear la notificacion
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"Inventory");

                // Personalizar
                builder.setContentTitle("CallBroadcast");
                builder.setSmallIcon(android.R.drawable.sym_call_incoming);
                builder.setLargeIcon(
                        BitmapFactory.decodeResource(
                            context.getResources(),
                            android.R.drawable.sym_call_incoming
                        )
                );
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(CALLNOTIFICAION, builder.build());
            }
        }

    }

}
