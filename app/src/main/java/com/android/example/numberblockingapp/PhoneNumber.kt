package com.android.example.numberblockingapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blocked_numbers_table")
data class PhoneNumber(
    @PrimaryKey(autoGenerate = true)
    var id : Long = 0L,

    //not sure if I need the initialization here or not
    @ColumnInfo(name = "phone_number")
    var phoneNumber : String = ""
)
