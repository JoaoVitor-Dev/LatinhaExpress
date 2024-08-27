package com.example.latinhaexpress.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.view.*;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.dialog.MyDialog;
import com.example.latinhaexpress.entities.Caixa;
import com.example.latinhaexpress.entities.Usuario;

public class HomeFragment extends Fragment
{
    private ImageButton imgNovaColeta, imgNovaVenda;
    private MyDatabase db;
    private AllDao allDao;
    private TextView statuscaixa, totalColetas;
    private Long id_caixa;
    private Caixa caixa;
    public Usuario usuarioLogado;
    private Button btnCaixa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        btnCaixa.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(caixa == null)
                {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    MyDialog dialog = MyDialog.newInstance("Aviso", "Deseja abrir um novo Caixa?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            abrirCaixa();
                        }
                    });
                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(getContext(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.show(fragmentManager, "MyDialog");

                }else
                {
                    FragmentManager fragmentManager = getParentFragmentManager();
                    MyDialog dialog = MyDialog.newInstance("Aviso", "Deseja realmente fechar o Caixa?");

                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            fecharCaixa();
                        }
                    });

                    dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(getContext(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialog.show(fragmentManager, "MyDialog");
                }
            }
        });

        //setaValoresCaixa();

        return view;
    }

    private void novaColeta()
    {
        ColetaFragment coletaFragment = new ColetaFragment();

        coletaFragment.caixa_id = id_caixa;

        coletaFragment.usuarioLogado = usuarioLogado;

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
        btnCaixa = view.findViewById(R.id.btnCaixa);
        totalColetas = view.findViewById(R.id.totalColetas);

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

        vendaFragment.usuarioLogado = usuarioLogado;

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, vendaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void mostraStatusCaixa()
    {
        caixa = allDao.get_caixa_aberto();

        if(caixa == null)
        {
            statuscaixa.setText("Caixa fechado");
            statuscaixa.setTextColor(Color.RED);
            btnCaixa.setText("Abrir caixa");
        }else
        {
            statuscaixa.setText("Caixa aberto");
            statuscaixa.setTextColor(Color.GREEN);
            btnCaixa.setText("Fechar caixa");
        }
    }

    private void setaValoresCaixa()
    {
        if(caixa != null)
        {
            caixa.caixa_coletas = allDao.total_coletas(caixa.caixa_id);

            //totalColetas.setText(Double.toString(caixa.caixa_coletas));
        }
    }

    private void abrirCaixa()
    {
        caixa = new Caixa();

        caixa.caixa_aberto = true;

        caixa.usuario_id = usuarioLogado.usuario_id;

        caixa.caixa_id = allDao.insert_caixa(caixa);

        statuscaixa.setText("Caixa aberto");
        statuscaixa.setTextColor(Color.GREEN);

        btnCaixa.setText("Fechar caixa");

        Toast.makeText(getContext(), "Caixa aberto com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void fecharCaixa()
    {
        caixa.caixa_aberto = false;

        allDao.update_caixa(caixa);

        caixa = null;

        statuscaixa.setText("Caixa fechado");

        statuscaixa.setTextColor(Color.RED);

        btnCaixa.setText("Abrir caixa");

        Toast.makeText(getContext(), "Caixa fechado com sucesso!", Toast.LENGTH_SHORT).show();
    }


}