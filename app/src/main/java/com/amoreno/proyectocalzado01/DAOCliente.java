package com.grupal.proyectoNoelia.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.grupal.proyectoNoelia.entidad.Cliente;
import com.grupal.proyectoNoelia.util.ClienteBD;

import java.util.ArrayList;
import java.util.List;

public class DAOCliente {
    ClienteBD clientesBD;
    SQLiteDatabase db;
    Context context;

    public DAOCliente(Context context){
        this.context = context;
        clientesBD = new ClienteBD(context);
    }

    public void abrirDB(){
        db = clientesBD.getWritableDatabase();
    }

    public String registrarCliente(Cliente objCliente) {
        String respuesta = "";

        try {
            ContentValues valores = new ContentValues();
            valores.put("dniCliente", objCliente.getDniCliente());
            valores.put("nombresCliente", objCliente.getNombresCliente());
            valores.put("apellidosCliente", objCliente.getApellidosCliente());
            valores.put("telefonoCliente", objCliente.getTelefonoCliente());

            long rpta = db.insert("clientes", null, valores);
            if (rpta == -1) {
                respuesta = "Error al registrar cliente";
            } else {
                respuesta = "Se registró correctamente";
            }
        } catch (Exception e) {
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }

    public String modificarCliente(Cliente objCliente) {
        String respuesta = "";
        try {
            ContentValues valores = new ContentValues();
            valores.put("dniCliente", objCliente.getDniCliente());
            valores.put("nombresCliente", objCliente.getNombresCliente());
            valores.put("apellidosCliente", objCliente.getApellidosCliente());
            valores.put("telefonoCliente", objCliente.getTelefonoCliente());

            long rpta = db.update("clientes", valores, "idCliente=" + objCliente.getIdCliente(), null);
            if (rpta == -1) {
                respuesta = "Error al actualizar";
            } else {
                respuesta = "Se actualizó correctamente";
            }
        } catch (Exception e) {
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }

    public String eliminarCliente(int id) {
        String respuesta = "";
        try {
            long rpta = db.delete("clientes", "idCliente=" + id, null);
            if(rpta == -1){
                respuesta = "Error al eliminar cliente";
            }else{
                respuesta = "Se eliminó correctamente";
            }
        } catch (Exception e) {
            Log.d("===>", e.getMessage());
        }
        return respuesta;
    }

    public List<Cliente> cargarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        try {
            Cursor c = db.rawQuery("SELECT *FROM clientes", null);
            while (c.moveToNext()) {
                listaClientes.add(new Cliente(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }
        } catch (Exception e) {
            Log.d("===>", e.getMessage());
        }
        return listaClientes;
    }

}
