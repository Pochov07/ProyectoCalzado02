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

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;

import java.util.ArrayList;
import java.util.List;


public class AdaptadorPersonalizado  extends RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder> {

    private Context context;
    private List<Categoria> listaCategorias = new ArrayList<>();

    public AdaptadorPersonalizado( Context context, List<Categoria> listaCategorias){
        this.context = context;
        this.listaCategorias = listaCategorias;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.filacategoria,parent,false);
        return new MiViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.fila_nombreCategoria.setText(listaCategorias.get(position).getNombreCategoria()+"");
        holder.fila_descripcionCategria.setText(listaCategorias.get(position).getDescripcionCategoria()+"");
        holder.fila_btnEditarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoriaActivity.class);

                intent.putExtra("paramIdCategoria", listaCategorias.get(position).getIdcategoria()+"");
                intent.putExtra("paramNombreCategoria", listaCategorias.get(position).getNombreCategoria()+"");
                intent.putExtra("paramDescripcionCategoria", listaCategorias.get(position).getDescripcionCategoria()+"");
                context.startActivity(intent);

            }
        });

        holder.fila_btnEliminarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ventana = new AlertDialog.Builder(context);
                ventana.setTitle(Html.fromHtml("<font color='#275A7C'> CONFIRMAR ELIMINAR "+ "</font>"));
                ventana.setMessage("¿ Desea eliminar la categoría  "+ listaCategorias.get(position).getNombreCategoria()+" ?");
                ventana.setNegativeButton(Html.fromHtml("<font color='#000000'> NO"+ "</font>"),null);
                ventana.setPositiveButton(Html.fromHtml("<font color='#FF0000'> SÍ "+ "</font>"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DAOCategoria daoCategoria = new DAOCategoria(context);
                        daoCategoria.abrirBD();
                        String mensaje = daoCategoria.eliminarCategoria(listaCategorias.get(position).getIdcategoria());

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
        return  listaCategorias.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView fila_nombreCategoria, fila_descripcionCategria;
        ImageButton fila_btnEditarCategoria, fila_btnEliminarCategoria;


        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            fila_nombreCategoria = itemView.findViewById(R.id.fila_nombreCategoria);
            fila_descripcionCategria = itemView.findViewById(R.id.fila_descripcionCategoria);
            fila_btnEditarCategoria = itemView.findViewById(R.id.fila_btnEditarCategoria);
            fila_btnEliminarCategoria = itemView.findViewById(R.id.fila_btnEliminarCategoria);
        }
    }

}