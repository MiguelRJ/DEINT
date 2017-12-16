package com.example.sendmessage.sendmessage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.sendmessage.sendmessage.pojo.Message;

/**
 * Esta clase recibe un mensaje de otra actividad
 *
 * @author Miguel Rodriguez Jimenez
 *         <p>Conceptos aprendidos:</p>
 *         <ul>
 *         <li>Concepto context</li>
 *         <li>Paso de parametros mediante el objeto {@link #getIntent()}</li>
 *         <li>Paso de mensajes entre dos actividades mediante la clase @see android.content.Intent</li>
 *         <li>Crear documentacion en /doc</li>
 *         <li>Crear log para el android monitor</li>
 *         <li>Reformat el codigo</li>
 *         </ul>
 */
public class ViewMessageActivity extends AppCompatActivity {

    private TextView txvViewMessage;
    private TextView txvViewUser;
    // tag para los log.d()
    private static final String TAG = "com.example.sendmessage";
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        txvViewMessage = (TextView) findViewById(R.id.txvViewMessage);
        txvViewUser = (TextView) findViewById(R.id.txvViewUser);

        //1. Recoger intent enviado desde la actividad anterior
        //Intent intent=getIntent();

        //2. Recoger mensaje del bundle, a un nuevo bundle le asignamos lo que traia el creado en la otra actividad
        //Bundle bundle=intent.getExtras();

        //3. Mostrar mensaje, ya podemos desglosar lo que traia nuestro bundle y asignarlo a donde queremos
        //txvMessage.setText(bundle.getString("message"));

        //   Factorizando
        //txvViewMessage.setText(getIntent().getExtras().getString("message"));// los 3 pasos anteriores en 1 linea
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        msg=(Message) bundle.getSerializable("message");
        txvViewMessage.setText(msg.getMessage());

        /**
         * Creamos un string que vamos a formatear
         * Al string le hacemos un format del recurso que hay en strings.xml
         *      para añadirle en su primer parametro un contenido del bundle
         *
         * String.format(recurso en strings.xml,objeto del bundle)
         *      (en este caso el objeto añadido es del bundle pero podria venir de otro lado)
         */
        //String viewUser = String.format(getResources().getString(R.string.txvViewUser), getIntent().getExtras().getString("user"));
        String viewUser = String.format(getResources().getString(R.string.txvViewUser), ((Message)(getIntent().getExtras().getSerializable("message"))).getUser());
        //String viewUser = String.format(getResources().getString(R.string.txvViewUser), msg.getUser());
        txvViewUser.setText(viewUser);
        Log.d(TAG, "ViewMessage: OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "ViewMessage: OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "ViewMessage: OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "ViewMessage: OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "ViewMessage: OnStop");
    }
}
