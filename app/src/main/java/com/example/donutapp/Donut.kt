package com.example.donutapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donut_table")
data class Donut(
    @PrimaryKey(autoGenerate = true)
    var Id : Long = 0,

    @ColumnInfo(name = "num_donuts")
    var numDonuts : Int = 0
)