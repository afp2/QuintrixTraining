package com.android.example.numberblockingapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PhoneNumberDao {
    @Insert
    suspend fun insert(number : PhoneNumber)

    //don't think I'll ever need update

    @Delete
    suspend fun delete(number : PhoneNumber)

    @Query("SELECT * FROM blocked_numbers_table WHERE phone_number= :phoneNumber")
    fun get(phoneNumber : String) : LiveData<PhoneNumber>

    @Query("SELECT * FROM blocked_numbers_table")
    fun getAll() : LiveData<List<PhoneNumber>>
}