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
import com.grupal.proyectoNoelia.entidad.Proveedor;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;
import com.grupal.proyectoNoelia.modelo.DAOProveedor;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarProveedorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarProveedorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rvProveedores;
    FloatingActionButton btn_nuevoProveedor;

    public ListarProveedorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarProveedorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarProveedorFragment newInstance(String param1, String param2) {
        ListarProveedorFragment fragment = new ListarProveedorFragment();
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
        View v = inflater.inflate( R.layout.fragment_listar_proveedor,container,false);
        rvProveedores = v.findViewById(R.id.rvProveedores);
        btn_nuevoProveedor= v.findViewById(R.id.btnNuevoProveedor);
        btn_nuevoProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProveedorActivity.class);
                getContext().startActivity(intent);
            }
        });
        mostrarProveedor();
        return v;
    }

    private void mostrarProveedor(){
        List<Proveedor> listaProveedor =new ArrayList<>();
        DAOProveedor daoProveedor = new DAOProveedor(getContext());
        AdaptadorPersonalizadoProveedor adaptador;

        daoProveedor.abrirBD();
        listaProveedor = daoProveedor.cargarProveedor();
       adaptador = new AdaptadorPersonalizadoProveedor(getContext(),listaProveedor);
        rvProveedores.setAdapter(adaptador);
        rvProveedores.setLayoutManager(new LinearLayoutManager(getContext()));


    }



}