package com.example.latinhaexpress.entities;

import androidx.room.*;

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

    public Venda(){}

    @Ignore
    public Venda(String venda_recicladora_nome, Long caixa_id, Double venda_qtde, Double venda_preco_total, Double venda_qtde_co2)
    {
        this.venda_recicladora_nome = venda_recicladora_nome;
        this.caixa_id = caixa_id;
        this.venda_qtde = venda_qtde;
        this.venda_preco_total = venda_preco_total;
        this.venda_qtde_co2 = venda_qtde_co2;
    }
}

