package com.grupal.proyectoNoelia.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupal.proyectoNoelia.entidad.Proveedor;
import com.grupal.proyectoNoelia.util.ProveedoresBD;

import java.util.ArrayList;
import java.util.List;

public class DAOProveedor {
    ProveedoresBD proveedoresBD;
    SQLiteDatabase db;
    Context context;

    public DAOProveedor(Context context){
        this.context = context;
        proveedoresBD = new ProveedoresBD(context);
    }

    public void abrirBD(){

        db = proveedoresBD.getWritableDatabase();
    }

    public String registrarProveedor(Proveedor proveedor){
        String respuesta= "";

        try{
            ContentValues valores = new ContentValues();
            valores.put("rucProveedor", proveedor.getRucProveedor());
            valores.put("razonSocialProveedor", proveedor.getRazonSocialProveedor());
            valores.put("direccionProveedor", proveedor.getDireccionProveedor());
            valores.put("telefonoProveedor", proveedor.getTelefonoProveedor());


            long rpta = db.insert("proveedor", null,valores);
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

    public String editarProveedor (Proveedor proveedor){
        String respuesta = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("rucProveedor", proveedor.getRucProveedor());
            valores.put("razonSocialProveedor", proveedor.getRazonSocialProveedor());
            valores.put("direccionProveedor", proveedor.getDireccionProveedor());
            valores.put("telefonoProveedor", proveedor.getTelefonoProveedor());
            long rpta = db.update("proveedor", valores, "idProveedor="+proveedor.getIdProveedor(),null);

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


    public String eliminarProveedor(int idProveedor){
        String respuesta = "";

        try {
            long rpta = db.delete("proveedor", "idProveedor="+idProveedor,null);

            if (rpta == -1){
                respuesta ="Error al eliminar proveedor";
            }else {
                respuesta = "Se eliminó correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }

        return respuesta;
    }

    public List<Proveedor> cargarProveedor(){
        List<Proveedor> listaProveedor = new ArrayList<>();

        try {
            Cursor c= db.rawQuery("SELECT *FROM proveedor",null);
            while (c.moveToNext()){
                listaProveedor.add(new Proveedor(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4)));
            }
        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return listaProveedor;
    }

}
