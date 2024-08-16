package com.example.sucatacontrol.views;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import com.example.sucatacontrol.R;
import com.example.sucatacontrol.dao.AllDao;
import com.example.sucatacontrol.database.MyDatabase;
import com.example.sucatacontrol.entities.Sobre;
import com.example.sucatacontrol.entities.Usuario;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private MyDatabase db;
    private AllDao allDao;
    private TextView descricao, recursos;
    private Button btnComecar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();
        cadastraInfoSobre();
        mostraSobre();

        btnComecar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                LoginOuCadastro();
            }
        });
    }

    private void setup()
    {
        db = Room.databaseBuilder(this, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();

        descricao = findViewById(R.id.texDescricao);
        recursos = findViewById(R.id.texRecursos);

        btnComecar = findViewById(R.id.btnComecar);
    }

    private void mostraSobre()
    {
        Sobre s = allDao.getSobre();

        descricao.setText(s.descricao);
        recursos.setText(s.recursos);
    }

    private void cadastraInfoSobre()
    {
        Sobre s = new Sobre();

        s.descricao = "O Sucata Control é um aplicativo de compra  e Venda de Aluminío (Latinhas), com controle de caixa e com recurso para calcular a redução de Carbono enviando toneladas de aluminio para reciclagem.";
        s.recursos = "É utilizado banco de dados SQLite para persistência de informações, componentes de UI como ActionBar, ProgressBar e CardView, além de Fragments e Activities. ";

        allDao.insert_sobre(s);
    }

    private void LoginOuCadastro()
    {
        List<Usuario> usuarioList = allDao.usuarios();

        if(usuarioList.size() > 0)
        {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }else
        {
            startActivity(new Intent(MainActivity.this, CadastroUsuarioActivity.class));
        }
    }
}