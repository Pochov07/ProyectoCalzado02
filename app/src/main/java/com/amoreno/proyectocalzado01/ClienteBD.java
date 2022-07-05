package com.grupal.proyectoNoelia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ClienteBD extends SQLiteOpenHelper {

    public  ClienteBD (Context context){
        super(context, "clientes.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE clientes" +
                "(idCliente INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "dniCliente TEXT NOT NULL, "+
                "nombresCliente TEXT NOT NULL,"+
                "apellidosCliente TEXT NOT NULL,"+
                "telefonoCliente TEXT NOT NULL);" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
