package com.example.fragmentapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpTabBar()
    }

    //this will be done tomorrow (code is on thomas' github)
    private fun setUpTabBar(){
        var vP = findViewById<ViewPager2>(R.id.viewPager)
        var tL = findViewById<TabLayout>(R.id.tabLayout)
        val adapter = TabPageAdapter(this,   tL.tabCount)
        vP.adapter = adapter

        vP.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback()
        {
            override fun onPageSelected(position: Int) {
                tL.selectTab(tL.getTabAt(position))
            }
        })

        tL.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener
        {
            override fun onTabSelected(tab: TabLayout.Tab)
            {
                vP.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

}