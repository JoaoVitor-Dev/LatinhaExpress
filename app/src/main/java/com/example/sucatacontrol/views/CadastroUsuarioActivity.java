package com.example.sucatacontrol.views;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;
import com.example.sucatacontrol.R;
import com.example.sucatacontrol.dao.AllDao;
import com.example.sucatacontrol.database.MyDatabase;
import com.example.sucatacontrol.entities.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity
{
    private MyDatabase db;
    private AllDao allDao;
    private Button btnCadastrar;
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
    }

    private void setup()
    {
        btnCadastrar = findViewById(R.id.btnCadastrar);
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

        if (nome.equals(""))
        {
            Toast.makeText(this, "Por favor, informe o nome!", Toast.LENGTH_SHORT).show();
            edtNome.requestFocus();
            return;
        }

        if (senha.equals(""))
        {
            Toast.makeText(this, "Por favor, informe a senha!", Toast.LENGTH_SHORT).show();
            edtSenha.requestFocus();
            return;
        }

        Usuario usuario = new Usuario(nome, senha);

        allDao.insert_usuario(usuario);

        Toast.makeText(this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();

        Intent it = new Intent(CadastroUsuarioActivity.this, LoginActivity.class);

        it.putExtra("nome_usuario", nome);

        startActivity(it);

    }
}