package com.android.example.numberblockingapp

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
    private lateinit var viewModel : PhoneNumberViewModel
    val REQUEST_ID = 1

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
        val dao = PhoneNumberDatabase.getInstance(this).phoneNumberDao
        val viewModelFactory = PhoneNumberViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PhoneNumberViewModel::class.java)

        blockNumberButton?.setOnClickListener {
            Toast.makeText(this, "Blocked Number", Toast.LENGTH_LONG).show()
            viewModel.newPhoneNumberToBlockOrUnblock = phoneNumberEditText?.text.toString()
            Toast.makeText(this, "${phoneNumberEditText?.text.toString()}", Toast.LENGTH_LONG).show()
            viewModel.addPhoneNumberToBlockList()
        }

        unblockNumberButton?.setOnClickListener {
            Toast.makeText(this, "Unblocked Number", Toast.LENGTH_LONG).show()
            viewModel.newPhoneNumberToBlockOrUnblock = phoneNumberEditText?.text.toString()
            Toast.makeText(this, "${phoneNumberEditText?.text.toString()}", Toast.LENGTH_LONG).show()
            viewModel.removePhoneNumberFromBlockList()
        }

        showBlockedNumbersButton?.setOnClickListener {
            Toast.makeText(this, "Showing blocked numbers", Toast.LENGTH_LONG).show()
            val blockedNumbers = viewModel.blockedNumbers.value.toString()
            Toast.makeText(this, blockedNumbers, Toast.LENGTH_LONG).show()
            blockedNumbersTextView?.text = blockedNumbers
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
        /*
        var roleManager : RoleManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getSystemService(ROLE_SERVICE) as RoleManager
        } else {
            return 1
        }
        var i : Intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            //Intent(this, CallScreeningServiceImpl::class.java)
        } else {
            return 1
        }
        //maybe use non deprecated version once I figure this out
        startActivityForResult(intent, REQUEST_ID)
        return 0
         */
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //maybe delete super call b/c it is not in starter code
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_ID){
            if(resultCode == android.app.Activity.RESULT_OK){
                Toast.makeText(this, "requestCode is ok", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this, "requestCode is not ok", Toast.LENGTH_LONG).show()
            }
        }

    }

}