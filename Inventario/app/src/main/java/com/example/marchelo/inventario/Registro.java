package com.example.marchelo.inventario;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registro extends AppCompatActivity {

    EditText et1,et2,et3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  setContentView(R.layout.activity_registro);
        //Cargar datos de los Botones
        et1= (EditText) findViewById(R.id.txtNombre);
        et2= (EditText) findViewById(R.id.txtUsuarioRe);
        et3= (EditText) findViewById(R.id.txtContraseñaRe);
    }
    //metodo para registrar usuario
    public void registrar(View v){
        DBHelper admin=new DBHelper(this,"instituto",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String nombre=et1.getText().toString();
        String usuario=et2.getText().toString();
        String contraseña=et3.getText().toString();

        ContentValues values=new ContentValues();
        values.put("nombre",nombre);
        values.put("usuario",usuario);
        values.put("contrasena",contraseña);

        db.insert("usuarios",null,values);
        db.close();

        Intent ven=new Intent(this,MainActivity.class);
        startActivity(ven);
    }

    //metodo para cancelar el registro
    public void cancelar(View view){
        finish();
    }
}
