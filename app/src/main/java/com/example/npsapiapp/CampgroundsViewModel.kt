package com.example.npsapiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.npsapiapp.api.CampgroundResponse
import com.example.npsapiapp.api.NPSFetcher

class CampgroundsViewModel : ViewModel() {
    val campgrounds : LiveData<CampgroundResponse>
    init {
        campgrounds = NPSFetcher().getCampgrounds()
    }
}