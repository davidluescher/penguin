package ch.hackzurich.savethepinguins.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ch.hackzurich.savethepinguins.R
import kotlinx.android.synthetic.main.activity_home.*

class DetailRatingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rating)
        btnAction.setOnClickListener { finish() }
    }
}