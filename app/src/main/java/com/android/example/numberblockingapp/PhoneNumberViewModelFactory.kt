package com.android.example.numberblockingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PhoneNumberViewModelFactory(private val dao : PhoneNumberDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhoneNumberViewModel::class.java)) {
            return PhoneNumberViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }


}