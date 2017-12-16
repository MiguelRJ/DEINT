package com.example.sendmessage.sendmessagebutterknife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sendmessage.sendmessagebutterknife.pojo.Message;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Esta clase envia un mensaje de un usuario a otro
 *
 * @author Miguel Rodriguez Jimenez
 *         <p>Conceptos aprendidos:</p>
 *         <ul>
 *         <li>Concepto context</li>
 *         <li>Paso de parametros mediante el objeto @see android.os.Bundle</li>
 *         <li>Paso de mensajes entre dos actividades mediante la clase @see android.content.Intent</li>
 *         <li>Crear documentacion en /doc</li>
 *         <li>Crear log para el android monitor</li>
 *         <li>Reformat el codigo</li>
 *         <li>Code > Comment with block comment (rodear seleccion con comentario de bloque)</li>
 *         <li>Serializable (ineficiente, manda todito) Parcelable(manda por partes) , a dia de hoy </li>
 *         <li> butterknife libreria para asociar con las vistar (findbyid)</li>
 *         </ul>
 */
public class SendMessageActivity extends AppCompatActivity {

    /**
     * En esta zona se inicializan los elementos que pretendemos usar en la activity
     * Si salen en gris significa que no se estan usando, o que hemos olvidado darle memoria
     * *******NullPointerException********
     *
     * @BindView ayuda de butterkenife para bindear
     */
    @BindView(R.id.edtMessage)
    EditText edtMessage;
    @BindView(R.id.edtUser)
    EditText edtUser;
    @BindView(R.id.btnOK)
    Button btnOK;
    private static final String TAG = "com.example.sendmessage";
    private Message msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        /**
         * Aqui damos memoria a los elementos inicializados anteriormente
         * Siempre hacer un cast al tipo que queremos porque findViewById devuelve un entero
         * findViewById(R.id.edtMessage); esto encuentra el entero
         *      que hace referencia al objeto en el fichero xml de la actividad en la que estemos
         * Recordar:
         *      setContentView(R.layout.activity_send_message);
         *      Carga el layout de la actividad, si hacemos findViewById() de un elemento
         *          que no esta en el xml de la actividad dara error en la ejecucion.
         */

            /* con butterknife no hace falta ir una por una
            edtMessage = (EditText) findViewById(R.id.edtMessage);
            edtUser = (EditText) findViewById(R.id.edtUser);
            btnOK = (Button) findViewById(R.id.btnOK);*/

        //Vincular el id de las vistas con las clases view
        ButterKnife.bind(this);

        /*btnOK.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View view) {
                                         iniciarActivityIntnet();
                                     }
                                 }
        );*/

        Log.d(TAG, "SendMessage: OnCreate");
    }

    // hacer metodo onclick con la libreria butter knife
    @OnClick(R.id.btnOK)
    public void getOnclick(View view){
        iniciarActivityIntnet();
    }

    /*
    //Para no crear listeners
    public void getOnCLick(View view){
        switch (view.getId()){
            case R.id.btnOK:
                iniciarActivityIntnet()
                break;
        }
     */

    void iniciarActivityIntnet() {
        //1. Recoger mensaje
        // edtMessage.getText().toString();

        // Damos memoria el objeto msg creado antes para pasar los strings al constructor
        msg = new Message(edtMessage.getText().toString(),edtUser.getText().toString());

        //2. Crear un objeto Bundle y añadir el mensaje
        // bundle es un paquete donde añadimos (put) elementos (strings) para llevarlos a otra activity
        Bundle bundle = new Bundle();
        //bundle.putString("message", edtMessage.getText().toString());
        //bundle.putString("user", edtUser.getText().toString());
        bundle.putSerializable("message",msg);

        //3. Crear un objeto intent
        // intent implicito, al abrir un pdf android nos pregunta con que abrirlo, eso es.
        // intent es (origen,destino)
        Intent intent = new Intent(SendMessageActivity.this, ViewMessageActivity.class);

        //4. añadimos al intent el bundle que hemos creado antes
        intent.putExtras(bundle);

        //5. Iniciar la Activity ViewMessage
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessage: OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "SendMessage: OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "SendMessage: OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "SendMessage: OnStop");
    }
}