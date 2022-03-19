package com.example.a100contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import org.w3c.dom.Text
import java.io.File
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    var editText : EditText? = null
    var textView : TextView? = null
    var fetchButton : Button? = null
    var alreadyFetched : Boolean = false
    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.edit)
        textView = findViewById(R.id.text_view)
        fetchButton = findViewById(R.id.fetch)
        //messed up when creating database first time so had to delete it and recreate it
        //this.applicationContext.deleteDatabase(DBHelper.DATABASE_NAME)
    }


    //android studio told me I needed this line
    @SuppressLint("Range")
    @RequiresApi(Build.VERSION_CODES.R)
    fun fetch(view : View){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
            ),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )
        //permission granted for read and write but not for manage
        //permission is always denied for some reason, even after I am ExternalStorageManager
        /*
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
        }
         */
        //not sure why I need this here but not in the ReadWrite app
        //app doesn't work at all w/out it
        //the app always crashes in the background once when you first install it as it starts this
        //activity below. Once you accept the permissions from this activity it should never crash
        //again (from what I have tested)
        if(!Environment.isExternalStorageManager()){
            try {
                var i = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                startActivity(i)
            }
            catch (e : Exception){
                Toast.makeText(this, "Couldn't start activity", Toast.LENGTH_LONG).show()
            }
        }
        if (! alreadyFetched){
            //this probably appends to the database once per launch when fetch is first called
            //not sure how to only create database once (need some sort of persistent variable)
            //could probably tie database creation to checking if we have read write permission
            //b/c once permission is given we will always have it until uninstall
            val db = DBHelper(this, null)
            val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(folder, "100-contacts.csv")
            //line below is where it crashes
            //says open failed: EACCES (Permission denied)
            /*
            val lines = file.readLines()
            for (line in lines){
                val str = line.split(",")
                db.addContact(str)
            }
             */
            //crashes at line below for same reason as above
            val bufferedReader = file.bufferedReader()
            var line = bufferedReader.readLine()
            while (line != null){
                //was a bug w/ company name b/c some of them had a comma which caused split to not work
                //as intended. this ignores commas if they are in quotes
                val str = line.split(",(?=(?:[^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*\$)".toRegex())
                db.addContact(str)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()

            alreadyFetched = true
        }
        val i = editText!!.text.toString().toInt()
        val db = DBHelper(this, null)
        var c : Cursor? = db.getContact(i)
        if (c == null){
            textView!!.setText("Contact Not Found")
        }
        else{
            c.moveToFirst()
            textView!!.setText("")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.FIRST_NAME_COL)) + " ")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.LAST_NAME_COL))  + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.COMPANY_NAME))  + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.ADDRESS))  + " ")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.CITY)) + " ")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.STATE))  + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.COUNTY))  + " ")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.ZIP))  + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.PHONE1)) + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.PHONE)) + "\n")
            textView!!.append(c.getString(c.getColumnIndex(DBHelper.EMAIL)) + "\n")
            c.close()
        }
    }

}