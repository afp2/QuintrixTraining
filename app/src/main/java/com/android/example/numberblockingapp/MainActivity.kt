package com.android.example.numberblockingapp

import android.annotation.SuppressLint
import android.app.role.RoleManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    var phoneNumberEditText : EditText? = null
    var blockedNumbersTextView : TextView? = null
    var blockNumberButton : Button? = null
    var unblockNumberButton : Button? = null
    var showBlockedNumbersButton : Button? = null
    var db : DBHelper = DBHelper(this, null)

    val REQUEST_ID = 1

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneNumberEditText = findViewById(R.id.phoneNumberEditText)
        blockedNumbersTextView = findViewById(R.id.blockedNumbersTextView)
        blockNumberButton = findViewById(R.id.blockNumberButton)
        unblockNumberButton = findViewById(R.id.unblockNumberButton)
        showBlockedNumbersButton = findViewById(R.id.showBlockedNumbersButton)
        val errCode = requestRole()
        //Toast.makeText(this, "$errCode", Toast.LENGTH_LONG).show()

        //think this works


        blockNumberButton?.setOnClickListener {
            val phoneNumberToBlock = phoneNumberEditText?.text.toString()
            //Toast.makeText(this, "Blocked Number $phoneNumberToBlock", Toast.LENGTH_LONG).show()
            db.addPhoneNumber(phoneNumberToBlock)
        }

        unblockNumberButton?.setOnClickListener {
            val phoneNumberToUnblock = phoneNumberEditText?.text.toString()
            //Toast.makeText(this, "Unblocked Number $phoneNumberToUnblock", Toast.LENGTH_LONG).show()
            db.removePhoneNumber(phoneNumberToUnblock)
        }

        showBlockedNumbersButton?.setOnClickListener {
            //Toast.makeText(this, "Showing blocked numbers", Toast.LENGTH_LONG).show()
            var cursor = db.getAllBlockedNumbers()
            cursor.moveToFirst()
            blockedNumbersTextView?.text = ""
            do {
                blockedNumbersTextView?.append(cursor.getString(cursor.getColumnIndex(DBHelper.BLOCKED_PHONE_NUMBERS_COL)) + System.getProperty("line.separator"))
                blockedNumbersTextView?.append(System.getProperty("line.separator"))
            } while (cursor.moveToNext())
        }


    }


    //two functions below adapted from https://developer.android.com/reference/android/telecom/CallScreeningService
    //maybe add error code return so that way I can check that intent was correctly started (instead
    //of just returning)
    fun requestRole() : Int {
        //don't know why this worked but below didn't
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            startActivityForResult(intent, 1)
            return 0
        }
        return 1
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //maybe delete super call b/c it is not in starter code
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ID){
            if(resultCode == android.app.Activity.RESULT_OK){
                //Toast.makeText(this, "requestCode is ok", Toast.LENGTH_LONG).show()
            }
            else{
                //Toast.makeText(this, "requestCode is not ok", Toast.LENGTH_LONG).show()
            }
        }

    }

}