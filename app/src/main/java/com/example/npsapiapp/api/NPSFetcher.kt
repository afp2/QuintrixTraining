package com.example.npsapiapp.api

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.npsapiapp.api.NPSApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class NPSFetcher {
    private val npsApi : NPSApi
    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://developer.nps.gov/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        npsApi = retrofit.create(NPSApi::class.java)
    }

    fun getParks() : LiveData<ParksResponse> {
        val responseLiveData : MutableLiveData<ParksResponse> = MutableLiveData()
        val npsRequest : Call<ParksResponse> = npsApi.getParks()

        npsRequest.enqueue(object : Callback<ParksResponse> {
            override fun onFailure(call: Call<ParksResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch parks", t)
            }

            override fun onResponse(call: Call<ParksResponse>, response: Response<ParksResponse>) {
                Log.d(TAG, "Parks received")
                responseLiveData.value = response.body()
            }

        })
        return responseLiveData
    }

    fun getNews() : LiveData<NewsResponse> {
        val responseLiveData : MutableLiveData<NewsResponse> = MutableLiveData()
        val npsRequest : Call<NewsResponse> = npsApi.getNews()

        npsRequest.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d(TAG, "News received")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch news", t)
            }

        })
        return responseLiveData
    }

    fun getCampgrounds() : LiveData<CampgroundResponse> {
        val responseLiveData : MutableLiveData<CampgroundResponse> = MutableLiveData()
        val npsRequest : Call<CampgroundResponse> = npsApi.getCampgrounds()
        npsRequest.enqueue(object : Callback<CampgroundResponse>{
            override fun onResponse(
                call: Call<CampgroundResponse>,
                response: Response<CampgroundResponse>
            ) {
                Log.d(TAG, "Campgrounds received")
                responseLiveData.value = response.body()
            }

            override fun onFailure(call: Call<CampgroundResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch campgrounds", t)
            }

        })
        return responseLiveData
    }

}