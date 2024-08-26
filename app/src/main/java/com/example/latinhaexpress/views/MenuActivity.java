package com.example.latinhaexpress.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.entities.Usuario;
import com.example.latinhaexpress.fragments.HomeFragment;

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
           HomeFragment homeFragment = new HomeFragment();

           homeFragment.usuarioLogado = usuarioLogado;

           FragmentManager fragmentManager = getSupportFragmentManager();
           FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

           fragmentTransaction.add(R.id.fragment_container, homeFragment);
           fragmentTransaction.commit();
        }
    }

    private void montaToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Latinha Express");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getTitle().equals("Principal"))
        {

        } else if (item.getTitle().equals("Caixas"))
        {

        } else if (item.getTitle().equals("Coletas"))
        {

        } else if (item.getTitle().equals("Vendas"))
        {

        } else if (item.getTitle().equals("Sair"))
        {
            finishAffinity();
        }

        return true;
    }
}