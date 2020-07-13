package com.admin.cdc.movil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.admin.cdc.movil.Aire.ListaAireFragment;
import com.admin.cdc.movil.Fauna.ListaFaunaFragment;
import com.admin.cdc.movil.Flora.ListaFloraFragment;
import com.admin.cdc.movil.Forestales.ListaForesFragment;
import com.admin.cdc.movil.Social.ListaSocialFragment;
import com.admin.cdc.movil.Suelo.ListaSueloFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private FirebaseAuth mAuth;
    boolean cerrar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        Fragment newFragment = new HomeMenuFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedormenu, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            //OPCIONES INVENTARIOS INVENTARIOS
            case R.id.inventario_flora:
                Fragment newFragment = new ListaFloraFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedormenu, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case R.id.inventario_fauna:
                Fragment newFragment1 = new ListaFaunaFragment();
                FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                transaction1.replace(R.id.contenedormenu, newFragment1);
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;

            case R.id.inventario_suelo:
                Fragment newFragment2 = new ListaSueloFragment();
                FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                transaction2.replace(R.id.contenedormenu, newFragment2);
                transaction2.addToBackStack(null);
                transaction2.commit();
                break;

            case R.id.inventario_aire:
                Fragment newFragment3 = new ListaAireFragment();
                FragmentTransaction transaction3 = getSupportFragmentManager().beginTransaction();
                transaction3.replace(R.id.contenedormenu, newFragment3);
                transaction3.addToBackStack(null);
                transaction3.commit();
                break;

            case R.id.inventario_forestales:
                Fragment newFragment4 = new ListaForesFragment();
                FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                transaction4.replace(R.id.contenedormenu, newFragment4);
                transaction4.addToBackStack(null);
                transaction4.commit();
                break;

            case R.id.inventario_social:
                Fragment newFragment5 = new ListaSocialFragment();
                FragmentTransaction transaction5 = getSupportFragmentManager().beginTransaction();
                transaction5.replace(R.id.contenedormenu, newFragment5);
                transaction5.addToBackStack(null);
                transaction5.commit();
                break;


            //CERRAR SESÓN
            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);

                builder.setTitle("Alerta");
                builder.setMessage("¿Deseas cerrar la sesión?");

                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MenuActivity.this, "Sesión cerrada!", Toast.LENGTH_SHORT).show();
                        mAuth.signOut();

                        Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                builder.create();
                builder.show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void salirApp(boolean cerrar){
        if(cerrar == true){
            Toast.makeText(this, "Regresa pronto!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}