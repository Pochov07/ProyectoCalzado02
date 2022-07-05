package com.grupal.proyectoNoelia;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarCategoriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarCategoriaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    RecyclerView rv_categoria;
    FloatingActionButton btn_nuevoCategoria;

    public ListarCategoriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarCategoriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarCategoriaFragment newInstance(String param1, String param2) {
        ListarCategoriaFragment fragment = new ListarCategoriaFragment();
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

        View v = inflater.inflate( R.layout.fragment_listar_categoria,container,false);
        rv_categoria = v.findViewById(R.id.rv_categoria);
        btn_nuevoCategoria = v.findViewById(R.id.btn_nuevoCategoria);
        btn_nuevoCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoriaActivity.class);
                getContext().startActivity(intent);
            }
        });
        mostrarCategoria();
        return v;
    }


    private void mostrarCategoria(){
        List<Categoria> listaCategoria =new ArrayList<>();
        DAOCategoria daoCategoria = new DAOCategoria(getContext());
        AdaptadorPersonalizado adaptador;

        daoCategoria.abrirBD();
        listaCategoria = daoCategoria.cargarCategoria();
        adaptador = new AdaptadorPersonalizado(getContext(),listaCategoria);
        rv_categoria.setAdapter(adaptador);
        rv_categoria.setLayoutManager(new GridLayoutManager(getContext(),2));


    }


}