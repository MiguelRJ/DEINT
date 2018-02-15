package com.example.ficherosfinal;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ficherosfinal.service.MyIntentService;
import com.example.ficherosfinal.service.ServiceAsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtImagen, edtFrase;
    private ImageView imgImagen;
    private TextView txvTexto;
    private Button btnDescargar;
    String[] enlacesImg;
    String[] enlacesFrases;
    int tiempo;
    //OperacionesFichero opFi;
    ProgressDialog progress;
    //String errores = "http://alumno.mobi/superior/rodriguez/uploads/errores.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtImagen = (EditText) findViewById(R.id.edtImagen);
        edtFrase = (EditText) findViewById(R.id.edtFrase);
        imgImagen = (ImageView) findViewById(R.id.imgImagen);
        txvTexto = (TextView) findViewById(R.id.txvTexto);
        btnDescargar = (Button) findViewById(R.id.btnDescargar);
        btnDescargar.setOnClickListener(this);
        progress = new ProgressDialog(this);
        //opFi = new OperacionesFichero();
        leerTiempo();
    }

    @Override
    public void onClick(View v) {
        if (v == btnDescargar) {
            enlacesFrases = null;
            enlacesImg = null;
            descargarFichero(edtImagen.getText().toString(), "img.txt");
            descargarFichero(edtFrase.getText().toString(), "txt.txt");
        }
    }

    private void descargarFichero(String origin, String destination) {
        /*new FileAsyncTask().execute(origin, destination);
        switch (destination) {
            case "img.txt":
                break;
            case "txt.txt":
                break;
        }*/
        Intent intent = new Intent(this,ServiceAsyncHttpResponseHandler.class);
        Bundle bundle = new Bundle();
        bundle.putString(MyIntentService.INTENT_DATA_SOURCE, origin);
        bundle.putString(MyIntentService.INTENT_DATA_DESTINATION, destination);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void descargaImagen(String url) {
        Picasso.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(imgImagen);
    }

    private void descargaImagenes() {
        final int[] i = {0};
        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (enlacesImg != null) {
                    descargaImagen(enlacesImg[i[0]++ % enlacesImg.length]);
                }
                ha.postDelayed(this, tiempo);
            }
        }, tiempo);
    }

    private void descargaFrase(String frase) {
        txvTexto.setText(frase);
    }

    private void descargaFrases() {
        final int[] i = {0};
        final Handler ha = new Handler();
        ha.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (enlacesFrases != null) {
                    descargaFrase(enlacesFrases[i[0]++ % enlacesFrases.length]);
                }
                ha.postDelayed(this, tiempo);
            }
        }, tiempo);
    }

    void leerTiempo() {
        InputStream inputStream = getResources().openRawResource(R.raw.intervalo);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        try {
            while ((line = buffreader.readLine()) != null) {
                tiempo = Integer.parseInt(line) * 1000;
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    /**
     * Clase que inicia la descargar de los ficheros
     */
    /*class FileAsyncTask extends AsyncTask<String, Void, String> {

        private RestClient restClient;
        //private File fichero;

        @Override
        protected void onPreExecute() {
            progress.setProgress(ProgressDialog.STYLE_SPINNER);
            progress.setMessage("Conectando...");
            restClient = new RestClient();
            progress.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    restClient.cancelRequests(MainActivity.this, true);
                }
            });
            progress.show();
        }

        @Override
        protected String doInBackground(String... args) {
            String destination = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + args[1];
            restClient.get(args[0], new FileAsyncHttpResponseHandler(new File(destination), true) {
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    cancel(true);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File response) {

                }
            });
            return args[1];
        }

        @Override
        protected void onPostExecute(String destination) {
            super.onPostExecute(destination);
            progress.dismiss();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            progress.dismiss();

        }
    }*/

}