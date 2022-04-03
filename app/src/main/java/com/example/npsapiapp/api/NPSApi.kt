package com.example.npsapiapp.api

import retrofit2.Call
import retrofit2.http.GET
import java.io.FileInputStream
import java.util.*


interface NPSApi {

    @GET("parks?parkCode=&api_key=$apiKey")
    fun getParks() : Call<ParksResponse>

    @GET("newsreleases?api_key=$apiKey")
    fun getNews() : Call<NewsResponse>

    @GET("campgrounds?api_key=$apiKey")
    fun getCampgrounds() : Call<CampgroundResponse>

}

