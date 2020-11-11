package com.admin.cdc.movil.Forestales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.cdc.movil.R;
import com.admin.cdc.movil.Utils.UtilsNetwork;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DetalleFloraActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;
    private String ID;

    TextView nombretxt, idtxt, familiatxt, especietxt;
    TextView alturatotaltxt, alturacomercialtxt;
    TextView cap1txt, cap2txt, cap3txt, cap4txt;
    ImageView imagentxt;

    //Progress Dialog
    ProgressDialog progressDialog;

    private int IDCC;

    FloatingActionsMenu grupoBotones;
    FloatingActionButton fabEditar, fabDetalles, fabPdf;

    private String[]headerFlora = {"#", "Nombre", "Familia", "Especie", "Altura_Total", "Altura_Comercial"};
    private FloraPDF floraPDF;


    private Date fecha = new Date();
    ArrayList<String[]> flora = new ArrayList<>();
    SimpleDateFormat formatFecha = new SimpleDateFormat("dd/MM/YYYY");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_flora);

        floraPDF = new FloraPDF(this);
        ID = getIntent().getStringExtra("ID");
        IDCC = Integer.parseInt(ID);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        idtxt = findViewById(R.id.id_regional_detalle);
        nombretxt = findViewById(R.id.nombre_regional_detalle);
        imagentxt = findViewById(R.id.imagen_regional_detalle);
        familiatxt = findViewById(R.id.familia_fores_detalle);
        especietxt = findViewById(R.id.especie_fores_detalle);
        alturatotaltxt = findViewById(R.id.alturatotal_fores_detalle);
        alturacomercialtxt = findViewById(R.id.alturacomercial_fores_detalle);

        cap1txt = findViewById(R.id.CAP_1_fores_detalle);
        cap2txt = findViewById(R.id.CAP_2_fores_detalle);
        cap3txt = findViewById(R.id.CAP_3_fores_detalle);
        cap4txt = findViewById(R.id.CAP_4_fores_detalle);

        grupoBotones = findViewById(R.id.grupoFab_Fores);
        fabPdf = findViewById(R.id.pdf_fores);
        fabDetalles = findViewById(R.id.edit_fores);
        fabEditar = findViewById(R.id.detalle_fores);

        fabPdf.setOnClickListener(this);
        fabDetalles.setOnClickListener(this);
        fabEditar.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(UtilsNetwork.isOnline(this)) {
            //Abrimos el progressDialog
            progressDialog = new ProgressDialog(this);
            progressDialog.show();

            //Contenido
            progressDialog.setContentView(R.layout.progress_dialog);

            //Transparente
            progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);

            mDatabase.child("Forestales").child("Inventario").child(ID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String nombre_regional = dataSnapshot.child("Nombre_Regional").getValue().toString();
                        String numero_campo = dataSnapshot.child("Numero_Campo").getValue().toString();
                        String imagen = dataSnapshot.child("Imagen").getValue().toString();
                        String familia = dataSnapshot.child("Especie").getValue().toString();
                        String especie = dataSnapshot.child("Familia").getValue().toString();

                        String altura_total = dataSnapshot.child("Altura_Total").getValue().toString();
                        String altura_comercial = dataSnapshot.child("Altura_Comercial").getValue().toString();

                        String cap1 = dataSnapshot.child("CAP_1").getValue().toString();
                        String cap2 = dataSnapshot.child("CAP_2").getValue().toString();
                        String cap3 = dataSnapshot.child("CAP_3").getValue().toString();
                        String cap4 = dataSnapshot.child("CAP_4").getValue().toString();
                        String cap5 = dataSnapshot.child("CAP_5").getValue().toString();

                        idtxt.setText(numero_campo);
                        nombretxt.setText(nombre_regional);
                        familiatxt.setText(familia);
                        especietxt.setText(especie);
                        alturatotaltxt.setText(altura_total + " M");
                        alturacomercialtxt.setText(altura_comercial + " M");

                        cap1txt.setText(cap1 + " CM");
                        cap2txt.setText(cap2 + " CM");
                        cap3txt.setText(cap3 + " CM");
                        cap4txt.setText(cap4 + " CM");
                        Picasso.with(getApplicationContext()).load(imagen).into(imagentxt);

                        progressDialog.dismiss();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "A ocurrido en error, intentalo más tarde", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "A ocurrido en error, intentalo más tarde", Toast.LENGTH_LONG).show();
                }
            });

        } else{
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "NO WIFI", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if(v == fabPdf){
            Toast.makeText(this, "PDF", Toast.LENGTH_SHORT).show();
            grupoBotones.collapse();

        } else if(v == fabDetalles){
            Toast.makeText(this, "DETALLES", Toast.LENGTH_SHORT).show();
            grupoBotones.collapse();

        } else {
            Toast.makeText(this, "EDITAR", Toast.LENGTH_SHORT).show();
            grupoBotones.collapse();

        }
    }
}