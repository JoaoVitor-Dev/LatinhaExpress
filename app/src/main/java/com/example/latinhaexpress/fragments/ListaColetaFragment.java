package com.example.latinhaexpress.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.adapters.ColetaAdapter;
import com.example.latinhaexpress.adapters.VendaAdapter;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Coleta;

import java.util.List;

public class ListaColetaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;
    private ColetaAdapter adapter;
    private Context appContext;
    private RecyclerView rcListColetas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_lista_coleta, container, false);

        setup(view);

        carregaListaColetas();

        return view;
    }

    private void setup(View view)
    {
        appContext = getContext();

        rcListColetas = view.findViewById(R.id.rcListaColetas);

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void carregaListaColetas()
    {
        List<Coleta> coletas = allDao.coletas();

        adapter = new ColetaAdapter(coletas, appContext);

        rcListColetas.setAdapter(adapter);

        rcListColetas.setLayoutManager(new LinearLayoutManager(appContext));
    }
}