package com.example.reversechronometer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by usuario on 18/01/18.
 */

@SuppressLint("AppCompatCustomView")
public class ReverseChronometer extends TextView implements Runnable {

    private long overallDuration;
    private long warningDuration;
    private long startTime;

    public ReverseChronometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attribute = getContext().obtainStyledAttributes(attrs,R.styleable.ReverseChronometer); // guardando attr
        overallDuration = attribute.getInteger(R.styleable.ReverseChronometer_overallduration,60);
        warningDuration = attribute.getInteger(R.styleable.ReverseChronometer_warningduration, 10);
        reset();
    }

    public void setOverallDuration(long overallDuration){
        this.overallDuration = overallDuration;
    }

    public void setWarningDuration(long warningDuration){
        this.warningDuration = warningDuration;
    }

    @Override
    public void run() {
        long elapsedSeconds = (SystemClock.elapsedRealtime()-startTime)/1000;
        if (elapsedSeconds < overallDuration) {
            // Actualizar los tiempos
            long remainingSecond = overallDuration-elapsedSeconds;
            long minutes = remainingSecond/60;
            long seconds = remainingSecond-(60*minutes);
            setText(String.format("%d:%02d",minutes,seconds));
            // En el caso que nos entrontemos en tiempo de warning
            if (remainingSecond<warningDuration){
                setTextColor(Color.RED);
            }
            postDelayed(this,1000);
        } else {
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }

    public void reset(){
        startTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
    }


    public void stop() {
        removeCallbacks(this);
    }
}
