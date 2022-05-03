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
        var incomingPhoneNumber : String = call.handle.toString().removePrefix("tel:")
        Toast.makeText(applicationContext, incomingPhoneNumber, Toast.LENGTH_LONG).show()
        if(isBlocked(incomingPhoneNumber)){
            Toast.makeText(applicationContext, "$incomingPhoneNumber is blocked", Toast.LENGTH_LONG).show()
            var response = CallResponse.Builder()
            response.apply {
                setRejectCall(true)
                setDisallowCall(true)
                setSkipCallLog(true)
            }
            respondToCall(call, response.build())

        }
        else{
            Toast.makeText(applicationContext, "$incomingPhoneNumber is not blocked", Toast.LENGTH_LONG).show()
            return
        }
    }


    fun isBlocked(phoneNumber : String) : Boolean {
        if (phoneNumber == "6505551212"){
            return true
        }
        else{
            return false
        }


    }


}