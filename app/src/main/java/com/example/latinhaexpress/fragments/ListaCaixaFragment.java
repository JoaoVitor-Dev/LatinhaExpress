package com.example.latinhaexpress.fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.adapters.CaixaAdapter;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Caixa;
import com.example.latinhaexpress.entities.Usuario;

import java.util.List;


public class ListaCaixaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;
    private CaixaAdapter caixaAdapter;
    public Usuario usuario;
    private Context appContext;
    private RecyclerView rcListCaixas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_lista_caixa, container, false);

        setup(view);

        carregaListaCaixas();

        return view;
    }

    private void carregaListaCaixas()
    {
        List<Caixa> caixaList = allDao.caixas();

        if(!caixaList.isEmpty())
        {
            caixaAdapter = new CaixaAdapter(caixaList, getContext());

            rcListCaixas.setAdapter(caixaAdapter);
        }else {
            Toast.makeText(getContext(), "Nenhum caixa encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setup(View view)
    {
        rcListCaixas = view.findViewById(R.id.rcListaCaixas);
        rcListCaixas.setLayoutManager(new LinearLayoutManager(getContext()));

        appContext = getContext();

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }
}