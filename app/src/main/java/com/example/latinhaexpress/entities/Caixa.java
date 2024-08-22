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

    @ColumnInfo(name = "caixa_status")
    public String caixa_status;

    @ColumnInfo(name = "usuario_id")
    public Long usuario_id;

    @ColumnInfo(name = "caixa_saldo")
    public Double caixa_saldo;

    public Caixa(){}

    @Ignore
    public Caixa(String caixa_status, Long usuario_id, Double caixa_saldo)
    {
        this.caixa_status = caixa_status;
        this.usuario_id = usuario_id;
        this.caixa_saldo = caixa_saldo;
    }
}
