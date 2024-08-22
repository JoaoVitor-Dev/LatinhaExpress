package com.example.latinhaexpress.views;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import com.example.latinhaexpress.R;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.database.MyDatabase;
import com.example.latinhaexpress.entities.Usuario;

public class LoginActivity extends AppCompatActivity
{
    private Button btnAcessar;
    private EditText edtNome, edtSenha;
    private TextView textViewCadastrar;
    private MyDatabase db;
    private AllDao allDao;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();

        btnAcessar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                logar();
            }
        });

        textViewCadastrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                abrirTelaDeCadastro();
            }
        });
    }

    private void setup()
    {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Login");

        btnAcessar = findViewById(R.id.btnAcessar);
        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);
        textViewCadastrar = findViewById(R.id.textViewCadastrar);

        db = Room.databaseBuilder(this, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void logar()
    {
        String nome = edtNome.getText().toString().trim().toLowerCase();
        String senha = edtSenha.getText().toString().trim().toLowerCase();

        if(nome.equals(""))
        {
            Toast.makeText(this, "Por favor, informe o nome!", Toast.LENGTH_SHORT).show();
            edtNome.requestFocus();
            return;
        }

        if(senha.equals(""))
        {
            Toast.makeText(this, "Por favor, informe o nome!", Toast.LENGTH_SHORT).show();
            edtSenha.requestFocus();
            return;
        }

        Usuario usuario = allDao.usuario_por_nome(nome);

        if(usuario != null)
        {
            if(usuario.usuario_senha.equals(senha))
            {
                Intent it = new Intent(LoginActivity.this, MenuActivity.class);
                it.putExtra("usuario", usuario);
                startActivity(it);
            }else
            {
                Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this, "Usuário ou senha incorretos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirTelaDeCadastro()
    {
        startActivity(new Intent(LoginActivity.this, CadastroUsuarioActivity.class));
    }
}