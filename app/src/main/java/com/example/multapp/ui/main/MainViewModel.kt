package com.example.multapp.ui.main

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.multapp.MainActivity

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var val1 : MutableLiveData<String> = MutableLiveData()
    var val2 : MutableLiveData<String> = MutableLiveData()
    var result : MutableLiveData<String> = MutableLiveData()

    fun calculate(){
        val1.let { v1 ->
            //was having problems with null pointer exceptions so I added this check
            //it fixes it but it is not very readable...
            if(v1?.value == null) {
                result.value = "0"
            }
            else{
                if(!(v1.value!! == "")){
                    val2.let { v2 ->
                        //this null check is for the same reason as above
                        if(v2?.value == null){
                            result.value = "0"
                        }
                        else{
                            if(!(v2.value!! == "")){
                                result.value = v1.value!!.toInt().times(v2.value!!.toInt()).toString()
                            }
                            else{
                                result.value = "0"
                            }
                        }
                    }
                }
                else{
                    result.value = "0"
                }
            }

        }
    }
}