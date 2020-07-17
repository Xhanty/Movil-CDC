package com.admin.cdc.movil.Forestales;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.cdc.movil.Models.Forestales;
import com.admin.cdc.movil.R;
import com.admin.cdc.movil.Utils.UtilsNetwork;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListaForesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView mRutasList;
    private DatabaseReference mDatabase;
    ProgressDialog progressDialog;
    FirebaseRecyclerOptions<Forestales> options;
    FirebaseRecyclerAdapter<Forestales, ForestalesViewHolder> adapter;
    ImageButton buscar;
    Spinner spinnerBusqueda;
    private String codigo;
    EditText txt_codigo;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_fores, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference("Forestales");
        mDatabase.keepSynced(true);

        spinnerBusqueda = view.findViewById(R.id.select_flora);
        buscar = view.findViewById(R.id.filtro_fores);
        spinnerBusqueda.setVisibility(View.GONE);

        mRutasList = (RecyclerView) view.findViewById(R.id.recy_forestales);
        mRutasList.setHasFixedSize(true);
        mRutasList.setLayoutManager(new LinearLayoutManager(getContext()));

        buscar.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(UtilsNetwork.isOnline(getContext())) {
            //Abrimos el progressDialog
            progressDialog = new ProgressDialog(getContext());
            progressDialog.show();

            //Contenido
            progressDialog.setContentView(R.layout.progress_dialog);

            //Transparente
            progressDialog.getWindow().setBackgroundDrawableResource(
                    android.R.color.transparent);

            listForestales();

        } else {

            //DESCARGAR BASE DE DATOS PARA UTILIZARLA SIN INTERNET
        }
    }

    @Override
    public void onClick(View v) {
        if(v == buscar){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

            builder.setTitle("Selecciona una opción");
            builder.setIcon(R.drawable.ic_buscar);

            final CharSequence[] opciones = new CharSequence[3];
            opciones[0] = "Buscar por código";
            opciones[1] = "Buscar por especie";
            opciones[2] = "Buscar por familia";

            builder.setItems(opciones, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (opciones[which] == opciones[0]){
                        //CÓDIGO
                        busqueda(0);

                    } else if (opciones[which] == opciones[1]){
                        //ESPECIE
                        busqueda(1);

                    } else {
                        //FAMILIA
                        busqueda(2);
                    }
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void busqueda(final int opcion){
        if (opcion == 0){

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.adapter_dialog, null);
            builder.setView(view)
                    .setTitle("Buscar por código")
                    .setIcon(R.drawable.ic_buscar)
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .setPositiveButton("Buscar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            codigo = txt_codigo.getText().toString();
                            Toast.makeText(getContext(), "Código ingresado: " + codigo, Toast.LENGTH_SHORT).show();
                            spinnerBusqueda.setVisibility(View.GONE);
                        }
                    });
            txt_codigo = view.findViewById(R.id.txt_codigo);
            builder.show();

        } else if (opcion == 1){
            llenarspinnerespecie();

        } else {
            llenarspinnerfamilia();
        }
    }

    private void llenarspinnerespecie() {
        mDatabase.child("Especies").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for (DataSnapshot item: dataSnapshot.getChildren()) {
                    arrayList.add(item.child("Nombre").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, arrayList);
                spinnerBusqueda.setAdapter(arrayAdapter);
                spinnerBusqueda.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void llenarspinnerfamilia() {
        mDatabase.child("Familias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();

                for (DataSnapshot item: dataSnapshot.getChildren()) {
                    arrayList.add(item.child("Nombre").getValue(String.class));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), R.layout.adapter_spinner, arrayList);
                spinnerBusqueda.setAdapter(arrayAdapter);
                spinnerBusqueda.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void listForestales() {

        options = new FirebaseRecyclerOptions.Builder<Forestales>().setQuery(mDatabase.child("Inventario"), Forestales.class).build();

        adapter = new FirebaseRecyclerAdapter<Forestales, ForestalesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(ForestalesViewHolder forestalesViewHolder, final int i, Forestales forestales) {

                forestalesViewHolder.setNombre(String.valueOf(forestales.getNombre_Regional()));
                forestalesViewHolder.setIdentificador(String.valueOf(forestales.getNumero_Campo()));
                forestalesViewHolder.setImage(getContext(), String.valueOf(forestales.getImagen()));

                progressDialog.dismiss();

                forestalesViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), DetalleFloraActivity.class);
                        intent.putExtra("ID", getRef(i).getKey());
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public ForestalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.forestales_rows, parent, false);
                return new ForestalesViewHolder(v);
            }

        };
        adapter.startListening();
        mRutasList.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRutasList.setAdapter(adapter);
    }

    public static class ForestalesViewHolder extends RecyclerView.ViewHolder {
        View mView;
        public ForestalesViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }

        public void setNombre(String nombre){
            TextView nombre_post = (TextView)mView.findViewById(R.id.forestales_title_id);
            nombre_post.setText(nombre);
        }

        public void setIdentificador(String identificador){
            TextView dificultad_post = (TextView)mView.findViewById(R.id.forestales_id);
            dificultad_post.setText(identificador);
        }

        public void setImage(Context ctx, String imagen){
            ImageView image_post = (ImageView)mView.findViewById(R.id.forestales_img_id);
            Picasso.with(ctx).load(imagen).into(image_post);
        }
    }
}