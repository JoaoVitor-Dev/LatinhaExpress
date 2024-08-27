package com.example.latinhaexpress.entities;

import androidx.room.*;

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
    public String coleta_obs;

    public Coleta(){}

    @Ignore
    public Coleta(Long caixa_id, String coleta_vendedor_nome, Double coleta_qtde, Double coleta_preco, String coleta_obs)
    {
        this.caixa_id = caixa_id;
        this.coleta_vendedor_nome = coleta_vendedor_nome;
        this.coleta_qtde = coleta_qtde;
        this.coleta_preco = coleta_preco;
        this.coleta_obs = coleta_obs;
    }
}
