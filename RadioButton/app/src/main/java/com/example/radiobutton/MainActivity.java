package com.example.radiobutton;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Clase de ejemplo de como funcionan los RadioButton y los RadioGroup
 * @author Miguel Rodriguez Jimenez
 * @version 1
 */
public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintParticular, constraintBusiness;
    private RadioGroup rgTypeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintParticular = findViewById(R.id.incParticular);// no se necesita casting porque View es el general en la jerarquia
        constraintBusiness = findViewById(R.id.incBussiness);
        rgTypeClient = findViewById(R.id.rgTypeClient);
        rgTypeClient.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rbtBussiness:
                        viewParticular(false);
                        break;
                    case R.id.rbtParticular:
                        viewParticular(true);
                        break;
                }
            }
        });
    }

    /**
     * Metodo que indica que constraint ha de estar visible segun la opcion seleccionada
     * @param b
     */
    private void viewParticular(boolean b) {
        constraintParticular.setVisibility(b?View.VISIBLE: View.GONE);
        constraintBusiness.setVisibility(b?View.GONE: View.VISIBLE);
    }
}
