package com.example.latinhaexpress.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.latinhaexpress.entities.*;

import java.util.List;

@Dao
public interface AllDao
{
    @Insert
    Long insert_sobre(Sobre s);

    @Insert
    Long insert_usuario(Usuario u);

     @Insert
    Long insert_coleta(Coleta c);

    @Insert
    Long insert_venda(Venda v);

    @Insert
    Long insert_caixa(Caixa c);

    @Query("SELECT * FROM _caixa WHERE caixa_aberto = true")
    Caixa get_caixa_aberto();

    @Update
    void update_caixa(Caixa caixa);

    @Query("SELECT  * FROM _SOBRE")
    Sobre getSobre();

    @Query("SELECT  * FROM _USUARIO")
    List<Usuario> usuarios();

    @Query("SELECT * FROM _usuario WHERE usuario_nome = :nome")
    Usuario usuario_por_nome(String nome);
    
}
