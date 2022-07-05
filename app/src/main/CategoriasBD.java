package com.grupal.proyectoNoelia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CategoriasBD extends SQLiteOpenHelper {

    public CategoriasBD(Context context){
        super(context, "categorias.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE categorias" +
                        "(idCategoria INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nombreCategoria TEXT NOT NULL, "+
                        "descripcionCategoria TEXT NOT NULL);" ;
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
