package com.example.assynctaskexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    private static final int MAX_LENGHT = 20000;
    private int[] numbers = new int[MAX_LENGHT];
    SimpleAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMessage = findViewById(R.id.txvMessage);
        btnSort = findViewById(R.id.btnSort);
        btnCancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
    }

    public void onClickSort(View view) {
        /* // Opcion 1: Se obtiene el mensaje de error ANR
        bubbleSort(numbers);
        txvMessage.setText("Operacion terminada.");*/
        /* // Opcion 2: Crear un hilo para la ejecucion del metodo bubbleSort y actualizacion del mensaje
        execWithThread();*/
        /* // Opcion 3: SimpleAsyncTask */
        task = new SimpleAsyncTask();
        task.execute();
        /* // Opcion 4: Fragments*/
    }

    public void onClickCancel(View view) {
        task.cancel(true);
    }


    private class SimpleAsyncTask extends AsyncTask<Void,Integer,Void> {
        // doInBackground <Void,
        // publis Integer,
        // postexecuted Void>

        @Override
        protected Void doInBackground(Void... voids) {
            int i, j, aux;
            for(i = 0; i < numbers.length - 1; i++) {
                if (!isCancelled()) { // Si no se cancela la operacion se actualizara la barra de progreso
                    publishProgress((i*100)/MAX_LENGHT);
                } else {
                    break;
                }
                for (j = i + 1; j < numbers.length - i - 1; j++) {
                    if (numbers[i] > numbers[j]) {
                        aux = numbers[j];
                        numbers[j] = numbers[i];
                        numbers[j] = aux;
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnCancel.setVisibility(View.VISIBLE);
            btnSort.setEnabled(false);
            txvMessage.setText("Empezar");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            btnCancel.setVisibility(View.INVISIBLE);
            btnSort.setEnabled(true);
            txvMessage.setText("Operacion terminada.");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txvMessage.setText(String.valueOf(values[0])+"%");
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            btnCancel.setVisibility(View.INVISIBLE);
            btnSort.setEnabled(true);
            txvMessage.setText("Operacion cancelada.");
        }

    }

    /*private void execWithThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txvMessage.setText("Empezar");
                    }
                });
                bubbleSort();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txvMessage.setText("Operacion terminada.");
                    }
                });
            }
        }).start();
    }*/

}
