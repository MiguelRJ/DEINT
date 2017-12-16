package com.example.dynamicfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by usuario on 16/11/17.
 * https://developer.android.com/guide/topics/ui/dialogs.html
 */

public class FragmentB extends Fragment {

    private TextView txvMessage;
    private String message;
    private int size;
    //private View rootView;
    private static String TAG ="fragmentb";

    /**
     * PATRON FACTORY, que es una simplificacion del patron BUILDER
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentB fragmentB = new FragmentB();
        if (bundle != null) {
            fragmentB.setArguments(bundle);
        }
        return fragmentB;
    }

    public void changeTextAndSize(String message, int size) {
        txvMessage.setText(message);
        txvMessage.setTextSize(size);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragmentb, container, false);
        txvMessage = view.findViewById(R.id.txvMessage);
        Log.d(TAG,"Activity onAttach()");
        return view;

    }

    /**
     * Para que el estado dinamico de un fragment sea permanente ante un cambio de configuracion
     * user setRetainInstance();
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (savedInstanceState == null) { // No hay cambio de configuracion se ejecuta por primera vez
            if (bundle != null) { // Si hya parametos se asignan
                message = bundle.getString("message");
                size = bundle.getInt("size");
            }
        }
        changeTextAndSize(message, size);
        Log.d(TAG,"Activity onViewCreated()");
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txvMessage.getText().toString());
        outState.putFloat("size", txvMessage.getTextSize() / getResources().getDisplayMetrics().scaledDensity);
        Log.d(TAG,"Activity onSaveInstanceState()");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
            txvMessage.setText(savedInstanceState.getString("message"));
        }
        Log.d(TAG,"Activity onActivityCreated()");
    }*/

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"Activity onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"Activity onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Activity onDestroy()");
    }

}
