package com.grupal.proyectoNoelia;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.grupal.proyectoNoelia.entidad.Categoria;
import com.grupal.proyectoNoelia.entidad.Proveedor;
import com.grupal.proyectoNoelia.modelo.DAOCategoria;
import com.grupal.proyectoNoelia.modelo.DAOProveedor;

public class ProveedorActivity extends AppCompatActivity {
    TextInputEditText txtRUCProveedor, txtRazonsocialProveedor, txtDireccionProveedor, txtTelefonoProveedor;
    Button btnGuardarProveedor;
    TextView txtTituloProveedor;
    Proveedor proveedor;

    boolean registra=true;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);
        asignarReferencias();
        recuperarDatos();

    }

    private void recuperarDatos(){
        if(getIntent().hasExtra("paramIdProveedor")){
            txtTituloProveedor.setText("MODIFICAR DATOS ");
            btnGuardarProveedor.setText("MODIFICAR");
            registra = false;
            id= Integer.parseInt(getIntent().getStringExtra("paramIdProveedor"));
            txtRUCProveedor.setText(getIntent().getStringExtra("paramRucProveedor"));
            txtRazonsocialProveedor.setText(getIntent().getStringExtra("paramRazonSocialProveedor"));
            txtDireccionProveedor.setText(getIntent().getStringExtra("paramDireccionProveedor"));
            txtTelefonoProveedor.setText(getIntent().getStringExtra("paramTelefonoProveedor"));
        }
    }

    private void asignarReferencias(){
        txtRUCProveedor = findViewById(R.id.txtRUCProveedor);
        txtRazonsocialProveedor = findViewById(R.id.txtRazonSocialProveedor);
        txtDireccionProveedor = findViewById(R.id.txtDireccionProveedor);
        txtTelefonoProveedor = findViewById(R.id.txtTelefonoProveedor);
        btnGuardarProveedor = findViewById(R.id.btnGuardarProveedor);
        txtTituloProveedor = findViewById(R.id.txtTituloProveedor);

        btnGuardarProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturarDatos()){
                    DAOProveedor daoProveedor = new DAOProveedor(ProveedorActivity.this);
                    daoProveedor.abrirBD();
                    String mensaje;

                    if(registra) {
                        mensaje = daoProveedor.registrarProveedor(proveedor);
                    }else{
                        mensaje = daoProveedor.editarProveedor(proveedor);
                    }
                    AlertDialog.Builder ventana = new AlertDialog.Builder(ProveedorActivity.this);
                    ventana.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // mostrarFragmento(new ListarCategoriaFragment());


                            Fragment fragment = new ListarProveedorFragment();
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
        String ruc = txtRUCProveedor.getText().toString();
        String razonSocial = txtRazonsocialProveedor.getText().toString();
        String direccion = txtDireccionProveedor.getText().toString();
        String telefono = txtTelefonoProveedor.getText().toString();


        if(ruc.equals("")){
            txtRUCProveedor.setError("RUC es obligatorio");
            valida = false;
        }
        if(razonSocial.equals("")){
            txtRazonsocialProveedor.setError("Razon Social es obligatorio");
            valida =false;
        }
        if(direccion.equals("")){
            txtDireccionProveedor.setError("Dirección es obligatorio");
            valida = false;
        }
        if(telefono.equals("")){
            txtTelefonoProveedor.setError("Teléfono es obligatorio");
            valida =false;
        }


        if (valida){
            if(registra) {
               proveedor = new Proveedor(ruc, razonSocial,direccion,telefono);
            }else{
                proveedor = new Proveedor(id,ruc, razonSocial,direccion,telefono);
            }
        }

        return valida;
    }

    public void onClick(View view) {
    }
}