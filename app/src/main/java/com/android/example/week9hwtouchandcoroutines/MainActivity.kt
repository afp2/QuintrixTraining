package com.android.example.week9hwtouchandcoroutines

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.android.example.week9hwtouchandcoroutines.urls
import com.android.example.week9hwtouchandcoroutines.descs
import com.android.example.week9hwtouchandcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

private lateinit var binding : ActivityMainBinding
var job : Job? = null

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        view.setOnTouchListener{
                v : View, m : MotionEvent ->
            //job for current coroutines is passed back up and if
            //onTouchListener is activated again it will cancel
            //current coroutine and start another one
            //not sure if this is the best way to do this but it is the first thing
            //that I though of for this requirement of the assignment
            if(job != null){
                job?.cancel()
                Log.d("TouchListener", "coroutine canceled")
            }
            job = handleTouch(v, m)
            true
        }
    }

    private fun handleTouch(v : View, m : MotionEvent) : Job?{
        val top = v.top
        val bot = v.bottom
        val segment = (bot - top) / 10
        Log.d("handleTouch", "Top of screen is $top , Bottom of screen is $bot")
        val y = m.y
        for (i in 0 until 10){
            if(i <= y && y < (i+1)*segment){
                Log.d("handleTouch", "Starting $i")
                return handleCoroutines(i)
                //break
            }
        }
        return null
    }

    private fun handleCoroutines(i : Int) : Job {
        val j = GlobalScope.launch(Dispatchers.IO) {
            try {
                val imageUrl = URL(urls[i])
                val httpConnection = imageUrl.openConnection() as HttpURLConnection
                httpConnection.doInput = true
                httpConnection.connect()
                val inputStream = httpConnection.inputStream
                val bitmapImage = BitmapFactory.decodeStream(inputStream)
                launch(Dispatchers.Main) {
                    Log.d("handleCoroutines", "binding images and descriptions")
                    binding.imageView.setImageBitmap(bitmapImage)
                    binding.textView.text = descs[i]
                }
            }
            catch (e : Exception){
                Log.e("handleCoroutines", e.toString())
            }

        }
        return j
    }

}