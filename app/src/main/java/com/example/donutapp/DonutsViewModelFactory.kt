package com.example.donutapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DonutsViewModelFactory(private val dao : DonutDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DonutsViewModel::class.java)){
            return DonutsViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }

}