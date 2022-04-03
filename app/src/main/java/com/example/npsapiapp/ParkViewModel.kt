package com.example.npsapiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.npsapiapp.api.NPSFetcher
import com.example.npsapiapp.api.ParksResponse

class ParkViewModel : ViewModel() {
    val parks : LiveData<ParksResponse>
    init {
        parks = NPSFetcher().getParks()
    }
}