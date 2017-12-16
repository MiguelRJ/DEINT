package com.example.staticfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentB extends Fragment {

    private TextView txvMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragmentb, container, false);
        txvMessage = view.findViewById(R.id.txvMessage);
        return view;
    }

    public void changeTextAndSize(String message, int size){
        txvMessage.setText(message);
        txvMessage.setTextSize(size);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message",txvMessage.getText().toString());
        outState.putFloat("size",txvMessage.getTextSize()/getResources().getDisplayMetrics().scaledDensity);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
            txvMessage.setText(savedInstanceState.getString("message"));
        }
    }

}
