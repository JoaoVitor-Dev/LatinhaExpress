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
import com.example.latinhaexpress.entities.Coleta;
import com.example.latinhaexpress.views.MenuActivity;

public class ColetaFragment extends Fragment
{
    private MyDatabase db;
    private AllDao allDao;
    private EditText edtNome, edtQtde, edtValor, edtObs;
    private Button btnComprar, btnCancelarColeta;
    private Context appContext;
    public Long caixa_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_coleta, container, false);

        setup(view);

        btnComprar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                realizarCompra();
            }
        });

        btnCancelarColeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getParentFragmentManager();
                MyDialog dialog = MyDialog.newInstance("Aviso", "Deseja cancelar a Coleta?");

                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        cancelarColeta();
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
        edtNome = view.findViewById(R.id.edtNome);
        edtQtde = view.findViewById(R.id.edtQtde);
        edtValor = view.findViewById(R.id.edtValor);
        edtObs = view.findViewById(R.id.edtObs);

        btnComprar = view.findViewById(R.id.btnComprar);
        btnCancelarColeta = view.findViewById(R.id.btnCancelarColeta);

        db = Room.databaseBuilder(appContext, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void realizarCompra()
    {
       String nome = edtNome.getText().toString().trim().toUpperCase();
       String qtde = edtQtde.getText().toString();
       String valor = edtValor.getText().toString();
       String obs = edtObs.getText().toString();

       if(nome.isEmpty())
       {
           Toast.makeText(appContext, "Por favor, informe o nome!", Toast.LENGTH_SHORT).show();
       } else if (qtde.isEmpty())
       {
           Toast.makeText(appContext, "Por favor, informe a quantidade!", Toast.LENGTH_SHORT).show();
       } else if (valor.isEmpty())
       {
           Toast.makeText(appContext, "Por favor, informe o Preço!", Toast.LENGTH_SHORT).show();
       }else
       {
           Coleta coleta = new Coleta();

           coleta.caixa_id = caixa_id;
           coleta.coleta_vendedor_nome = nome;
           coleta.coleta_qtde = Double.parseDouble(qtde);
           coleta.coleta_preco = Double.parseDouble(valor);

           allDao.insert_coleta(coleta);

           Toast.makeText(appContext, "Compra realizada!", Toast.LENGTH_SHORT).show();
       }
    }

    private void cancelarColeta()
    {
        HomeFragment homeFragment = new HomeFragment();

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, homeFragment);

        fragmentTransaction.addToBackStack(null);

        Toast.makeText(appContext, "Coleta cancelada com sucesso!", Toast.LENGTH_SHORT).show();

        fragmentTransaction.commit();
    }
}