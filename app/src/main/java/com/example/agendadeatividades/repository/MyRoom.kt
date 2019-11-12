package com.example.agendadeatividades.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agendadeatividades.entity.Activity
import com.example.agendadeatividades.repository.dao.ActivityDao

// Classe com instância do banco de dados
@Database(entities = [Activity::class], version = 1)
abstract class MyRoom : RoomDatabase() {
    abstract fun getActivityDao(): ActivityDao

    companion object {
        var INSTANCE: MyRoom? = null

        fun getInstance(context: Context): MyRoom? {
            if (INSTANCE == null) {
                /*Apenas um encadeamento possa acessar o recurso em um determinado momento
                 Mesmo objeto podem ter apenas um encadeamento em execução por vez.
                 Todos os outros threads que tentam entrar no bloco sincronizado são bloqueados
                até que o thread dentro do bloco sincronizado saia do bloco.
                 Apenas um thread pode enviar uma mensagem de uma vez.*/
                synchronized(MyRoom::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoom::class.java, "arq.db"
                    )
                        .allowMainThreadQueries() //TODO: ALTERAR DEPOIS
                        .build()
                }
            }
            return INSTANCE
        }
    }
}