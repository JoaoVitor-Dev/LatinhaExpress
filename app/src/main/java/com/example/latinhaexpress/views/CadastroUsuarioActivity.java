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

public class CadastroUsuarioActivity extends AppCompatActivity
{
    private MyDatabase db;
    private AllDao allDao;
    private Button btnCadastrar, btnCancelar;
    private EditText edtNome, edtSenha;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro_usuario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();

        btnCadastrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cadastrarUsuario();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(CadastroUsuarioActivity.this, LoginActivity.class));
            }
        });
    }

    private void setup()
    {
        TextView text_toolbar = findViewById(R.id.text_toolbar);
        text_toolbar.setText("Cadastro de usuário");

        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        edtNome = findViewById(R.id.edtNome);
        edtSenha = findViewById(R.id.edtSenha);

        db = Room.databaseBuilder(this, MyDatabase.class, "mydb")
                .allowMainThreadQueries()
                .build();

        allDao = db.allDao();
    }

    private void cadastrarUsuario()
    {
        String nome = edtNome.getText().toString().trim().toLowerCase();
        String senha = edtSenha.getText().toString().trim().toLowerCase();

        if(nome.isEmpty())
        {
            Toast.makeText(this, "Por favor, informe o nome!", Toast.LENGTH_SHORT).show();
            edtNome.requestFocus();
        } else if (senha.isEmpty()) {
            Toast.makeText(this, "Por favor, informe a senha!", Toast.LENGTH_SHORT).show();
            edtSenha.requestFocus();
        }else
        {
            Usuario usuario = allDao.usuario_por_nome(nome.trim().toLowerCase());

            if (usuario == null)
            {
                usuario = new Usuario(nome, senha);

                allDao.insert_usuario(usuario);

                Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();

                Intent it = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);

                it.putExtra("nome_usuario", nome);

                startActivity(it);
            }else
            {
                Toast.makeText(this, "Já existe usuário cadastrado com este Login!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}