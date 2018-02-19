package com.example.boundservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * La actividad requiere un servicio concreto, que ofrece en este caso
 * el service ServiceBound
 */
public class MainActivity extends AppCompatActivity {

    private Button btnTimeStamp, btnStopTimeStamp;
    private TextView txvTimeStamp;
    private BoundService boundService; // Nos ofrece un contador de tiempo
    private boolean isBound = false; // Indica si estamos vinculados al service

    // Necesitamos un objeto service conecction que controla la conexion con el servidor
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            boundService = ((BoundService.MyBinder) binder).getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvTimeStamp = findViewById(R.id.txvTimeStamp);

        btnTimeStamp = findViewById(R.id.btnTimeStamp);
        btnTimeStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    txvTimeStamp.setText(boundService.getTimestamp());
                    btnStopTimeStamp.setEnabled(true);
                    btnTimeStamp.setEnabled(false);
                }
            }
        });

        btnStopTimeStamp = findViewById(R.id.btnStopTimeStamp);
        btnStopTimeStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    stopServiceBound();
                    txvTimeStamp.setText("parado");
                    btnTimeStamp.setEnabled(true);
                    btnStopTimeStamp.setEnabled(false);
                }
            }
        });
    }

    /**
     * Metodo que inicia el servidor
     * Si se inicia mediante startservice el servicio que se inicia en serviceConnection es null
     * Se debe ahcer con bind serice ya que lo crea si es necesario
     */
    private void startServiceBound() {
        Intent intent = new Intent(this,BoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE); // de forma automatica
    }

    /**
     * Metodo que para el servidor
     */
    private void stopServiceBound() {
        unbindService(serviceConnection);
    }

    @Override
    protected void onStart() {
        super.onStart();
        startServiceBound();
    }

    @Override
    protected void onStop() { // ES OBLIGATORIO desvinvular el servisio de la activity
        super.onStop();
        if (isBound) {
            stopServiceBound();
        }
    }

}
