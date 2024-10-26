package com.example.marchelo.inventario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListarDatos extends AppCompatActivity {

    ListView lv ;
    ArrayList<String> lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_datos);

        lv = (ListView)findViewById(R.id.lista);

        ToDoDbHelper db = new ToDoDbHelper(getApplicationContext());
        lista = db.llenar_nom();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        lv.setAdapter(adaptador);
    }
}
