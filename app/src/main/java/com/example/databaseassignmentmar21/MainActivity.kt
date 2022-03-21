package com.example.databaseassignmentmar21

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var UIDEditText : EditText? = null
    var firstNameEditText : EditText? = null
    var lastNameEditText : EditText? = null
    var rewardsEditText : EditText? = null
    var addButton : Button? = null
    var displayButton : Button? = null
    var deleteButton : Button? = null
    var enterUIDEditText : EditText? = null
    var displayTextView : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UIDEditText = findViewById(R.id.UID)
        firstNameEditText = findViewById(R.id.firstName)
        lastNameEditText = findViewById(R.id.lastName)
        rewardsEditText = findViewById(R.id.rewards)
        addButton = findViewById(R.id.add)
        displayButton = findViewById(R.id.displayButton)
        deleteButton = findViewById(R.id.delete)
        enterUIDEditText = findViewById(R.id.enterUID)
        displayTextView = findViewById(R.id.displayTextView)
    }

    fun add(view: View) {
        /* don't need this b/c android:inputType="number" already prohibits negative numbers
        if(UIDEditText!!.text.toString().toInt() <= 0){
            displayTextView!!.setText("Make sure UID is a positive integer")
            return
        }
         */
        if(
            UIDEditText!!.text.isNotEmpty()
            &&  firstNameEditText!!.text.isNotEmpty()
            &&  lastNameEditText!!.text.isNotEmpty()
            && rewardsEditText!!.text.isNotEmpty()
        ) {
            val db = DBHelper(this, null)
            db.addEntry(
                UIDEditText!!.text.toString().toInt(),
                firstNameEditText!!.text.toString(),
                lastNameEditText!!.text.toString(),
                rewardsEditText!!.text.toString()
            )
            UIDEditText!!.setText("")
            firstNameEditText!!.setText("")
            lastNameEditText!!.setText("")
            rewardsEditText!!.setText("")
        }
        else{
            displayTextView!!.setText("Make sure all fields are non empty before adding to database")
        }
    }

    @SuppressLint("Range")
    fun display(view: View) {
        if(enterUIDEditText!!.text.isNotEmpty()) {
            val db = DBHelper(this, null)
            val cursor = db.getEntry(enterUIDEditText!!.text.toString().toInt())
            if (cursor == null) {
                val s = enterUIDEditText!!.text.toString()
                displayTextView!!.setText("No entry with UID=$s found.")
            } else {
                val fname = cursor.getString(cursor.getColumnIndex(DBHelper.FIRST_NAME_COL))
                val lname = cursor.getString(cursor.getColumnIndex(DBHelper.LAST_NAME_COL))
                val reward = cursor.getString(cursor.getColumnIndex(DBHelper.REWARDS_COL))
                displayTextView!!.setText("$fname $lname" + System.getProperty("line.separator") + "$reward")
            }
            enterUIDEditText!!.setText("")
        }
        else{
            displayTextView!!.setText("Make sure to enter a UID to interact with the database")
        }
    }

    fun delete(view: View) {
        if(enterUIDEditText!!.text.isNotEmpty()) {
            val db = DBHelper(this, null)
            var i = enterUIDEditText!!.text.toString().toInt()
            val check = db.removeEntry(i)
            if(check == 0){
                displayTextView!!.setText("No entry with UID=$i found.")
            }
            enterUIDEditText!!.setText("")
        }
        else{
            displayTextView!!.setText("Make sure to enter a UID to interact with the database")
        }
    }

}