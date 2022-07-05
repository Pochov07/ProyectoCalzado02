package com.grupal.proyectoNoelia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProveedoresBD extends SQLiteOpenHelper {
    public ProveedoresBD(Context context){
        super(context,"proveedor.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE proveedor" +
                "(idProveedor INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "rucProveedor TEX NOT NULL," +
                "razonSocialProveedor TEX NOT NULL," +
                "direccionProveedor TEX NOT NULL," +
                "telefonoProveedor TEX NOT NULL);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
