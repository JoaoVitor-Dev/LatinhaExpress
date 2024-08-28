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
import com.example.latinhaexpress.entities.Coleta;
import com.example.latinhaexpress.entities.Usuario;
import com.example.latinhaexpress.entities.Venda;

import java.util.List;

public class HomeFragment extends Fragment
{
    private ImageButton imgNovaColeta, imgNovaVenda;
    private MyDatabase db;
    private AllDao allDao;
    private TextView statuscaixa, totalColetas, totalVendas, totalCo2Reduzido, saldo;
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

        setaValoresCaixa();

        return view;
    }

    private void novaColeta()
    {
        ColetaFragment coletaFragment = new ColetaFragment();

        coletaFragment.caixa_id = caixa.caixa_id;

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
        totalVendas = view.findViewById(R.id.totalVendas);
        totalCo2Reduzido = view.findViewById(R.id.totalCo2Reduzido);
        saldo = view.findViewById(R.id.saldo);

        Context appContext = getContext();

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void novaVenda()
    {
        VendaFragment vendaFragment = new VendaFragment();

        vendaFragment.caixa_id = caixa.caixa_id;

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
            Double total_coletas = 0.0;
            Double total_vendas = 0.0;
            Double total_co2 = 0.0;

            List<Coleta> coletas = allDao.total_coletas(caixa.caixa_id);
            List<Venda> vendas = allDao.total_vendas(caixa.caixa_id);

            for(Coleta coleta : coletas)
            {
                total_coletas += coleta.coleta_preco * coleta.coleta_qtde;
            }

            for (Venda venda : vendas)
            {
                total_vendas += venda.venda_preco_total * venda.venda_qtde;
                total_co2 += venda.venda_qtde_co2;
            }

            caixa.caixa_coletas = total_coletas;
            caixa.caixa_vendas = total_vendas;
            caixa.caixa_co2_reduzido = total_co2;

            totalVendas.setText(String.format("%.2f", caixa.caixa_vendas));

            totalColetas.setText(String.format("%.2f", caixa.caixa_coletas));
            totalCo2Reduzido.setText(String.format("%.2f", caixa.caixa_co2_reduzido));
            saldo.setText(String.format("%.2f",caixa.caixa_vendas - caixa.caixa_coletas));
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

        limpaValoresCaixa();

        Toast.makeText(getContext(), "Caixa fechado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    private void limpaValoresCaixa()
    {
        totalVendas.setText(String.format("%.2f", 0.0));
        totalColetas.setText(String.format("%.2f", 0.0));
        totalVendas.setText(String.format("%.2f", 0.0));
        totalCo2Reduzido.setText(String.format("%.2f", 0.0));
        saldo.setText(String.format("%.2f", 0.0));
    }


}