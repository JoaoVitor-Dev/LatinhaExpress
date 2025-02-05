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

    @Query("SELECT * FROM _caixa WHERE caixa_aberto = true AND usuario_id = :id_usuario")
    Caixa get_caixa_aberto(Long id_usuario);

    @Update
    void update_caixa(Caixa caixa);

    @Query("SELECT  * FROM _SOBRE")
    Sobre getSobre();

    @Query("SELECT  * FROM _USUARIO")
    List<Usuario> usuarios();

    @Query("SELECT * FROM _usuario WHERE usuario_nome = :nome")
    Usuario usuario_por_nome(String nome);

    @Query("SELECT * FROM _coleta WHERE caixa_id = :id")
    List<Coleta> total_coletas(Long id);

    @Query("SELECT * FROM _venda WHERE caixa_id = :id")
    List<Venda> total_vendas(Long id);

    @Query("SELECT * FROM _caixa WHERE caixa_aberto = false")
    List<Caixa> caixas();

    @Query("SELECT * FROM _venda")
    List<Venda> vendas();

    @Query("SELECT * FROM _coleta")
    List<Coleta> coletas();
}
