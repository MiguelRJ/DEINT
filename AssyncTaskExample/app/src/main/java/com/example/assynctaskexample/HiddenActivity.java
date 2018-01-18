package com.example.assynctaskexample;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by usuario on 17/01/18.
 */

public class HiddenActivity extends AppCompatActivity implements HiddenFragment.TaskCallbacks {

    private Button btnSort, btnCancel;
    private ProgressBar progressBar;
    private TextView txvMessage;
    private HiddenFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMessage = findViewById(R.id.txvMessage);
        btnSort = findViewById(R.id.btnSort);
        btnCancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        fragment = (HiddenFragment) getFragmentManager().findFragmentByTag(HiddenFragment.TAG);
        if (fragment == null){
            fragment = new HiddenFragment();
        } else {
            if (fragment.progressBarTask.getStatus() == HiddenFragment.ProgressBarTask.Status.RUNNING){
                btnCancel.setVisibility(View.VISIBLE);
                btnSort.setEnabled(false);
            }
        }
    }

    public void onClickSort(View view) {
        fragment = new HiddenFragment();
        getFragmentManager().beginTransaction().add(fragment,HiddenFragment.TAG).commit();
    }

    public void onClickCancel(View view) {
        fragment.OnCancel();
    }

    @Override
    public void onPreExecute() {
        btnCancel.setVisibility(View.VISIBLE);
        btnSort.setEnabled(false);
        txvMessage.setText("Empezar");
    }

    @Override
    public void onProgressUpdate(Integer... values) {
        txvMessage.setText(String.valueOf(values[0]) + "%");
        progressBar.setProgress(values[0]);
    }

    @Override
    public void onPostExecute() {
        btnCancel.setVisibility(View.INVISIBLE);
        btnSort.setEnabled(true);
        txvMessage.setText("Operacion terminada.");
    }

    @Override
    public void onCancelled() {
        btnCancel.setVisibility(View.INVISIBLE);
        btnSort.setEnabled(true);
        txvMessage.setText("Operacion cancelada.");
    }

}