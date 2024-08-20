package com.example.latinhaexpress.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "_usuario")
public class Usuario
{
    @PrimaryKey
    @ColumnInfo(name = "usuario_id")
    public Long usuario_id;

    @ColumnInfo(name = "usuario_nome")
    public String usuario_nome;

    @ColumnInfo(name = "usuario_senha")
    public String usuario_senha;

    public Usuario(){}

    @Ignore
    public Usuario(String usuario_nome, String usuario_senha)
    {
        this.usuario_nome = usuario_nome;
        this.usuario_senha = usuario_senha;
    }
}
