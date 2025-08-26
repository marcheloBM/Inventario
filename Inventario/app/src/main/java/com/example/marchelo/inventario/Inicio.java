package com.example.marchelo.inventario;

import android.content.Intent;
import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Recupera un String desde el código
        String idiomaPorDefecto = getString(R.string.idiomaPorDefecto);
        mostrarIdioma(idiomaPorDefecto);

        Button btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void mostrarIdioma(String s){
        TextView tvIdioma = (TextView) findViewById(R.id.tvIdioma);
        tvIdioma.setText(s);
    }

    public void ingresarDat(View view){
        Intent ven=new Intent(this,ingresarDatos.class);
        startActivity(ven);
    }

    public void buscarDat(View view){
        Intent ven=new Intent(this,buscarDatos.class);
        startActivity(ven);
    }

    public void onClick(View v) {
        //String url = "https://duocdaa.firebaseio.com/saludoDelDia.json?print=pretty";
        String url = "https://inventario-df6cb.firebaseio.com/version.json?print=pretty";
        new StringTask().execute(url);
    }

    class StringTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();

            // Add the String message converter
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

            // Make the HTTP GET request, marshaling the response to a String
            String result = restTemplate.getForObject(url, String.class);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Inicio.this, s, Toast.LENGTH_LONG).show();
        }
    }
}
