package com.example.donutapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import  androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DonutsViewModel(val dao : DonutDao) : ViewModel() {
    var newDonutString = ""
    var newDonutNum = 0
    private val donuts : LiveData<List<Donut>> = dao.getAll()
    private val lastDonut : LiveData<Donut> = dao.getLast()
    //used for testing
    val donutString = Transformations.map(donuts) {
        d -> formatDonuts(d)
    }
    //why does this work but
    //val lastDonutString = convertToString(lastDonut)
    //doesn't work
    val lastDonutString = Transformations.map(lastDonut) {
        d -> convertToString(d)
    }

    fun formatDonuts(donuts : List<Donut>) : String {
        return donuts.fold("") {
            str, d -> str + '\n' + convertToString(d)
        }
    }

    fun convertToString(donut : Donut?) : String{
        if(donut == null){
            return ""
        }
        else{
            return "Number of donuts eaten: ${donut.numDonuts}"
        }
    }

    fun addDonut(){
        if(newDonutString.isNotEmpty()) {
            viewModelScope.launch {
                val donut = Donut()
                //probably crashes if newDonutString is empty
                //not sure if the way I implemented the check is good practice or not
                donut.numDonuts = newDonutString.toInt()
                dao.insert(donut)
            }
        }
        else{
            viewModelScope.launch {
                val donut = Donut()
                donut.numDonuts = 0
                dao.insert(donut)
            }
        }
    }
}