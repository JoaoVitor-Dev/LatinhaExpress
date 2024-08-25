package com.example.latinhaexpress.entities;

import androidx.room.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity(
        tableName = "_caixa", foreignKeys = {
        @ForeignKey(
                entity = Usuario.class,
                parentColumns = {"usuario_id"},
                childColumns = {"usuario_id"},
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        )
}
)
public class Caixa
{
    @PrimaryKey
    @ColumnInfo(name = "caixa_id")
    public Long caixa_id;

    @ColumnInfo(name = "caixa_aberto")
    public Boolean caixa_aberto;

    @ColumnInfo(name = "usuario_id")
    public Long usuario_id;

    @ColumnInfo(name = "caixa_coletas")
    public Double caixa_coletas;

    @ColumnInfo(name = "caixa_vendas")
    public Double caixa_vendas;

    @ColumnInfo(name = "caixa_co2_reduzido")
    public Double caixa_co2_reduzido;

    public Caixa(){}

    @Ignore
    public Caixa(Boolean caixa_aberto, Long usuario_id, Double caixa_coletas, Double caixa_vendas, Double caixa_co2_reduzido)
    {
        this.caixa_aberto = caixa_aberto;
        this.usuario_id = usuario_id;
        this.caixa_coletas = caixa_coletas;
        this.caixa_vendas = caixa_vendas;
        this.caixa_co2_reduzido = caixa_co2_reduzido;
    }
}
