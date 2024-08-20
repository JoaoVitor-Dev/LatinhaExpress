package com.example.latinhaexpress.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "_sobre")
public class Sobre
{
    @PrimaryKey
    @ColumnInfo(name = "sobre_id")
    public Long id;

    @ColumnInfo(name = "sobre_descricao")
    public String descricao;

    @ColumnInfo(name = "sobre_recursos")
    public String recursos;
}
