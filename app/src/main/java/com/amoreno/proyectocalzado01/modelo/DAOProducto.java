package com.grupal.proyectoNoelia.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupal.proyectoNoelia.entidad.Producto;
import com.grupal.proyectoNoelia.util.ProductoBD;

import java.util.ArrayList;
import java.util.List;

public class DAOProducto {
    ProductoBD productoBD;
    SQLiteDatabase db;
    Context context;

    public DAOProducto(Context context){
        this.context = context;
        productoBD = new ProductoBD(context);
    }

    public void abrirBD(){

        db = productoBD.getWritableDatabase();
    }

    public String registrarProducto(Producto producto){
        String respuesta= "";

        try{
            ContentValues valores = new ContentValues();
            valores.put("codigoProducto", producto.getCodProducto());
            valores.put("nombProducto", producto.getNombProducto());
            valores.put("colorProducto", producto.getColorProducto());
            valores.put("stockProducto", producto.getStockProducto());
            valores.put("tallaProducto", producto.getTallaProducto());
            valores.put("precioProducto", producto.getPrecioProducto());

            long rpta = db.insert("productos", null,valores);
            if (rpta == -1){
                respuesta ="Error al registrar  producto";
            }else {
                respuesta = "Se registró correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }

    public String editarProducto (Producto producto){
        String respuesta = "";
        try{
            ContentValues valores = new ContentValues();
            valores.put("nombProducto", producto.getNombProducto());
            valores.put("codigoProducto", producto.getCodProducto());
            valores.put("colorProducto", producto.getColorProducto());
            valores.put("stockProducto", producto.getStockProducto());
            valores.put("tallaProducto", producto.getTallaProducto());
            valores.put("precioProducto", producto.getPrecioProducto());

            long rpta = db.update("productos", valores, "idProducto="+producto.getIdProducto(),null);

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


    public String eliminarProducto(int idProducto){
        String respuesta = "";

        try {
            long rpta = db.delete("productos", "idProducto="+idProducto,null);

            if (rpta == -1){
                respuesta ="Error al eliminar producto";
            }else {
                respuesta = "Se eliminó correctamente";
            }

        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }

        return respuesta;
    }

    public List<Producto> cargarProducto(){
        List<Producto> listaProducto = new ArrayList<>();

        try {
            Cursor c= db.rawQuery("SELECT *FROM productos",null);
            while (c.moveToNext()){
                listaProducto.add(new Producto(c.getInt(0),c.getString(1),c.getString(2),
                        c.getString(3), c.getInt(4),c.getInt(5),c.getDouble(6)));
            }
        }catch (Exception e){
            Log.d("===>", e.getMessage());
        }
        return listaProducto;
    }


}
