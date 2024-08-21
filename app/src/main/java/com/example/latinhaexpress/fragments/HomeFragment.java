package com.example.latinhaexpress.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.latinhaexpress.R;

public class HomeFragment extends Fragment
{

    private ImageButton imgNovaColeta, imgNovaVenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        setup(view);

        imgNovaColeta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              novaColeta();
            }
        });

        imgNovaVenda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                novaVenda();
            }
        });

        return view;
    }

    private void novaColeta()
    {
        ColetaFragment coletaFragment = new ColetaFragment();

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

        TextView text_toolbar = view.findViewById(R.id.text_toolbar);
        text_toolbar.setText("Latinha Express");

        ImageButton imgvolta = view.findViewById(R.id.btnVoltar);
        //imgvolta.setVisibility(ViewGroup.VISIBLE);


    }

    private void novaVenda()
    {
        VendaFragment vendaFragment = new VendaFragment();

        FragmentManager fragmentManager = getParentFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, vendaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }


}