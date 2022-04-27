package com.android.example.cookieclickerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var currentScore = 0
    private var submittedName = ""
    private var submittedScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgCookie : ImageView = findViewById(R.id.imgCookie)
        val total : TextView = findViewById(R.id.total)
        val editText : EditText = findViewById(R.id.editText)
        val submitButton : Button = findViewById(R.id.submitButton)

        imgCookie.setOnClickListener {
            currentScore++
            total.text = "$currentScore"
        }

        submitButton.setOnClickListener {
            submittedName = editText.text.toString()
            submittedScore = currentScore
        }

    }
}