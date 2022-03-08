package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)
        class MyOnClick : View.OnClickListener {
            override fun onClick(p0: View?) {
                sendMessage(button)
            }
        }
        button.setOnClickListener(MyOnClick())

        /*can also do this  (another way to do it on slides as well)
        val sendButton = findViewById<Button>(R.id.button)
        sendButton.setOnClickListener { sendMessage(sendButton) }
         */

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "onStop got called!", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "onPause got called!", Toast.LENGTH_LONG).show()
    }


    fun sendMessage(view : View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply { putExtra(EXTRA_MESSAGE, message) }
        startActivity(intent)
    }



}