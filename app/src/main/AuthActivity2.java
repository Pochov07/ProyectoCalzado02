package com.grupal.proyectoNoelia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class AuthActivity2 extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText textEmail;
    private TextInputEditText textPassword;
    private Button btnRegistrar;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    //declaracion de objeto firebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth2);

        //inicializamos el objeto
        mAuth = FirebaseAuth.getInstance();

        //referenciamo las vistas
        textEmail   =   (TextInputEditText) findViewById(R.id.emailEditText);
        textPassword=   (TextInputEditText) findViewById(R.id.passwordEditText);
        btnRegistrar=   (Button) findViewById(R.id.signButton);
        btnLogin    =   (Button) findViewById(R.id.loginButton);

        progressDialog  =new ProgressDialog(this);

        //asociamos al evento para que escuche
        btnRegistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    private void registrarUsuario()
    {
        String email = textEmail.getText().toString().trim();
        String password=textPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Se debe ingresar el email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Se debe ingresar el password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Creando nuevo usuario
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(AuthActivity2.this, "Se ha registrado el Email", Toast.LENGTH_SHORT).show();

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(AuthActivity2.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AuthActivity2.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }

                        progressDialog.dismiss();
                    }
                });
    }

    private void loguearUsuario()
    {

        String email = textEmail.getText().toString().trim();
        String password=textPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Se debe ingresar el email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Se debe ingresar el password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Logeando usuario
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(AuthActivity2.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(),MainActivity.class);//llamando al Homeactivity
                            intent.putExtra(MainActivity.user, email);
                            startActivity(intent);


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(AuthActivity2.this, "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(AuthActivity2.this, "No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                            }
                        }

                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signButton: registrarUsuario();break;
            case R.id.loginButton: loguearUsuario();
        }

    }
}