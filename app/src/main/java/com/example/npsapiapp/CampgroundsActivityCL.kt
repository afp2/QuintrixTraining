package com.example.npsapiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CampgroundsActivityCL : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campgrounds_cl)
        val isFragmentContainerEmpty = savedInstanceState == null
        if (isFragmentContainerEmpty) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.campgroundsFragmentContainer, NPSCampgroundsFragment.newInstance())
                .commit()
        }

    }
}