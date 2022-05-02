package com.android.example.numberblockingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhoneNumberViewModel(val dao : PhoneNumberDao) : ViewModel() {

    var newPhoneNumberToBlockOrUnblock = ""
    val blockedNumbers = dao.getAll()

    fun addPhoneNumberToBlockList() {
        viewModelScope.launch {
            val phoneNumber = PhoneNumber()
            phoneNumber.phoneNumber = newPhoneNumberToBlockOrUnblock
            dao.insert(phoneNumber)
        }
    }

    fun removePhoneNumberFromBlockList() {
        viewModelScope.launch {
            val phoneNumber = PhoneNumber()
            phoneNumber.phoneNumber = newPhoneNumberToBlockOrUnblock
            dao.delete(phoneNumber)
        }
    }

}