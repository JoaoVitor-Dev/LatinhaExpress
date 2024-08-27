package com.example.latinhaexpress.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.adapters.VendaAdapter;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;


public class ListVendaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;
    private VendaAdapter adapter;
    private Context appContext;
    private RecyclerView rcListVendas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_list_venda, container, false);

        setup(view);

        return view;
    }

    private void setup(View view)
    {
        rcListVendas = view.findViewById(R.id.rcListaVendas);

        appContext = getContext();

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void carregaListaVendas()
    {
        adapter = new VendaAdapter(allDao.vendas(), appContext);

        rcListVendas.setAdapter(adapter);
    }
}