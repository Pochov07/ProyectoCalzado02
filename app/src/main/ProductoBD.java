package com.grupal.proyectoNoelia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductoBD extends SQLiteOpenHelper {
    public ProductoBD(Context context){
        super(context, "productos.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query=
                "CREATE TABLE productos" +
                        "(idproducto INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "codigoProducto TEXT NOT NULL, "+
                        "nombProducto TEXT NOT NULL, "+
                        "colorProducto TEXT NOT NULL, "+
                        "stockProducto INTEGER NOT NULL, "+
                        "tallaProducto INTEGER NOT NULL, "+
                        "precioProducto DOUBLE NOT NULL);" ;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}