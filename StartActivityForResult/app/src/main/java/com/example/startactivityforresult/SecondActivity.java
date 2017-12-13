package com.example.startactivityforresult;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtMessage;
    private Button btnAceptar,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        edtMessage = findViewById(R.id.edtMessage);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(this);
        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnAceptar){
            Intent intent = new Intent();
            //1. comrpobar que no este vacio el intent
            if(edtMessage.getText().toString() != null) {
                //2. se envia al intent
                intent.putExtra("message", edtMessage.getText().toString());
            }
            //3. indicar resultado correcto
            setResult(RESULT_OK,intent);
            finish();
        }
        if(view == btnCancel){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

    }
}
