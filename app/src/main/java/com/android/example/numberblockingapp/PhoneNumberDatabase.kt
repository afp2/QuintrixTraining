package com.android.example.numberblockingapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneNumber::class], version = 1, exportSchema = false)
abstract class PhoneNumberDatabase : RoomDatabase() {
    abstract val phoneNumberDao : PhoneNumberDao

    companion object {
        @Volatile
        private var INSTANCE : PhoneNumberDatabase? = null

        fun getInstance(context : Context) : PhoneNumberDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PhoneNumberDatabase::class.java,
                        "blocked_numbers_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}