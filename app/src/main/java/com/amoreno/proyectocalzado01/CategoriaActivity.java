package com.grupal.proyectoNoelia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;

import java.lang.reflect.Parameter;
import java.security.Policy;

public class CategoriaActivity extends AppCompatActivity {
    TextInputEditText txtNombreCategoria, txtDescripcionCategoria;
    Button btnGuardarCategoria;
    ImageView imagenCategoria;
    TextView txtTitulo;
    Categoria categoria;

    boolean registra=true;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        asignarReferencias();
        recuperarDatos();

    }

    private void recuperarDatos(){
        if(getIntent().hasExtra("paramIdCategoria")){
            txtTitulo.setText("MODIFICAR DATOS ");
            btnGuardarCategoria.setText("MODIFICAR");
            registra = false;
            id= Integer.parseInt(getIntent().getStringExtra("paramIdCategoria"));
            txtNombreCategoria.setText(getIntent().getStringExtra("paramNombreCategoria"));
            txtDescripcionCategoria.setText(getIntent().getStringExtra("paramDescripcionCategoria"));

        }
    }

    private void asignarReferencias(){
        txtNombreCategoria = findViewById(R.id.txtNombreCategoria);
        txtDescripcionCategoria = findViewById(R.id.txtDescripcionCategoria);
        btnGuardarCategoria = findViewById(R.id.btnRegistrarCategoria);
        txtTitulo = findViewById(R.id.txtTituloCategoria);

        btnGuardarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturarDatos()){
                    DAOCategoria daoCategoria = new DAOCategoria(CategoriaActivity.this);
                    daoCategoria.abrirBD();
                    String mensaje;

                    if(registra) {
                        mensaje = daoCategoria.registrarCategoria(categoria);
                    }else{
                        mensaje = daoCategoria.editarCategoria(categoria);
                    }
                    AlertDialog.Builder ventana = new AlertDialog.Builder(CategoriaActivity.this);
                    ventana.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           // mostrarFragmento(new ListarCategoriaFragment());


                            Fragment  fragment = new ListarCategoriaFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();

                            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();


                        }
                    });
                    ventana.create().show();
                };
            }
        });
    }

    private void mostrarFragmento(Fragment fragment){
       getSupportFragmentManager().beginTransaction().replace(R.id.drawer_layout,fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();

    }

    private boolean capturarDatos(){
        boolean valida = true;
        String nombre = txtNombreCategoria.getText().toString();
        String descripcion = txtDescripcionCategoria.getText().toString();


        if(nombre.equals("")){
            txtNombreCategoria.setError("Nombre es obligatorio");
            valida = false;
        }
        if(descripcion.equals("")){
            txtDescripcionCategoria.setError("Descripción es obligatorio");
            valida =false;
        }


        if (valida){
            if(registra) {
                categoria = new Categoria(nombre, descripcion);
            }else{
                categoria = new Categoria(id,nombre, descripcion);
            }
        }

        return valida;
    }

    public void onClick(View view) {
    }
}