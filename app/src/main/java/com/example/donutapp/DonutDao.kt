package com.example.donutapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DonutDao {
    @Insert
    suspend fun insert(donut : Donut)

    @Update
    suspend fun update(donut : Donut)

    @Delete
    suspend fun delete(donut : Donut)

    @Query("SELECT * FROM donut_table WHERE Id= :thisId")
    fun get(thisId : Long) : LiveData<Donut>

    @Query("SELECT * FROM donut_table ORDER BY Id DESC")
    fun getAll() : LiveData<List<Donut>>

    @Query("SELECT * FROM donut_table ORDER BY Id DESC LIMIT 1")
    fun getLast() : LiveData<Donut>
}