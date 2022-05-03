package com.android.example.numberblockingapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context, factory : SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("Create Table " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BLOCKED_PHONE_NUMBERS_COL + " Text" + ")" )
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addPhoneNumber(phoneNumber : String) {
        val values = ContentValues()
        val db = this.writableDatabase
        values.put(BLOCKED_PHONE_NUMBERS_COL, phoneNumber)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun removePhoneNumber(phoneNumber: String){
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$BLOCKED_PHONE_NUMBERS_COL=?", arrayOf(phoneNumber))
    }

    fun getPhoneNumber(phoneNumber: String) : Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE $BLOCKED_PHONE_NUMBERS_COL=$phoneNumber", null)
    }

    fun getAllBlockedNumbers() : Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)
    }

    companion object {
        private val DATABASE_NAME = "BLOCKED_NUMBERS_DATABASE"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "blocked_numbers"
        val ID_COL = "id"
        val BLOCKED_PHONE_NUMBERS_COL = "blocked_phone_numbers"
    }

}