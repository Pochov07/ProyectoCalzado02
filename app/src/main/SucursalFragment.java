package com.grupal.proyectoNoelia;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SucursalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SucursalFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnMiraflores, btnSanMiguel, btnCallao;

    public SucursalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SucursalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SucursalFragment newInstance(String param1, String param2) {
        SucursalFragment fragment = new SucursalFragment();
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
        View v = inflater.inflate( R.layout.fragment_sucursal,container,false);

        btnMiraflores= v.findViewById(R.id.btnMiraflores);
        btnCallao= v.findViewById(R.id.btnCallao);
        btnSanMiguel= v.findViewById(R.id.btnSanMiguel);
        btnMiraflores= v.findViewById(R.id.btnMiraflores);
        btnMiraflores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapaActivity.class);
                intent.putExtra("latitud","-12.12288");
                intent.putExtra("longitud","-7703005");
                intent.putExtra("titulo", "SUCURSAL MIRAFLORES");
                getContext().startActivity(intent);
            }
        });

        btnSanMiguel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapaActivity.class);
                intent.putExtra("latitud","-12.06886");
                intent.putExtra("longitud","-77.09804");
                intent.putExtra("titulo", "SUCURSAL SAN MIGUEL");
                getContext().startActivity(intent);
            }
        });

        btnCallao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapaActivity.class);
                intent.putExtra("latitud","-12.07786");
                intent.putExtra("longitud","-77.08956");
                intent.putExtra("titulo", "SUCURSAL CALLAO");
                getContext().startActivity(intent);
            }
        });




        return v;
    }
}