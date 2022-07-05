package com.grupal.proyectoNoelia;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grupal.proyectoNoelia.entidad.Cliente;
import com.grupal.proyectoNoelia.modelo.DAOCliente;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoCliente extends RecyclerView.Adapter<AdaptadorPersonalizadoCliente.MiViewHolder> {

    private Context context;
    private List<Cliente> listaClientes = new ArrayList<>();

    public AdaptadorPersonalizadoCliente(Context context, List<Cliente> listaClientes){
        this.context = context;
        this.listaClientes = listaClientes;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View vista = inflater.inflate(R.layout.filacliente, parent, false);
        return new MiViewHolder(vista);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.filaDniCliente.setText(listaClientes.get(position).getDniCliente()+"");
        holder.filaNombresCliente.setText(listaClientes.get(position).getNombresCliente()+"");
        holder.filaApellidosCliente.setText(listaClientes.get(position).getApellidosCliente()+"");
        holder.filaTelefonoCliente.setText(listaClientes.get(position).getTelefonoCliente()+"");


        holder.filaEditarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClienteActivity.class);
                intent.putExtra("paramIdCliente", listaClientes.get(position).getIdCliente()+"");
                intent.putExtra("paramDniCliente", listaClientes.get(position).getDniCliente()+"");
                intent.putExtra("paramNombresCliente", listaClientes.get(position).getNombresCliente()+"");
                intent.putExtra("paramApellidosCliente", listaClientes.get(position).getApellidosCliente()+"");
                intent.putExtra("paramTelefonoCliente", listaClientes.get(position).getTelefonoCliente()+"");

                context.startActivity(intent);
            }
        });

        holder.filaEliminarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder ventana = new android.app.AlertDialog.Builder(context);
                ventana.setTitle(Html.fromHtml("<font color='#275A7C'> CONFIRMAR ELIMINAR "+ "</font>"));
                ventana.setMessage("¿ Desea eliminar el cliente  "+ listaClientes.get(position).getNombresCliente()+" ?");
                ventana.setNegativeButton(Html.fromHtml("<font color='#000000'> NO"+ "</font>"),null);
                ventana.setPositiveButton(Html.fromHtml("<font color='#FF0000'> SÍ "+ "</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DAOCliente daoCliente = new DAOCliente(context);
                        daoCliente.abrirDB();
                        String mensaje = daoCliente.eliminarCliente(listaClientes.get(position).getIdCliente());

                        android.app.AlertDialog.Builder ven2 = new AlertDialog.Builder(context);
                        ven2.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                        ven2.setMessage(mensaje);
                        ven2.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);
                            }
                        });
                        ven2.create().show();
                    }
                });
                ventana.create().show();
            }
        });
    }

    @Override
    public int getItemCount() { return listaClientes.size(); }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView filaNombresCliente, filaApellidosCliente, filaDniCliente, filaTelefonoCliente;
        ImageButton filaEliminarCliente, filaEditarcliente;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaDniCliente = itemView.findViewById(R.id.filaDniCliente);
            filaNombresCliente =itemView.findViewById(R.id.filaNombresCliente);
            filaApellidosCliente = itemView.findViewById(R.id.filaApellidosCliente);
            filaTelefonoCliente= itemView.findViewById(R.id.filaTelefonoCliente);

            filaEditarcliente = itemView.findViewById(R.id.fila_btnEditarCliente);
            filaEliminarCliente = itemView.findViewById(R.id.fila_btnEliminarCliente);
        }
    }
}