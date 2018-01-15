package com.example.assynctaskexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btnSort,btnCancel;
    private ProgressBar progressBar;
    private TextView txvMessage;
    private static final int MAX_LENGHT = 200000;
    private int[] numbers = new int[MAX_LENGHT];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMessage = findViewById(R.id.txvMessage);
        generateNumbers();
    }

    private void generateNumbers() {
        Random rnd = new Random();
        for (int i = 0; i<MAX_LENGHT;i++){
            numbers[i] = rnd.nextInt();
        }
    }

    public void onClickSort(View view) {
        /* // Opcion 1: Se obtiene el mensaje de error ANR
        bubbleSort(numbers);
        txvMessage.setText("Operacion terminada.");*/
        /* // Opcion 2: Crear un hilo para la ejecucion del metodo bubbleSort y actualizacion del mensaje
         */
        execWithThread();
    }

    private void execWithThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txvMessage.setText("Empezar");
                    }
                });
                bubbleSort(numbers);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txvMessage.setText("Operacion terminada.");
                    }
                });
            }
        }).start();
    }

    private void bubbleSort(int[] A) {
        int i, j, aux;

        for(i = 0; i < A.length - 1; i++) {

            for (j = i + 1; j < A.length - i - 1; j++) {

                if (A[i] > A[j]) {

                    aux = A[j];
                    A[j] = A[i];
                    A[j] = aux;

                }

            }

        }

    }

    private class SimpleAsyncTask extends AsyncTask<Void,Integer,Void>{
        // doInBackground
        // publis
        // postexecuted

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
