package com.example.alarmtimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmTimerActivity extends AppCompatActivity {

    private TimePicker tp;
    private FloatingActionButton fab;
    private final int ALARMTIMER=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tp = findViewById(R.id.tp);
        tp.setIs24HourView(true);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.alarmtimer.intent");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmTimerActivity.this,ALARMTIMER, intent,PendingIntent.FLAG_UPDATE_CURRENT);
                Calendar c = Calendar.getInstance();
                //c.setTimeInMillis(System.currentTimeMillis());
                c.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
                c.set(Calendar.MINUTE, tp.getCurrentMinute());
                c.set(Calendar.SECOND,0);

                // Registro

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP,0,pendingIntent);
                finish();
            }
        });
    }
}
