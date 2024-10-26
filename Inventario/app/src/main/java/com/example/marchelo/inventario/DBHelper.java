package com.example.marchelo.inventario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marchelo on 13-12-2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuarios(nombre text,usuario text,contrasena text)");
        db.execSQL("insert into usuarios values('admin','admin','admin')");
        db.execSQL("insert into usuarios values('user','user','123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("create table usuarios(nombre text,usuario text,contrasena text)");
        db.execSQL("insert into usuarios values('admin','admin','admin')");
        db.execSQL("insert into usuarios values('user','user','123')");
    }
}
