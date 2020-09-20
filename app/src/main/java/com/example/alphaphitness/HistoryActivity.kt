package com.example.alphaphitness

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(history_toolbar)
        val actionbar = supportActionBar

        if(actionbar!=null){
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.title = "HISTORY"
        }
        history_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates(){
        val dbHandler = SQLiteDatabase(this, null)
        val database = dbHandler.getDatabase()

        if(database.size>0){
            tvHistory.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            tv_noData.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, database)
            rvHistory.adapter = historyAdapter
        }else{
            tvHistory.visibility = View.GONE
            rvHistory.visibility = View.GONE
            tv_noData.visibility = View.VISIBLE
        }

    }
}