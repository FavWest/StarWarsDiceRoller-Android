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
        val resultsTextView: TextView = findViewById(R.id.results)
        val numGreen: TextView = findViewById(R.id.num_green)
        val numPurple: TextView = findViewById(R.id.num_purple)
        val numYellow: TextView = findViewById(R.id.num_yellow)
        val numRed: TextView = findViewById(R.id.num_red)
        val numBlue: TextView = findViewById(R.id.num_blue)
        val numBlack: TextView = findViewById(R.id.num_black)
        val numWhite: TextView = findViewById(R.id.num_white)

        findViewById<Button>(R.id.button).setOnClickListener {
            val text = getResult(numGreen, numPurple, numYellow, numRed, numBlue, numBlack, numWhite)
            resultsTextView.text = text
            resultsTextView.visibility = VISIBLE
        }

        findViewById<Button>(R.id.clear).setOnClickListener {
            numGreen.text="0"
            numPurple.text="0"
            numYellow.text="0"
            numRed.text="0"
            numBlue.text="0"
            numBlack.text="0"
            numWhite.text="0"
        }

        findViewById<Button>(R.id.plus_green).setOnClickListener {
            val text = (numGreen.text.toString().toInt() + 1).toString()
            numGreen.text = text
        }

        findViewById<Button>(R.id.plus_purple).setOnClickListener {
            val text = (numPurple.text.toString().toInt() + 1).toString()
            numPurple.text = text
        }

        findViewById<Button>(R.id.plus_yellow).setOnClickListener {
            val text = (numYellow.text.toString().toInt() + 1).toString()
            numYellow.text = text
        }

        findViewById<Button>(R.id.plus_red).setOnClickListener {
            val text = (numRed.text.toString().toInt() + 1).toString()
            numRed.text = text
        }

        findViewById<Button>(R.id.plus_blue).setOnClickListener {
            val text = (numBlue.text.toString().toInt() + 1).toString()
            numBlue.text = text
        }

        findViewById<Button>(R.id.plus_black).setOnClickListener {
            val text = (numBlack.text.toString().toInt() + 1).toString()
            numBlack.text = text
        }

        findViewById<Button>(R.id.plus_white).setOnClickListener{
            val text = (numWhite.text.toString().toInt() +1).toString()
            numWhite.text = text
        }

        findViewById<Button>(R.id.minus_green).setOnClickListener {
            if(numGreen.text.toString().toInt()>0) {
                val text = (numGreen.text.toString().toInt() - 1).toString()
                numGreen.text = text
            }
        }

        findViewById<Button>(R.id.minus_purple).setOnClickListener {
            if(numPurple.text.toString().toInt()>0) {
                val text = (numPurple.text.toString().toInt() - 1).toString()
                numPurple.text = text
            }
        }

        findViewById<Button>(R.id.minus_yellow).setOnClickListener {
            if(numYellow.text.toString().toInt()>0) {
                val text = (numYellow.text.toString().toInt() - 1).toString()
                numYellow.text = text
            }
        }

        findViewById<Button>(R.id.minus_red).setOnClickListener {
            if(numRed.text.toString().toInt()>0) {
                val text = (numRed.text.toString().toInt() - 1).toString()
                numRed.text = text
            }
        }

        findViewById<Button>(R.id.minus_blue).setOnClickListener {
            if(numBlue.text.toString().toInt()>0) {
                val text = (numBlue.text.toString().toInt() - 1).toString()
                numBlue.text = text
            }
        }

        findViewById<Button>(R.id.minus_black).setOnClickListener {
            if(numBlack.text.toString().toInt()>0) {
                val text = (numBlack.text.toString().toInt() - 1).toString()
                numBlack.text = text
            }
        }

        findViewById<Button>(R.id.minus_white).setOnClickListener {
            if(numWhite.text.toString().toInt()>0) {
                val text = (numWhite.text.toString().toInt() - 1).toString()
                numWhite.text = text
            }
        }
    }

    private fun getResult(numGreen: TextView, numPurple: TextView, numYellow: TextView,
    numRed: TextView, numBlue: TextView, numBlack: TextView, numWhite: TextView): String {
        var success = 0
        var advantage = 0
        var triumph = 0
        var despair = 0
        var light = 0
        var dark = 0

        val greenDice = numGreen.text.toString().toInt()
        if(greenDice>0){
            val greenArray=rollGreen(greenDice)
            success += greenArray[0]
            advantage += greenArray[1]
        }
        val purpleDice = numPurple.text.toString().toInt()
        if(purpleDice>0){
            val purpleArray=rollPurple(purpleDice)
            success -= purpleArray[0]
            advantage -= purpleArray[1]
        }
        val yellowDice = numYellow.text.toString().toInt()
        if(yellowDice>0){
            val yellowArray = rollYellow(yellowDice)
            success += yellowArray[0]
            advantage += yellowArray[1]
            triumph += yellowArray[2]
        }
        val redDice = numRed.text.toString().toInt()
        if(redDice>0){
            val redArray = rollRed(redDice)
            success -= redArray[0]
            advantage -= redArray[1]
            despair += redArray[2]
        }
        val blueDice = numBlue.text.toString().toInt()
        if(blueDice>0){
            val blueArray = rollBlue(blueDice)
            success += blueArray[0]
            advantage += blueArray[1]
        }
        val blackDice = numBlack.text.toString().toInt()
        if(blackDice>0){
            val blackArray=rollBlack(blackDice)
            success -= blackArray[0]
            advantage -= blackArray[1]
        }
        val whiteDice = numWhite.text.toString().toInt()
        if(whiteDice>0){
            val whiteArray = rollWhite(whiteDice)
            light += whiteArray[0]
            dark += whiteArray[1]
        }
        var result=""
        if(success>0){
            result += if(success==1) "$success success\n" else "$success successes\n"
        } else if(success<0){
            val failure = success * -1
            result += if(failure==1) "$failure failure\n" else "$failure failures\n"
        }
        if(advantage>0){
            result += if(advantage==1) "$advantage advantage\n" else "$advantage advantages\n"
        } else if(advantage<0){
            val threat = advantage * -1
            result += "$threat threat\n"
        }
        if(triumph>0){
            result += if(triumph==1) "$triumph triumph\n" else "$triumph triumphs\n"
        }
        if(despair>0){
            result += if(despair==1) "$despair despair\n" else "$despair despairs\n"
        }
        if(light>0){
            result += "$light light\n"
        }
        if(dark>0){
            result += "$dark dark\n"
        }
        if(result.isNotEmpty()){
            result = result.slice(0..(result.length-2))
        } else {
            result = "No net successes or failures"
        }
        return result
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
        return arrayOf(success,advantage)
    }

    private fun rollPurple(num:Int): Array<Int> {
        var failure = 0
        var threat = 0
        for(dice in 1..num){
            when((1..8).random()){
                2, 3 -> failure ++
                4 -> failure += 2
                5, 6 -> threat ++
                7 -> {
                    failure ++
                    threat ++
                }
                8 -> threat += 2
            }
        }
        return arrayOf(failure,threat)
    }

    private fun rollYellow(num:Int): Array<Int>{
        var success = 0
        var advantage = 0
        var triumph = 0
        for(dice in 1..num){
            when((1..12).random()){
                2, 3 -> success ++
                4, 5 -> success += 2
                6 -> advantage ++
                7, 8, 9 -> {
                    success ++
                    advantage ++
                }
                10, 11 -> advantage += 2
                12 -> triumph ++
            }
        }
        return arrayOf(success, advantage, triumph)
    }

    private fun rollRed(num:Int): Array<Int>{
        var failure = 0
        var threat = 0
        var despair = 0
        for(dice in 1..num){
            when((1..12).random()){
                2, 3 -> failure ++
                4, 5 -> failure += 2
                6, 7 -> threat ++
                8, 9 -> {
                    failure ++
                    threat ++
                }
                10, 11 -> threat += 2
                12 -> despair ++
            }
        }
        return arrayOf(failure, threat, despair)
    }

    private fun rollBlue(num:Int): Array<Int> {
        var success = 0
        var advantage = 0
        for(dice in 1..num){
            when((1..6).random()){
                3 -> success ++
                4 -> {
                    success ++
                    advantage ++
                }
                5 -> advantage += 2
                6 -> advantage ++
            }
        }
        return arrayOf(success,advantage)
    }

    private fun rollBlack(num:Int): Array<Int> {
        var failure = 0
        var threat = 0
        for(dice in 1..num){
            when((1..6).random()){
                3, 4 -> failure ++
                5, 6 -> threat ++
            }
        }
        return arrayOf(failure,threat)
    }

    private fun rollWhite(num:Int): Array<Int> {
        var light = 0
        var dark = 0
        for(dice in 1..num){
            when((1..12).random()){
                1, 2, 3, 4, 5, 6 -> dark ++
                7 -> dark += 2
                8, 9 -> light ++
                10, 11, 12 -> light += 2
            }
        }
        return arrayOf(light, dark)
    }
}
