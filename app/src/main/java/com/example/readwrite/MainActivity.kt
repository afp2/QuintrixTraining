package com.example.readwrite

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import org.w3c.dom.Text
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private val EXTERNAL_STORAGE_PERMISSION_CODE = 23
    lateinit var shared : SharedPreferences
    var editText : EditText? = null
    var retrieveText : TextView? = null
    var editPrefText : EditText? = null
    var editPrefView : TextView? = null
    //why do I need these lines but thomas does not??
    var savePrefButton : Button? = null
    var showPrefButton : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById<View>(R.id.editText_data) as EditText
        retrieveText = findViewById<View>(R.id.retrieve_text_view) as TextView
        editPrefText = findViewById<View>(R.id.edt) as EditText
        editPrefView = findViewById<View>(R.id.txt) as TextView
        savePrefButton = findViewById<View>(R.id.savePref) as Button
        showPrefButton = findViewById<View>(R.id.viewPref) as Button
        //this line below fixes it
        shared = getSharedPreferences("", Context.MODE_PRIVATE)
        //no longer crashes on lauch but still crashes when I hit either of the buttons
        //try to set onclick in the xml to see if this fixes the issue
        //still crashes even when set as on click in xml
        //think it has something to do with shared (doesn't look like it is initialized anywhere)
        savePrefButton!!.setOnClickListener {
            val edit = shared.edit()
            edit.putString("txt", editPrefText!!.text.toString())
            edit.apply()
        }
        showPrefButton!!.setOnClickListener {
            editPrefView!!.text = shared.getString("txt", "No imported")
        }
    }

    //can only save one piece of info for private and public
    //fix this by changing from overwriting the file to appending to the file (in writeTextData)
    fun savePublicly(view: View?) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE_PERMISSION_CODE
        )
        val editTextData = editText!!.text.toString()

        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }


    fun savePrivately(view: View?) {
        val editTextData = editText!!.text.toString()

        val folder = getExternalFilesDir("AndroidKotlinTraining")

        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        writeTextData(file, editTextData)
        editText!!.setText("")
    }

    fun viewInformation(view: View) {
        val intent = Intent(this@MainActivity, ViewInformationActivity::class.java)
        startActivity(intent)
    }

    private fun writeTextData(file: File, data: String) {
        var fileOutputStream : FileOutputStream? = null
        try {
            //append to file w/ true and newline character in the next line
            //new line not working but is appending correctly
            //can't use '\n' b/c that is not what android uses for newline
            //use System.getProperty("line.separator")
            //this might not work the first time if the file has not yet been created/is empty (not sure)
            fileOutputStream = FileOutputStream(file, true)
            fileOutputStream.write((data + System.getProperty("line.separator")).toByteArray())
            Toast.makeText(this, "Done " + file.absolutePath, Toast.LENGTH_LONG).show()
        }
        catch (e : Exception){
            Toast.makeText(this, e.stackTraceToString(), Toast.LENGTH_LONG).show()
        }
        finally {
            if(fileOutputStream != null) {
                try{
                    fileOutputStream.close()
                }
                catch (e : IOException){
                    e.printStackTrace()
                }
            }
        }
    }


    //added two buttons that allow the user to delete both private and public files
    //delete means overwrite file to contain only the empty string
    //better practice might be to merge this function and writeTextData b/c of the amount
    //of redundant code and add an extra parameter to the function
    private fun deleteData(file : File) {
        var fileOutputStream : FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write("".toByteArray())
            Toast.makeText(this, "Deleted " + file.absolutePath, Toast.LENGTH_LONG).show()
        }
        catch (e : Exception){
            Toast.makeText(this, e.stackTraceToString(), Toast.LENGTH_LONG).show()
        }
        finally {
            if(fileOutputStream != null) {
                try{
                    fileOutputStream.close()
                }
                catch (e : IOException){
                    e.printStackTrace()
                }
            }
        }
    }

    fun deletePublic(view: View) {
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(folder, "QuintrixTrainingPublicFile.txt")
        deleteData(file)
    }

    fun deletePrivate(view: View) {
        val folder = getExternalFilesDir("AndroidKotlinTraining")

        val file = File(folder, "QuintrixTrainingPrivateFile.txt")
        deleteData(file)
    }

    fun retrieve(view: View) {
        val folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        retrieveText!!.text = "External files are stored at $folder"

    }

    fun savePrefs(view : View){
        val edit = shared.edit()
        edit.putString("txt", editPrefText!!.text.toString())
        edit.apply()
    }

    fun showPrefs(view : View){
        editPrefView!!.text = shared.getString("txt", "No imported")
    }

}