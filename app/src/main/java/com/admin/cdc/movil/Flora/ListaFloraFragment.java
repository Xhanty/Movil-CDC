package com.admin.cdc.movil.Flora;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.cdc.movil.Models.Flora;
import com.admin.cdc.movil.R;

import java.util.ArrayList;
import java.util.List;

public class ListaFloraFragment extends Fragment {

    List<Flora> lstBook;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_flora, container, false);

        lstBook = new ArrayList<>();
        lstBook.add(new Flora("Flor 1","Categorie Book","Description book",R.drawable.logo));
        lstBook.add(new Flora("Flor 2","Categorie Book","Description book",R.drawable.forestales));
        lstBook.add(new Flora("Flor 3","Categorie Book","Description book",R.drawable.flora));
        lstBook.add(new Flora("Flor 4","Categorie Book","Description book",R.drawable.fauna));
        lstBook.add(new Flora("Flor 5","Categorie Book","Description book",R.drawable.social));
        lstBook.add(new Flora("Flor 6","Categorie Book","Description book",R.drawable.aire));
        lstBook.add(new Flora("Flor 7","Categorie Book","Description book",R.drawable.suelo));
        lstBook.add(new Flora("Flor 8","Categorie Book","Description book",R.drawable.forestales));

        RecyclerView myrv = (RecyclerView) view.findViewById(R.id.recyclerview_flora_id);
        AdapterFlora myAdapter = new AdapterFlora(getContext(), lstBook);
        myrv.setLayoutManager(new GridLayoutManager(getContext(),3));
        myrv.setAdapter(myAdapter);

        return view;
    }
}