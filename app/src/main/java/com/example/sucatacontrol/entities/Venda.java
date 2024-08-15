package com.example.sucatacontrol.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "_venda")
public class Venda
{
    @PrimaryKey
    @ColumnInfo(name = "venda_id")
    public Long venda_id;
    @ColumnInfo(name = "venda_recicladora_nome")
    public String venda_recicladora_nome;

    @ColumnInfo(name = "venda_qtde")
    public int venda_qtde;

    @ColumnInfo(name = "venda_preco_total")
    public Double venda_preco_total;
}

