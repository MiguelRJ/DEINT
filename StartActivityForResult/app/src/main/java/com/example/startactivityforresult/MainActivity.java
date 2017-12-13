package com.example.startactivityforresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txvMessageName, txvMessageLastName;
    private Button btnName, btnLastName;
    private final static int NAME = 0;
    private final static int LASTNAME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvMessageName = findViewById(R.id.txvMessageName);
        txvMessageLastName = findViewById(R.id.txvMessageLastName);
        btnName = findViewById(R.id.btnName);
        btnName.setOnClickListener(this);
        btnLastName = findViewById(R.id.btnLastName);
        btnLastName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnName){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(intent,MainActivity.NAME);
        }
        if (view == btnLastName){
            Intent intent = new Intent(MainActivity.this,SecondActivity.class);
            startActivityForResult(intent,MainActivity.LASTNAME);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //1. se comprueba el codigo de la operacion
        switch (requestCode){
            case NAME:
                if (resultCode == RESULT_OK){
                    String message = data.getExtras().getString("message");
                    txvMessageName.setText(message);
                }
                break;
            case LASTNAME:
                if (resultCode == RESULT_OK){
                    String message = data.getExtras().getString("message");
                    txvMessageLastName.setText(message);
                }
                break;
        }

        //2. se comprueba el resultado de peticion
    }
}
