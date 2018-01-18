package com.example.reversechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ReverseChronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setOverallDuration(30);
        chronometer.setWarningDuration(15);
        chronometer.setText("Valor Inicial: 30");
    }

    @Override
    protected void onResume() {
        super.onResume();
        chronometer.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        chronometer.stop();
    }
}
