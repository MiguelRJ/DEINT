package com.example.greeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText greetEditText;
    private TextView messageTextView;
    private Button greetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greetEditText = findViewById(R.id.greetEditText);
        messageTextView =  findViewById(R.id.messageTextView);
        greetButton = findViewById(R.id.greetButton);
        greetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageTextView.setText(String.format("Hello, %s!", greetEditText.getText().toString()));
            }
        });
    }

}
