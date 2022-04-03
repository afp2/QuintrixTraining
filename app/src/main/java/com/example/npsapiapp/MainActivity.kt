package com.example.npsapiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var getParksButton : Button? = null
    var getNewsButton : Button? = null
    var getCampgroundsButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isFragmentContainerEmpty = savedInstanceState == null
        if(isFragmentContainerEmpty){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, BlankFragment.newInstance())
                .commit()
        }

        getParksButton = findViewById(R.id.getParks)
        getParksButton?.setOnClickListener {
            val i = Intent(this, ParkActivityCL::class.java)
            startActivity(i)
        }

        getNewsButton = findViewById(R.id.getNews)
        getNewsButton?.setOnClickListener {
            val i = Intent(this, NewsActivityCL::class.java)
            startActivity(i)
        }

        getCampgroundsButton = findViewById(R.id.getCampgrounds)
        getCampgroundsButton?.setOnClickListener {
            val i = Intent(this, CampgroundsActivityCL::class.java)
            startActivity(i)
        }
    }

}