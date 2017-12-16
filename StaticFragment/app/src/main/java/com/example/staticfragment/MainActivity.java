package com.example.staticfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

/**
 * http://gpmess.com/blog/2014/04/16/buenas-practicas-usando-fragments-en-android/
 */
public class MainActivity extends Activity implements FragmentA.FragmentAListener{

    private Fragment fragmentb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentb = getFragmentManager().findFragmentById(R.id.fragmentB);
    }

    @Override
    public void onFragmentAEvent(String message, int size) {
        ((FragmentB)fragmentb).changeTextAndSize(message, size);
    }

}
