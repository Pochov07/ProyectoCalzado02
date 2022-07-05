package com.grupal.proyectoNoelia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputEditText;
import com.grupal.proyectoNoelia.entidad.Cliente;
import com.grupal.proyectoNoelia.modelo.DAOCliente;

public class ClienteActivity extends AppCompatActivity {

    TextInputEditText txtNombresCliente, txtApellidosCliente, txtDniCliente, txtTelefonoCliente;
    TextView txtTituloCliente;
    Button btnRegistrarCliente;
    Cliente cliente;

    boolean registra = true;
    int id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        asignarReferencias();
        recuperarDatos();

    }

    private void recuperarDatos(){
        if(getIntent().hasExtra("paramIdCliente")){
            txtTituloCliente.setText("MODIFICAR CLIENTE");
            btnRegistrarCliente.setText("MODIFICAR CIENTE");
            registra = false;
            id = Integer.parseInt(getIntent().getStringExtra("paramIdCliente"));
            txtDniCliente.setText(getIntent().getStringExtra("paramDniCliente"));
            txtNombresCliente.setText(getIntent().getStringExtra("paramNombresCliente"));
            txtApellidosCliente.setText(getIntent().getStringExtra("paramApellidosCliente"));
            txtTelefonoCliente.setText(getIntent().getStringExtra("paramTelefonoCliente"));


        }
    }
    private void asignarReferencias(){
        txtTituloCliente = findViewById(R.id.txtTituloCliente);
        txtDniCliente= findViewById(R.id.txtDniCliente);
        txtNombresCliente = findViewById(R.id.txtNombresCliente);
        txtApellidosCliente = findViewById(R.id.txtApellidosCliente);
        txtTelefonoCliente = findViewById(R.id.txtTelefonoCliente);

        btnRegistrarCliente = findViewById(R.id.btnRegistrarCliente);

        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(capturarDatos()){
                    DAOCliente daoCliente = new DAOCliente(ClienteActivity.this);
                    daoCliente.abrirDB();
                    String mensaje;

                    if(registra){
                        mensaje = daoCliente.registrarCliente(cliente);
                    }else{
                        mensaje = daoCliente.modificarCliente(cliente);
                    }
                    AlertDialog.Builder ventana = new AlertDialog.Builder(ClienteActivity.this);
                    ventana.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Fragment  fragment = new ListarCategoriaFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();

                            fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                        }
                    });
                    ventana.create().show();
                }
            }
        });
    }


    private boolean capturarDatos(){
        boolean valida = true;
        String dni = txtDniCliente.getText().toString();
        String nombre = txtNombresCliente.getText().toString();
        String apellido = txtApellidosCliente.getText().toString();
        String celular = txtTelefonoCliente.getText().toString();


        if(nombre.equals("")){
            txtNombresCliente.setError("Nombre es obligatorio");
            valida = false;
        }
        if(apellido.equals("")){
            txtApellidosCliente.setError("Apellido es obligatorio");
            valida = false;
        }
        if(dni.equals("")){
            txtDniCliente.setError("DNI es obligatorio");
            valida = false;
        }
        if(celular.equals("")){
            txtTelefonoCliente.setError("Celular es obligatorio");
            valida = false;
        }


        if(valida){
            if(registra){
                cliente = new Cliente(dni, nombre,apellido , celular);
            }else{
                cliente = new Cliente(id,dni, nombre,apellido , celular);
            }
        }
        return valida;
    }

    public void onClick(View view) {
    }
}