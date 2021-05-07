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
        val plusGreen: TextView = findViewById(R.id.plus_green)
        val plusPurple: TextView = findViewById(R.id.plus_purple)
        val minusGreen: TextView = findViewById(R.id.minus_green)
        val minusPurple: TextView = findViewById(R.id.minus_purple)
        val numGreen: TextView = findViewById(R.id.num_green)
        val numPurple: TextView = findViewById(R.id.num_purple)

        rollButton.setOnClickListener {
            val result = getResult(numGreen)
            val text = "$result"
            resultsTextView.text = text
            resultsTextView.visibility = VISIBLE
        }

        plusGreen.setOnClickListener {
            val text = (numGreen.text.toString().toInt() + 1).toString()
            numGreen.text = text
        }

        minusGreen.setOnClickListener {
            if(numGreen.text.toString().toInt()>0) {
                val text = (numGreen.text.toString().toInt() - 1).toString()
                numGreen.text= text
            }
        }
    }

    private fun getResult(numGreen: TextView): String {
        var success = 0
        var advantage = 0
        var failure = 0
        var threat = 0
        var triumph = 0
        var despair = 0
        val greenDice = numGreen.text.toString().toInt()
        if(greenDice>0){
            val greenArray=rollGreen(greenDice)
            success += greenArray[0]
            advantage += greenArray[1]
        }
        var result=""
        if(success>0){
            result += if(success==1) "$success success\n" else "$success successes\n"
        }
        if(advantage>0){
            result += if(advantage==1) "$advantage advantage\n" else "$advantage advantages\n"
        }

        return result //TODO remove trailing \n, add message for empty string
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
        return arrayOf(success,advantage)}
}
