package com.example.donutapp

import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database

@Database(entities = [Donut::class], version = 3, exportSchema = false)
abstract class DonutDatabase : RoomDatabase() {
    abstract val donutDao : DonutDao

    companion object {
        @Volatile
        private var INSTANCE : DonutDatabase? = null

        fun getInstance(context : Context) : DonutDatabase {
            synchronized(this){}
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DonutDatabase::class.java,
                    "donuts_database"
                ).build()
                INSTANCE = instance
            }
            return instance
        }
    }
}