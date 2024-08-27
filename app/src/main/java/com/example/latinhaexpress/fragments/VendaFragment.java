package com.example.latinhaexpress.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.dialog.MyDialog;
import com.example.latinhaexpress.entities.Caixa;
import com.example.latinhaexpress.entities.Venda;


public class VendaFragment extends Fragment
{
    private Button btnVender, btnCancelar;
    private EditText edtValor, edtQtde, edtNome;
    private Context appContext;
    private MyDatabase db;
    private AllDao allDao;
    public Long caixa_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_venda, container, false);

        setup(view);

        btnVender.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                realizarVenda();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getParentFragmentManager();
                MyDialog dialog = MyDialog.newInstance("Aviso", "Deseja cancelar a Venda?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        cancelarVenda();
                    }
                });
                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Toast.makeText(appContext, "Operação cancelada!", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show(fragmentManager, "MyDialog");
            }
        });

        return view;
    }

    private void setup(View view)
    {
        appContext = getContext();

        btnVender = view.findViewById(R.id.btnVender);
        edtNome = view.findViewById(R.id.edtNome);
        edtQtde = view.findViewById(R.id.edtQtde);
        edtValor = view.findViewById(R.id.edtValor);
        btnCancelar = view.findViewById(R.id.btnCancelarVenda);

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void realizarVenda()
    {
        String nome = edtNome.getText().toString().trim().toUpperCase();
        String qtde = edtQtde.getText().toString();
        String valor = edtValor.getText().toString();

        if(nome.isEmpty())
        {
            Toast.makeText(appContext, "Por favor, informe a reciladora!", Toast.LENGTH_SHORT).show();
            edtNome.requestFocus();
        } else if (qtde.isEmpty())
        {
            Toast.makeText(appContext, "Por favor, informe a quantidade!", Toast.LENGTH_SHORT).show();
            edtQtde.requestFocus();
        } else if (valor.isEmpty())
        {
            Toast.makeText(appContext, "Por favor, informe o Valor!", Toast.LENGTH_SHORT).show();
            edtQtde.requestFocus();
        } else
        {
            Venda venda = new Venda();

            venda.venda_recicladora_nome = nome;
            venda.venda_qtde = Double.parseDouble(qtde);
            venda.venda_preco_total = Double.parseDouble(valor);

            venda.caixa_id = caixa_id;

            allDao.insert_venda(venda);

            Toast.makeText(appContext, "Venda realizada com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cancelarVenda()
    {
        HomeFragment homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, homeFragment);

        fragmentTransaction.addToBackStack(null);

        Toast.makeText(appContext, "Venda cancelada com sucesso!", Toast.LENGTH_SHORT).show();

        fragmentTransaction.commit();
    }
}