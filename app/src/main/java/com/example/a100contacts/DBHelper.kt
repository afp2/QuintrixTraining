package com.example.a100contacts

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context, factory : SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("Create Table " + TABLE_NAME + " (" + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " Text," + LAST_NAME_COL + " Text,"
                + COMPANY_NAME + " Text," + ADDRESS + " Text,"
                + CITY + " Text," + COUNTY + " Text," + STATE + " Text,"
                + ZIP + " Text," + PHONE1 + " Text," + PHONE + " Text,"
                + EMAIL + " Text" + ")")
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addContact(str : List<String>) {
        val values = ContentValues()
        val db = this.writableDatabase
        values.put(FIRST_NAME_COL, str[0])
        values.put(LAST_NAME_COL, str[1])
        values.put(COMPANY_NAME, str[2])
        values.put(ADDRESS, str[3])
        values.put(CITY, str[4])
        values.put(STATE, str[5])
        values.put(COUNTY, str[6])
        values.put(ZIP, str[7])
        values.put(PHONE1, str[8])
        values.put(PHONE, str[9])
        values.put(EMAIL, str[10])
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getContact(i : Int) : Cursor? {
        if (i in 1..100) {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE $ID_COL=$i", null)
        }
        else {
            return null
        }
    }


    companion object{
        private val DATABASE_NAME = "100CONTACTS"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "contacts_table"
        val ID_COL = "id"
        val FIRST_NAME_COL = "first_name"
        val LAST_NAME_COL = "last_name"
        val COMPANY_NAME = "company_name"
        val ADDRESS = "address"
        val CITY = "city"
        val STATE = "state"
        val COUNTY = "county"
        val ZIP = "zip"
        val PHONE1 = "phone1"
        val PHONE = "phone"
        val EMAIL = "email"

    }
}