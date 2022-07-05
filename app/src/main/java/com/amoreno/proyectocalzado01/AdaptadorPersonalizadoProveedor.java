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

import com.grupal.proyectoNoelia.entidad.Proveedor;
import com.grupal.proyectoNoelia.modelo.DAOProveedor;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorPersonalizadoProveedor extends  RecyclerView.Adapter<AdaptadorPersonalizadoProveedor.MiViewHolder>{

    private Context context;
    private List<Proveedor> listaProveedor = new ArrayList<>();

    public AdaptadorPersonalizadoProveedor( Context context, List<Proveedor> listaProveedor){
        this.context = context;
        this.listaProveedor = listaProveedor;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.filaproveedor,parent,false);
        return new MiViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.filaRUCProveedor.setText(listaProveedor.get(position).getRucProveedor()+"");
        holder.filaRazonSocialProveedor.setText(listaProveedor.get(position).getRazonSocialProveedor()+"");
        holder.filaDireccionProveedor.setText(listaProveedor.get(position).getDireccionProveedor()+"");
        holder.filaTelefonoProveedor.setText(listaProveedor.get(position).getTelefonoProveedor()+"");

        holder.fila_btnEditarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProveedorActivity.class);

                intent.putExtra("paramIdProveedor", listaProveedor.get(position).getIdProveedor()+"");
                intent.putExtra("paramRucProveedor", listaProveedor.get(position).getRucProveedor()+"");
                intent.putExtra("paramRazonSocialProveedor", listaProveedor.get(position).getRazonSocialProveedor()+"");
                intent.putExtra("paramDireccionProveedor", listaProveedor.get(position).getDireccionProveedor()+"");
                intent.putExtra("paramTelefonoProveedor", listaProveedor.get(position).getTelefonoProveedor()+"");
                context.startActivity(intent);

            }
        });

        holder.fila_btnEliminarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle(Html.fromHtml("<font color='#275A7C'> CONFIRMAR ELIMINAR "+ "</font>"));
                ventana.setMessage("¿ Desea eliminar proveedor  "+ listaProveedor.get(position).getRazonSocialProveedor()+" ?");
                ventana.setNegativeButton(Html.fromHtml("<font color='#000000'> NO"+ "</font>"),null);
                ventana.setPositiveButton(Html.fromHtml("<font color='#FF0000'> SÍ " + "</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DAOProveedor daoProveedor = new DAOProveedor(context);
                        daoProveedor.abrirBD();
                        String mensaje = daoProveedor.eliminarProveedor(listaProveedor.get(position).getIdProveedor());

                        AlertDialog.Builder ventana2 = new AlertDialog.Builder(context);
                        ventana2.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                        ventana2.setMessage(mensaje);
                        ventana2.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Create new fragment and transaction
                                Intent intent = new Intent(context, MainActivity.class);
                                context.startActivity(intent);


                            }
                        });
                        ventana2.create().show();
                    }
                });
                ventana.create().show();

            }
        });


    }
    @Override
    public int getItemCount() {
        return  listaProveedor.size();
    }


    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView filaRUCProveedor, filaRazonSocialProveedor, filaDireccionProveedor, filaTelefonoProveedor;
        ImageButton fila_btnEditarProveedor, fila_btnEliminarProveedor;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaRUCProveedor = itemView.findViewById(R.id.filaRUCProveedor);
            filaRazonSocialProveedor = itemView.findViewById(R.id.filaRazonSocialProveedor);
            filaDireccionProveedor = itemView.findViewById(R.id.filaDireccionProveedor);
            filaTelefonoProveedor = itemView.findViewById(R.id.filaTelefonoProveedor);
            fila_btnEditarProveedor = itemView.findViewById(R.id.fila_btnEditarProveedor);
            fila_btnEliminarProveedor = itemView.findViewById(R.id.fila_btnEliminarProveedor);

        }
    }



}
