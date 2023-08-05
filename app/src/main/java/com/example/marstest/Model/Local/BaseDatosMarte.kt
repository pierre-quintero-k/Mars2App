package com.example.marstest.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marstest.Model.Remoto.TerrenoDeMArte

@Database(entities = [TerrenoDeMArte::class],version=1)
abstract class MarsDataBase : RoomDatabase(){


    abstract fun getMarsDao(): MarsDao

    companion object {
        @Volatile
        private var INSTANCE: MarsDataBase? = null

        fun getDataBase(context: Context): MarsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarsDataBase::class.java,
                    "mars_db_1"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }




}