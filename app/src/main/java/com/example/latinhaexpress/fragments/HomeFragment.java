package com.example.latinhaexpress.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Caixa;
import com.example.latinhaexpress.entities.Usuario;

public class HomeFragment extends Fragment
{
    private ImageButton imgNovaColeta, imgNovaVenda;
    private MyDatabase db;
    private AllDao allDao;
    private TextView statuscaixa;
    private Long id_caixa;
    private Caixa caixa;
    public Usuario usuarioLogado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        setup(view);

        mostraStatusCaixa();

        imgNovaColeta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(caixa != null){
                    novaColeta();
                }
            }
        });

        imgNovaVenda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(caixa != null)
                {
                    novaVenda();
                }
            }
        });

        statuscaixa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(caixa == null)
                {
                    abrirCaixa();
                }else{
                    fecharCaixa();
                }
            }
        });

        return view;
    }

    private void novaColeta()
    {
        ColetaFragment coletaFragment = new ColetaFragment();

        coletaFragment.caixa_id = id_caixa;

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, coletaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void setup (View view)
    {
        imgNovaColeta = view.findViewById(R.id.imgNovaColeta);
        imgNovaVenda = view.findViewById(R.id.imgNovaVenda);
        statuscaixa = view.findViewById(R.id.statuscaixa);

        TextView text_toolbar = view.findViewById(R.id.text_toolbar);
        text_toolbar.setText("Latinha Express");

        ImageButton imgvoltar = view.findViewById(R.id.btnVoltar);


        Context appContext = getContext();

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void novaVenda()
    {
        VendaFragment vendaFragment = new VendaFragment();

        vendaFragment.caixa_id = id_caixa;

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, vendaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void mostraStatusCaixa()
    {
        Caixa caixa = allDao.get_caixa_aberto();

        if(caixa == null)
        {
            statuscaixa.setText("Abrir Caixa");
        }else
        {
            statuscaixa.setText("Fechar Caixa");
        }
    }

    private void abrirCaixa()
    {
        caixa = new Caixa();

        caixa.caixa_aberto = true;

        caixa.usuario_id = usuarioLogado.usuario_id;

        caixa.caixa_id = allDao.insert_caixa(caixa);

        statuscaixa.setText("Fechar Caixa");
    }

    private void fecharCaixa()
    {
        caixa.caixa_aberto = false;

        allDao.update_caixa(caixa);

        caixa = null;

        statuscaixa.setText("Abrir Caixa");
    }


}