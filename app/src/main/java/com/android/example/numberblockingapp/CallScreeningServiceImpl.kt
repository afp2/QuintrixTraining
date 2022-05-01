package com.android.example.numberblockingapp

import android.telecom.Call
import android.telecom.CallScreeningService
import android.widget.Toast

class CallScreeningServiceImpl : CallScreeningService() {

    //implement room database to store numbers that have been blocked
    //could also just save to text file but that is probably not enough for
    //a final project

    override fun onScreenCall(call : Call.Details) {
        Toast.makeText(applicationContext, "Call received", Toast.LENGTH_LONG).show()
    }


}