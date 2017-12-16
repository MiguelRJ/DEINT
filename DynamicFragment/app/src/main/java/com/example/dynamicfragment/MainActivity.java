package com.example.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements FragmentA.FragmentAListener{

    private Fragment fragmentA;
    private Fragment fragmentB;
    private static String TAG ="Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentA = fragmentManager.findFragmentByTag("fragmentA");
        if (fragmentA == null) {
            fragmentA = new FragmentA();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(android.R.id.content,fragmentA,"fragmentA");
            fragmentTransaction.commit();
        }
        Log.d(TAG,"Activity onCreate()");

    }

    @Override
    public void onFragmentAEvent(String message, int size) {
        fragmentB = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("size",size);
        //Con el metodo setSarguments se pasa la iformacion que necesita el fragment

        // Se debe utilizar el patron factoria donde la cracion del objeto y el paso
        // de argumentos se ejecueten consecutivamente
        fragmentB = FragmentB.newInstance(bundle);
        // A continuacion se empieza la transaccion del FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,fragmentB);
        // Antes de realizar el commit se debe guardar la transacccion
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        /*
        //Con el metodo setSarguments se pasa la iformacion que necesita el fragment
        fragmentB.setArguments(bundle);
        // A continuacion se empieza la transaccion del FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content,fragmentB);
        // Antes de realizar el commit se debe guardar la transacccion
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
         */
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"Activity onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"Activity onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Activity onDestroy()");
    }
}
