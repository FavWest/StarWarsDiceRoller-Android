package com.favwest.starwarsdiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        val resultsTextView: TextView = findViewById(R.id.results)

        rollButton.setOnClickListener{
            resultsTextView.text = "5 successes!"
            resultsTextView.visibility = VISIBLE
        }
    }
//
//    fun roll() {
//        results.visibility= View.VISIBLE
}