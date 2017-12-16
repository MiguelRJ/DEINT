package com.example.staticfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Created by usuario on 16/11/17.
 */

public class FragmentA extends Fragment {

    private FragmentAListener mCallBack;
    private EditText edtMessage;
    private Button btnSize;
    private SeekBar skbSize;

    /* Se define la interfaz que servira de contrato entre en Fragment y la activity */

    public interface FragmentAListener {
        void onFragmentAEvent(String message, int size);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallBack = (FragmentAListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentAListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragmenta, container, false);
        edtMessage = view.findViewById(R.id.edtMessage);
        btnSize = view.findViewById(R.id.btnSize);
        skbSize = view.findViewById(R.id.skbSize);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onFragmentAEvent(edtMessage.getText().toString(), skbSize.getProgress());
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

}

/**
 * Este metodo solo funciona desde la API 23 en adelante
 * Si se ejecuta en una API inferior NO da error pero no funciona la comunicacion Activity-Fragment
 * @param //context
 */
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    */