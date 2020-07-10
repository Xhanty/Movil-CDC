package com.admin.cdc.movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.admin.cdc.movil.Utils.UtilsNetwork;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Validaciones
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=\\w*\\d)(?=\\w*[a-zA-Z0-9])\\S{6,16}$");

    //Progress Dialog
    ProgressDialog progressDialog;

    //VARIABLES QUE VAMOS A REGISTRAR
    private String correo = "";
    private String contrasena = "";

    EditText email, clave;
    Button login, recuperarclave;

    //Login en Firebase
    FirebaseAuth mAuth;

    boolean cerrar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.username);
        clave = findViewById(R.id.password);

        recuperarclave = findViewById(R.id.forgot_password);
        login = findViewById(R.id.btn_login);

        recuperarclave.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    //VALIDACIONES
    private boolean validateEmail(){
        correo = email.getText().toString().trim();
        if(correo.isEmpty()){
            email.setError("Escribe un E-mail");
            return false;

        } else if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
            email.setError("Escribe un E-mail válido");
            return false;

        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validateclave(){
        contrasena = clave.getText().toString().trim();
        if(contrasena.isEmpty()){
            clave.setError("Ingresa una clave");
            return false;

        } else if(!PASSWORD_PATTERN.matcher(contrasena).matches()){
            clave.setError("La clave debe tener mínimo 6 dígitos y un número");
            return false;

        } else {
            clave.setError(null);
            return true;
        }
    }


    @Override
    public void onClick(View v) {
        if(v == login){
            if(!validateEmail() | !validateclave()){
                return;
            }

            if(UtilsNetwork.isOnline(LoginActivity.this)){
                //Abrimos el progressDialog
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.show();

                //Contenido
                progressDialog.setContentView(R.layout.progress_dialog);

                //Transparente
                progressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent
                );

                //Ejecutamos la acción
                ingresarsesion();

            } else {
                Toast.makeText(LoginActivity.this, "No tienes conexión a internet", Toast.LENGTH_SHORT).show();
            }


        } else if (v == recuperarclave){
            startActivity(new Intent(LoginActivity.this, ClaveActivity.class));
        }
    }

    private void ingresarsesion() {
        try {
            correo = email.getText().toString().trim();
            contrasena = clave.getText().toString().trim();

            mAuth.signInWithEmailAndPassword(correo, contrasena).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        email.setText("");
                        clave.setText("");
                        Toast.makeText(LoginActivity.this, "Sesión iniciada correctamente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Correo y/o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                        clave.setText("");
                        progressDialog.dismiss();
                    }
                }
            });
        } catch (Exception e){
            Toast.makeText(LoginActivity.this, "A ocurrido un error, intenta más tarde", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Alerta");
        builder.setMessage("¿Estás seguro de salir de la aplicación?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrar = true;
                salirApp(cerrar);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cerrar = false;
                salirApp(cerrar);
            }
        });

        builder.create();
        builder.show();
    }

    public void salirApp(boolean cerrar){
        if(cerrar == true){
            Toast.makeText(this, "Regresa pronto!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}