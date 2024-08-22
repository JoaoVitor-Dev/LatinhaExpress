package com.example.latinhaexpress.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.latinhaexpress.dao.AllDao;
import com.example.latinhaexpress.entities.*;

@Database(entities = {Sobre.class, Usuario.class, Coleta.class, Venda.class, Caixa.class},version = 2)
public abstract class MyDatabase extends RoomDatabase
{
    public abstract AllDao allDao();
}
