package com.example.assynctaskexample;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Random;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddenFragment extends Fragment {

    public static final String TAG = "HiddenFragment";

    private TaskCallbacks mTaskCallbacks;
    private static final int MAX_LENGHT = 80000;
    private int[] numbers = new int[MAX_LENGHT];
    ProgressBarTask progressBarTask;

    static interface TaskCallbacks {
        void onPreExecute();
        void onProgressUpdate(Integer... values);
        void onPostExecute();
        void onCancelled();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        generateNumbers();
        progressBarTask = new ProgressBarTask();
        progressBarTask.execute();
    }

    private void generateNumbers(){
        Random rnd = new Random();
        for (int i = 0; i < MAX_LENGHT;i++){
            numbers[i] = rnd.nextInt();
        }
    }

    public void OnCancel(){
        progressBarTask.cancel(true);
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            mTaskCallbacks = (TaskCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +" must implements TaskCallbacks");
        }
        super.onAttach(activity);
    }

    public class ProgressBarTask extends AsyncTask<Void,Integer,Void>{

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
            if (mTaskCallbacks!=null){
                mTaskCallbacks.onPreExecute();
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (mTaskCallbacks!=null){
                mTaskCallbacks.onPostExecute();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (mTaskCallbacks!=null){
                mTaskCallbacks.onProgressUpdate(values[0]);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            if (mTaskCallbacks!=null){
                mTaskCallbacks.onCancelled();
            }
        }


    }
}
