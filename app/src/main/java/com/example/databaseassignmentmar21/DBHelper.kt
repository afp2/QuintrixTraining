package com.example.databaseassignmentmar21

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.Contacts

class DBHelper(context : Context, factory : SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val q = ("Create Table " + TABLE_NAME + " (" + UID_COL + " INTEGER PRIMARY KEY, "
                + FIRST_NAME_COL + " Text," + LAST_NAME_COL + " Text,"
                + REWARDS_COL + " Text" + ")")
        db.execSQL(q)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    //should update if it exists already, otherwise just add it
    fun addEntry(UID : Int, fName : String, lName : String, reward : String) {
        val values = ContentValues()
        val dbw = this.writableDatabase
        values.put(UID_COL, UID)
        values.put(FIRST_NAME_COL, fName)
        values.put(LAST_NAME_COL, lName)
        values.put(REWARDS_COL, reward)
        if(getEntry(UID) != null){
            removeEntry(UID)
        }
        dbw.insert(TABLE_NAME, null, values)
        dbw.close()
    }

    fun getEntry(i : Int) : Cursor? {
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE $UID_COL=$i", null)
        if(c.moveToFirst()){
            return c
        }
        else{
            return null
        }
    }

    //not sure why androidStudio wanted this line but it removes a warning
    //call getEntry so we can check if it already exists or if user is try to delete something
    //that does not exist in the database. If it does exist, we delete it otherwise we inform user
    //that no entry with that UID exists in the table
    @SuppressLint("Recycle")
    fun removeEntry(i : Int) : Int{
        val db = this.writableDatabase
        val c = getEntry(i)
        if(c == null){
            return 0
        }
        else{
            db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE $UID_COL=$i")
            return 1
        }

    }

   companion object{
       private val DATABASE_NAME = "ASSIGNMENTMAR21"
       private val DATABASE_VERSION = 1
       val TABLE_NAME = "assignment_table"
       val UID_COL = "uid"
       val FIRST_NAME_COL = "first_name"
       val LAST_NAME_COL = "last_name"
       val REWARDS_COL = "rewards"
   }
}