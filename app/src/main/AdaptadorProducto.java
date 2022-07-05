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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupal.proyectoNoelia.entidad.Producto;
import com.grupal.proyectoNoelia.modelo.DAOProducto;


import java.util.ArrayList;
import java.util.List;

public class AdaptadorProducto  extends RecyclerView.Adapter<AdaptadorProducto.MiViewHolder> {

    private Context context;
    private List<Producto> listaProducto = new ArrayList<>();

    public AdaptadorProducto(Context context, List<Producto> listaProducto) {
        this.context = context;
        this.listaProducto = listaProducto;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.filaproducto, parent, false);
        return new MiViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.fila_codProducto.setText(listaProducto.get(position).getCodProducto() + "");
        holder.fila_nombreProducto.setText(listaProducto.get(position).getNombProducto() + "");
        holder.fila_colorProducto.setText(listaProducto.get(position).getColorProducto() + "");
        holder.fila_stockProducto.setText(listaProducto.get(position).getStockProducto() + "");
        holder.fila_tallaProducto.setText(listaProducto.get(position).getTallaProducto() + "");
        holder.fila_precioProducto.setText(listaProducto.get(position).getPrecioProducto() + "");
        holder.fila_btnEditarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductoActivity.class);

                intent.putExtra("paramIdProducto", listaProducto.get(position).getIdProducto() + "");
                intent.putExtra("paramCodProducto", listaProducto.get(position).getCodProducto() + "");
                intent.putExtra("paramNombProducto", listaProducto.get(position).getNombProducto() + "");
                intent.putExtra("paramColorProducto", listaProducto.get(position).getColorProducto() + "");
                intent.putExtra("paramStockProducto", listaProducto.get(position).getStockProducto() + "");
                intent.putExtra("paramTallaProducto", listaProducto.get(position).getTallaProducto() + "");
                intent.putExtra("paramPrecioProducto", listaProducto.get(position).getPrecioProducto() + "");
                context.startActivity(intent);

            }
        });

        holder.fila_btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle(Html.fromHtml("<font color='#275A7C'> CONFIRMAR ELIMINAR " + "</font>"));
                ventana.setMessage("¿ Desea eliminar la categoría  " + listaProducto.get(position).getNombProducto() + " ?");
                ventana.setNegativeButton(Html.fromHtml("<font color='#000000'> NO" + "</font>"), null);
                ventana.setPositiveButton(Html.fromHtml("<font color='#FF0000'> SÍ " + "</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DAOProducto daoProducto = new DAOProducto(context);
                        daoProducto.abrirBD();
                        String mensaje = daoProducto.eliminarProducto(listaProducto.get(position).getIdProducto());

                        AlertDialog.Builder ventana2 = new AlertDialog.Builder(context);
                        ventana2.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO " + "</font>"));
                        ventana2.setMessage(mensaje);
                        ventana2.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR " + "</font>"), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

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
        return listaProducto.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView fila_codProducto, fila_nombreProducto, fila_colorProducto, fila_stockProducto,
                fila_tallaProducto, fila_precioProducto;
        ImageButton fila_btnEditarProducto, fila_btnEliminarProducto;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            fila_codProducto = itemView.findViewById(R.id.filaCodProducto);
            fila_nombreProducto = itemView.findViewById(R.id.fila_nombreProducto);
            fila_colorProducto = itemView.findViewById(R.id.filaColorProducto);
            fila_stockProducto = itemView.findViewById(R.id.filaStockProducto);
            fila_tallaProducto = itemView.findViewById(R.id.filaTallaProducto);
            fila_precioProducto = itemView.findViewById(R.id.filaPrecioProducto);
            fila_btnEditarProducto = itemView.findViewById(R.id.fila_btnEditarProducto);
            fila_btnEliminarProducto = itemView.findViewById(R.id.fila_btnEliminarProducto);
        }
    }

}