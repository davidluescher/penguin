package ch.hackzurich.savethepinguins.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hackzurich.savethepinguins.R
import ch.hackzurich.savethepinguins.helper.PredictionRoomDatabase
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    private lateinit var viewAdapter: HistoryAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewManager = LinearLayoutManager(this)
        viewAdapter = HistoryAdapter(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        val predictionDao = PredictionRoomDatabase.getDatabase(application).predictionDao()
        AsyncTask.execute {
            val predictions = predictionDao.getAllPredictions()
            runOnUiThread { viewAdapter.addData(predictions) }
        }

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    val newIntent = Intent(this, HomeActivity::class.java)
                    startActivity(newIntent)
                    finish()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_history -> {
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_account -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
            return@setOnNavigationItemSelectedListener false
        }
        bottom_navigation.selectedItemId = R.id.action_history
    }

}