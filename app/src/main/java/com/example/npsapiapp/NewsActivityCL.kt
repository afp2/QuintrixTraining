package com.example.npsapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NewsActivityCL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_cl)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.newsFragmentContainer, NPSNewsFragment.newInstance())
                .commit()
        }
    }
}