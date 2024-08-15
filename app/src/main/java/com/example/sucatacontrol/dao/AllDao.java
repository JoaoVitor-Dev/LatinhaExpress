package com.example.sucatacontrol.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.sucatacontrol.entities.Sobre;
import com.example.sucatacontrol.entities.Usuario;

@Dao
public interface AllDao
{
    @Insert
    Long insert_sobre(Sobre s);

    @Insert
    Long insert_usuario(Usuario u);

    // @Insert
    //Long insert_coleta(Coleta c);

    //@Insert
    //Long insert_venda(Venda v);

    //@Insert
    //Long insert_caixa(Caixa c);

    @Query("SELECT  * FROM _SOBRE")
    Sobre getSobre();
    
}
