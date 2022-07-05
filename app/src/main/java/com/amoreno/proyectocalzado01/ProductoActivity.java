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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.grupal.proyectoNoelia.entidad.Producto;
import com.grupal.proyectoNoelia.modelo.DAOProducto;

public class ProductoActivity extends AppCompatActivity {
    TextInputEditText txtcodProducto, txtNombProducto, txtColorProducto, txtStockProducto, txtTallaProducto, txtPrecioProducto;
    Button btnGuardarProducto;
    ImageView imagenProducto;
    TextView txtTituloProducto;
    Producto producto;

    boolean registra=true;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        asignarReferencias();
        recuperarDatos();

    }

    private void recuperarDatos(){
        if(getIntent().hasExtra("paramIdProducto")){
            txtTituloProducto.setText("MODIFICAR DATOS ");
            btnGuardarProducto.setText("MODIFICAR");
            registra = false;
            id= Integer.parseInt(getIntent().getStringExtra("paramIdProducto"));
            txtcodProducto.setText(getIntent().getStringExtra("paramCodProducto"));
            txtNombProducto.setText(getIntent().getStringExtra("paramNombProducto"));
            txtColorProducto.setText(getIntent().getStringExtra("paramColorProducto"));
            txtStockProducto.setText(getIntent().getStringExtra("paramStockProducto"));
            txtTallaProducto.setText(getIntent().getStringExtra("paramTallaProducto"));
            txtPrecioProducto.setText(getIntent().getStringExtra("paramPrecioProducto"));

        }
    }

    private void asignarReferencias(){

        txtcodProducto=findViewById(R.id.txtCodProducto);
        txtNombProducto=findViewById(R.id.txtNombProducto);
        txtColorProducto=findViewById(R.id.txtColorProducto);
        txtStockProducto=findViewById(R.id.txtStockProducto);
        txtTallaProducto=findViewById(R.id.txtTallaProducto);
        txtPrecioProducto=findViewById(R.id.txtPrecioProducto);

        btnGuardarProducto = findViewById(R.id.btnRegistrarProducto);
        txtTituloProducto = findViewById(R.id.txtTituloProducto);

        btnGuardarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(capturarDatos()){
                    DAOProducto daoProducto = new DAOProducto(ProductoActivity.this);
                    daoProducto.abrirBD();
                    String mensaje;

                    if(registra) {
                        mensaje = daoProducto.registrarProducto(producto);
                    }else{
                        mensaje = daoProducto.editarProducto(producto);
                    }
                    AlertDialog.Builder ventana = new AlertDialog.Builder(ProductoActivity.this);
                    ventana.setTitle(Html.fromHtml("<font color='#275A7C'> MENSAJE INFORMATIVO "+ "</font>"));
                    ventana.setMessage(mensaje);
                    ventana.setPositiveButton(Html.fromHtml("<font color='#086155'> ACEPTAR "+ "</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // mostrarFragmento(new ListarProductoFragment());


                            Fragment fragment = new ListarProductoFragment();
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
        String codigo = txtcodProducto.getText().toString();
        String nombre = txtNombProducto.getText().toString();
        String color = txtColorProducto.getText().toString();
        String  stock = txtStockProducto.getText().toString();
        String  talla = txtTallaProducto.getText().toString();
        String  precio = txtPrecioProducto.getText().toString();


        if(codigo.equals("")){
            txtcodProducto.setError("Código es obligatorio");
            valida = false;
        }

        if(nombre.equals("")){
            txtNombProducto.setError("Nombre es obligatorio");
            valida = false;
        }

        if(color.equals("")){
            txtColorProducto.setError("Color es obligatorio");
            valida = false;
        }
        if(stock.equals("")){
            txtStockProducto.setError("Stock es obligatorio");
            valida = false;
        }
        if(talla.equals("")){
            txtTallaProducto.setError("Talla es obligatorio");
            valida = false;
        }
        if(precio.equals("")){
            txtPrecioProducto.setError("Precio es obligatorio");
            valida = false;
        }


        if (valida){
            if(registra) {
                producto = new Producto(codigo, nombre, color, Integer.parseInt(stock), Integer.parseInt(talla), Double.parseDouble(precio));
            }else{
                producto = new Producto(id, codigo, nombre, color, Integer.parseInt(stock), Integer.parseInt(talla), Double.parseDouble(precio));
            }
        }

        return valida;
    }
}