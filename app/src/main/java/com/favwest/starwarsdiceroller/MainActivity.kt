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
        val numGreen: TextView = findViewById(R.id.num_green)

        rollButton.setOnClickListener {
            var greenDice = numGreen.text.toString().toInt()
            Toast.makeText(this, greenDice.toString(), Toast.LENGTH_LONG).show()
            val resultArray=rollGreen(greenDice + 1)
            resultsTextView.text = "${resultArray[0]} success, ${resultArray[1]} advantage"
            resultsTextView.visibility = VISIBLE
        }
    }

    private fun rollGreen(num:Int): Array<Int> {
        var success = 0
        var advantage = 0
        for(dice in 1..num){
            when((1..8).random()){
                2, 3 -> success ++
                4 -> success += 2
                5, 6 -> advantage ++
                7 -> {
                    success ++
                    advantage ++
                }
                8 -> advantage += 2
            }
        }
        return arrayOf(success,advantage,0,0,0,0)}
}
