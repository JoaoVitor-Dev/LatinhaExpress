package com.example.latinhaexpress.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "_venda", foreignKeys = {
        @ForeignKey(
                entity = Caixa.class,
                parentColumns = {"caixa_id"},
                childColumns = {"caixa_id"},
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
}
)
public class Venda
{
    @PrimaryKey
    @ColumnInfo(name = "venda_id")
    public Long venda_id;
    @ColumnInfo(name = "venda_recicladora_nome")
    public String venda_recicladora_nome;

    @ColumnInfo(name = "caixa_id")
    public Long caixa_id;

    @ColumnInfo(name = "venda_qtde")
    public Double venda_qtde;

    @ColumnInfo(name = "venda_preco_total")
    public Double venda_preco_total;

    @ColumnInfo(name = "venda_qtde_co2")
    public Double venda_qtde_co2;
}

