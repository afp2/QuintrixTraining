package com.example.npsapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ParkActivityCL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_park_cl)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.parkFragmentContainer, NPSParkFragment.newInstance())
                .commit()
        }
    }
}