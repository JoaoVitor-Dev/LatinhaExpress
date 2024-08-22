package com.example.latinhaexpress.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "_coleta", foreignKeys = {
        @ForeignKey(
                entity = Caixa.class,
                parentColumns = {"caixa_id"},
                childColumns = {"caixa_id"},
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
}
)
public class Coleta
{
    @PrimaryKey
    @ColumnInfo(name = "coleta_id")
    public Long coleta_id;

    @ColumnInfo(name = "caixa_id")
    public Long caixa_id;

    @ColumnInfo(name = "coleta_vendedor_nome")
    public String coleta_vendedor_nome;

    @ColumnInfo(name = "coleta_qtde")
    public Double coleta_qtde;

    @ColumnInfo(name = "coleta_preco")
    public Double coleta_preco;

    @ColumnInfo(name = "coleta_obs")
    public Double coleta_obs;
}
