package com.android.example.numberblockingapp

import android.telecom.Call
import android.telecom.CallScreeningService
import android.widget.Toast

class CallScreeningServiceImpl : CallScreeningService() {


    //Removed all the Toasts from the app just to make it a little it cleaner.
    //Also, in a real call blocking app, you would not want to be notified every time a blocked
    //number called you
    override fun onScreenCall(call : Call.Details) {
        //Toast.makeText(applicationContext, "Call received", Toast.LENGTH_LONG).show()
        val incomingPhoneNumber : String = call.handle.toString().removePrefix("tel:")
        //Toast.makeText(applicationContext, incomingPhoneNumber, Toast.LENGTH_LONG).show()
        if(isBlocked(incomingPhoneNumber)){
            //Toast.makeText(applicationContext, "$incomingPhoneNumber is blocked", Toast.LENGTH_LONG).show()
            val response = CallResponse.Builder()
            response.apply {
                setRejectCall(true)
                setDisallowCall(true)
                setSkipCallLog(true)
            }
            respondToCall(call, response.build())

        }
        else{
            //Toast.makeText(applicationContext, "$incomingPhoneNumber is not blocked", Toast.LENGTH_LONG).show()
            val response = CallResponse.Builder()
            response.apply {
                setRejectCall(false)
                setDisallowCall(false)
                setSkipCallLog(false)
            }
            respondToCall(call, response.build())
        }
    }


    fun isBlocked(phoneNumber : String) : Boolean {
        val db = DBHelper(applicationContext, null)
        val cursor = db.getPhoneNumber(phoneNumber)
        //if number exists in database, the cursor count will be at least 1
        return cursor.count > 0
    }


}