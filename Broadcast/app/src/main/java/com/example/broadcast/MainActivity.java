package com.example.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.broadcast.intent");
        Bundle bundle = new Bundle();
        bundle.putString(TelephonyManager.EXTRA_STATE,"RINGING");
        bundle.putString(TelephonyManager.EXTRA_INCOMING_NUMBER,"6505551213");
        intent.putExtras(bundle);
        sendBroadcast(intent);
    }
}
