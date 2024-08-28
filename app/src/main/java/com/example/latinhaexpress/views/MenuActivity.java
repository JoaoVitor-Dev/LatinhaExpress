package com.example.latinhaexpress.views;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.entities.Usuario;
import com.example.latinhaexpress.fragments.HomeFragment;
import com.example.latinhaexpress.fragments.ListVendaFragment;
import com.example.latinhaexpress.fragments.ListaCaixaFragment;
import com.example.latinhaexpress.fragments.ListaColetaFragment;

public class MenuActivity extends AppCompatActivity
{
    private ImageButton imgNovaColeta;
    private Usuario usuarioLogado;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        montaToolbar();

        Intent it = getIntent();

        usuarioLogado = (Usuario) getIntent().getSerializableExtra("usuario");

        if (savedInstanceState == null)
        {
            fragmentHome();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    private void montaToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("Latinha Express");
        toolbar.setTitleTextColor(Color.WHITE);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getTitle().equals("Serviços"))
        {
            servicos();
        } else if (item.getTitle().equals("Caixas"))
        {
            fragmentListaCaixas();
        } else if (item.getTitle().equals("Coletas"))
        {
            fragmentListColetas();
        } else if (item.getTitle().equals("Vendas"))
        {
            fragmentListVendas();
        }

        return true;
    }

    private void servicos()
    {
        HomeFragment homeFragment = new HomeFragment();

        homeFragment.usuarioLogado = usuarioLogado;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, homeFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void fragmentHome()
    {
        HomeFragment homeFragment = new HomeFragment();

        homeFragment.usuarioLogado = usuarioLogado;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, homeFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void fragmentListaCaixas()
    {
        ListaCaixaFragment listaCaixaFragment = new ListaCaixaFragment();

        listaCaixaFragment.usuario = usuarioLogado;

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, listaCaixaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void fragmentListVendas()
    {
        ListVendaFragment listVendaFragment = new ListVendaFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, listVendaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    private void fragmentListColetas()
    {
        ListaColetaFragment listaColetaFragment = new ListaColetaFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, listaColetaFragment);

        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();

    }
}