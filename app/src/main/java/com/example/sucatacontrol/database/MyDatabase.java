package com.example.sucatacontrol.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.sucatacontrol.dao.AllDao;
import com.example.sucatacontrol.entities.*;

@Database(entities = {Sobre.class, Usuario.class, Coleta.class, Venda.class, Caixa.class},version = 1)
public abstract class MyDatabase extends RoomDatabase
{
    public abstract AllDao allDao();
}
