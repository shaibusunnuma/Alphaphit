package com.example.alphaphitness

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        setSupportActionBar(exercise_toolbar2)
        val actionbar = supportActionBar
        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        exercise_toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }

        btnfinish.setOnClickListener{
            finish()
            startActivity(Intent(this,MainActivity::class.java))
        }

        addDateToDatabase()

    }
    private fun addDateToDatabase(){
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Toast.makeText(this, ""+dateTime, Toast.LENGTH_SHORT).show()

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        val dbHandler = SQLiteDatabase(this, null)
        dbHandler.addDate(date)
        Toast.makeText(this, "date added", Toast.LENGTH_SHORT).show()
    }

}