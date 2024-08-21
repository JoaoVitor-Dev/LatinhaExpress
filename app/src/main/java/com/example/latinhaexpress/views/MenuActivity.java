package com.example.latinhaexpress.views;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.latinhaexpress.R;
import com.example.latinhaexpress.fragments.HomeFragment;

public class MenuActivity extends AppCompatActivity
{
    private ImageButton imgNovaColeta;
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

        //setup();

        if (savedInstanceState == null)
        {
           HomeFragment homeFragment = new HomeFragment();

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.fragment_container, homeFragment);
            fragmentTransaction.commit();
        }

    }

    private void setup()
    {
       imgNovaColeta = findViewById(R.id.imgNovaColeta);

       TextView text_toolbar = findViewById(R.id.text_toolbar);
       text_toolbar.setText("Latinha Express");
    }
}