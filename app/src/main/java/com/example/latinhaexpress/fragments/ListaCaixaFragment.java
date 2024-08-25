package com.example.latinhaexpress.fragments;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.adapters.CaixaAdapter;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Usuario;


public class ListaCaixaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;
    private CaixaAdapter caixaAdapter;
    private Usuario usuario;
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
        caixaAdapter = new CaixaAdapter(allDao.caixas(usuario.usuario_id), appContext);

        rcListCaixas.setAdapter(caixaAdapter);
    }

    private void setup(View view)
    {
        TextView text_toolbar = view.findViewById(R.id.text_toolbar);
        text_toolbar.setText("Lista de Caixas");
        rcListCaixas = view.findViewById(R.id.rcListaCaixas);

        appContext = getContext();

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }
}