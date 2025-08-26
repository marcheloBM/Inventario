package com.example.marchelo.inventario;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class buscarDatos extends AppCompatActivity {

    EditText txtbuscar,txtNombre,txtStock,txtValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_datos);

        txtbuscar=(EditText)findViewById(R.id.Ebuscar);

        txtNombre=(EditText)findViewById(R.id.NNombre);
        txtStock=(EditText)findViewById(R.id.NStock);
        txtValor=(EditText)findViewById(R.id.NValor);

    }

    public void buscarProducto(View view){
        ToDoDbHelper db = new ToDoDbHelper(getApplicationContext());
        String buscar = txtbuscar.getText().toString();
        String[] datos;
        datos=db.buscar_reg(buscar);
        txtNombre.setText(datos[0]);
        txtValor.setText(datos[1]);
        txtStock.setText(datos[2]);
        Toast.makeText(getApplicationContext(), datos[3], Toast.LENGTH_SHORT).show();
    }

    public void eliminarProducto(View view){
        ToDoDbHelper db = new ToDoDbHelper(getApplicationContext());
        String Nombre = txtNombre.getText().toString();
        String mensaje =db.eliminar(Nombre);
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
    }

    public void actualizarProducto(View view){
        ToDoDbHelper db = new ToDoDbHelper(getApplicationContext());
        String Buscar = txtbuscar.getText().toString();
        String Nombre = txtNombre.getText().toString();
        int Valor = Integer.parseInt(txtValor.getText().toString());
        int Stock = Integer.parseInt(txtStock.getText().toString());
        String Mensaje =db.actualizar(Buscar, Nombre, Valor, Stock);
        Toast.makeText(getApplicationContext(),Mensaje,Toast.LENGTH_SHORT).show();
    }

    public void volverInicio(View view){
        Intent intent = new Intent(this, Inicio.class);
        startActivity(intent);
        finish();
    }

    public void listarVen(View view){
        Intent intent = new Intent(this, ListarDatos.class);
        startActivity(intent);
    }
}
