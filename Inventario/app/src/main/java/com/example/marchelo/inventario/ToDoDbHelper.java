package com.example.marchelo.inventario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.ArrayList;

/**
 * Created by marchelo on 13-12-2016.
 */
public class ToDoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "todo.db";

    public static final String TAREA_TABLE = "producto";

    public static final String TAREA_NOMBRE = "nombre";
    public static final String TAREA_VALOR = "valor";
    public static final String TAREA_STOCK = "stock";
    public static final String TAREA_IMAGEN = "imagen";

    public ToDoDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE producto(nombre TEXT NOT NULL, valor INT DEFAULT 1\n" +
                "\t, stock INT DEFAULT 1, imagen TEXT NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion
            , int newVersion) {
        // código necesario para modificar la estructura en caso
        // que se hayan realizado modificaciones en el esquema
    }

    public ArrayList llenar_nom(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM producto";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                String img = registros.getString(3);
                Bitmap imagen =StringToBitMap(img);
                lista.add(registros.getString(0)+" Stock "+registros.getString(2)+" "+imagen);
            }while(registros.moveToNext());
        }
        return lista;

    }

    public String[] buscar_reg(String buscar){
        String[] datos= new String[4];
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT * FROM producto WHERE nombre ='"+buscar+"'";
        Cursor registros = database.rawQuery(q, null);
        if(registros.moveToFirst()){
            for(int i = 0 ; i<3;i++){
                datos[i]= registros.getString(i);

            }
            datos[3]= "Encontrado";
        }else{

            datos[3]= "No se encontro a "+buscar;
        }
        database.close();
        return datos;
    }

    public String eliminar(String Nombre){
        String mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        int cantidad =database.delete("producto", "nombre='" + Nombre + "'", null);
        if (cantidad !=0){
            mensaje="Eliminado Correctamente";
        }
        else{
            mensaje = "No existe";
        }
        database.close();
        return mensaje;
    }
    public String actualizar(String Buscar,String Nombre,int Valor,int Stock){
        String Mensaje ="";
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre",Nombre);
        contenedor.put("valor",Valor);
        contenedor.put("stock",Stock);
        int cantidad = database.update("producto", contenedor, "nombre='" + Buscar + "'", null);
        if(cantidad!=0){
            Mensaje="Actualizado Correctamente";
        }else{
            Mensaje="No Actualizado";
        }
        database.close();
        return Mensaje;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
