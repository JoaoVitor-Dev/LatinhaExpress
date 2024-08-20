package com.example.latinhaexpress.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "_caixa")
public class Caixa
{
    @PrimaryKey
    @ColumnInfo(name = "caixa_id")
    public Long caixa_id;

    @ColumnInfo(name = "caixa_saldo")
    public Double caixa_saldo;
}
