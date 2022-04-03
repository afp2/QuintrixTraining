package com.example.npsapiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.npsapiapp.api.NPSFetcher
import com.example.npsapiapp.api.NewsResponse

class NewsViewModel : ViewModel() {
    val news : LiveData<NewsResponse>
    init {
        news = NPSFetcher().getNews()
    }
}