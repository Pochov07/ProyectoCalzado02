package com.grupal.proyectoNoelia.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.util.CategoriasBD;

import java.util.ArrayList;
import java.util.List;

public class DAOCategoria {
    CategoriasBD  categoriasBD;
    SQLiteDatabase db;
    Context context;

    public DAOCategoria(Context context){
        this.context = context;
        categoriasBD = new CategoriasBD(context);
    }

    public void abrirBD(){

        db = categoriasBD.getWritableDatabase();
    }

    public String registrarCategoria(Categoria categoria){
        String respuesta= "";

        try{
            ContentValues valores = new ContentValues();
            valores.put("nombreCategoria", categoria.getNombreCategoria());
            valores.put("descripcionCategoria", categoria.getDescripcionCategoria());

            long rpta = db.insert("categorias", null,valores);
            if (rpta == -1){
                respuesta ="Error al registrar  categoría";
            }else {
                respuesta = "Se registró correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }

    public String editarCategoria (Categoria categoria){
        String respuesta = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("nombreCategoria", categoria.getNombreCategoria());
            valores.put("descripcionCategoria", categoria.getDescripcionCategoria());

            long rpta = db.update("categorias", valores, "idCategoria="+categoria.getIdcategoria(),null);

            if (rpta == -1){
                respuesta ="Error al actualizar datos";
            }else {
                respuesta = "Se actualizo correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }


    public String eliminarCategoria(int idCategoria){
        String respuesta = "";

        try {
            long rpta = db.delete("categorias", "idCategoria="+idCategoria,null);

            if (rpta == -1){
                respuesta ="Error al eliminar categoría";
            }else {
                respuesta = "Se eliminó correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }

        return respuesta;
    }

    public List<Categoria> cargarCategoria(){
        List<Categoria> listaCategoria = new ArrayList<>();

        try {
            Cursor c= db.rawQuery("SELECT *FROM categorias",null);
            while (c.moveToNext()){
                listaCategoria.add(new Categoria(c.getInt(0),c.getString(1),c.getString(2)));
            }
        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return listaCategoria;
    }


}
