package com.grupal.proyectoNoelia;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.entidad.Producto;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;
import com.grupal.proyectoNoelia.modelo.DAOProducto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarProductoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rv_producto;
    FloatingActionButton btn_nuevoProducto;

    public ListarProductoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarProductoFragment newInstance(String param1, String param2) {
        ListarProductoFragment fragment = new ListarProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_listar_producto, container, false);
        rv_producto = v.findViewById(R.id.rv_producto);
        btn_nuevoProducto = v.findViewById(R.id.btn_nuevoProducto);
        btn_nuevoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductoActivity.class);
                getContext().startActivity(intent);
            }
        });
        mostrarProducto();
        return v;
    }


    private void mostrarProducto() {
        List<Producto> listaProducto = new ArrayList<>();
        DAOProducto daoProducto = new DAOProducto(getContext());
        AdaptadorProducto adaptador;

        daoProducto.abrirBD();
        listaProducto = daoProducto.cargarProducto();
        adaptador = new AdaptadorProducto(getContext(), listaProducto);
        rv_producto.setAdapter(adaptador);
        rv_producto.setLayoutManager(new LinearLayoutManager(getContext()));


    }
}