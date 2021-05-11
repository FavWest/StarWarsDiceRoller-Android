package com.favwest.starwarsdiceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.favwest.starwarsdiceroller.databinding.ActivityMainBinding
import org.w3c.dom.Text
import java.util.Collections.list

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {

            val numList =
                listOf(numGreen, numPurple, numYellow, numRed, numBlue, numBlack, numWhite)
            val plusList =
                listOf(plusGreen, plusPurple, plusYellow, plusRed, plusBlue, plusBlack, plusWhite)
            val minusList = listOf(
                minusGreen,
                minusPurple,
                minusYellow,
                minusRed,
                minusBlue,
                minusBlack,
                minusWhite
            )

            button.setOnClickListener {
                val text =
                    getResult(numGreen, numPurple, numYellow, numRed, numBlue, numBlack, numWhite)
                results.text = text
                results.visibility = VISIBLE
            }

            clear.setOnClickListener {
                for (item in numList) {
                    item.text = "0"
                }
            }

            for (item in plusList) {
                item.setOnClickListener() { makePlusClickListener(it) }
            }

            for (item in minusList) {
                item.setOnClickListener() { makeMinusClickListener(it) }
            }
        }
    }

    private fun makePlusClickListener(view: View) {
        binding.apply {
            val num = when (view.id) {
                plusGreen.id -> numGreen
                plusPurple.id -> numPurple
                plusYellow.id -> numYellow
                plusRed.id -> numRed
                plusBlue.id -> numBlue
                plusBlack.id -> numBlack
                plusWhite.id -> numWhite
                else -> error("How'd you do that? Something not in the plus list wound up in makePlusClickListener.")
            }
            val text = (num.text.toString().toInt() + 1).toString()
            num.text = text
        }
    }

    private fun makeMinusClickListener(view: View) {
        binding.apply {
            val num = when (view.id) {
                minusGreen.id -> numGreen
                minusPurple.id -> numPurple
                minusYellow.id -> numYellow
                minusRed.id -> numRed
                minusBlue.id -> numBlue
                minusBlack.id -> numBlack
                minusWhite.id -> numWhite
                else -> error("How'd you do that? Something not in the minus list wound up in makeMinusClickListener.")
            }
            if (num.text.toString().toInt() > 0) {
                val text = (num.text.toString().toInt() - 1).toString()
                num.text = text
            }
        }
    }

    private fun getResult(
        numGreen: TextView, numPurple: TextView, numYellow: TextView,
        numRed: TextView, numBlue: TextView, numBlack: TextView, numWhite: TextView
    ): String {
        var success = 0
        var advantage = 0
        var triumph = 0
        var despair = 0
        var light = 0
        var dark = 0

        val greenDice = numGreen.text.toString().toInt()
        if (greenDice > 0) {
            val greenArray = rollGreen(greenDice)
            success += greenArray[0]
            advantage += greenArray[1]
        }
        val purpleDice = numPurple.text.toString().toInt()
        if (purpleDice > 0) {
            val purpleArray = rollPurple(purpleDice)
            success -= purpleArray[0]
            advantage -= purpleArray[1]
        }
        val yellowDice = numYellow.text.toString().toInt()
        if (yellowDice > 0) {
            val yellowArray = rollYellow(yellowDice)
            success += yellowArray[0]
            advantage += yellowArray[1]
            triumph += yellowArray[2]
        }
        val redDice = numRed.text.toString().toInt()
        if (redDice > 0) {
            val redArray = rollRed(redDice)
            success -= redArray[0]
            advantage -= redArray[1]
            despair += redArray[2]
        }
        val blueDice = numBlue.text.toString().toInt()
        if (blueDice > 0) {
            val blueArray = rollBlue(blueDice)
            success += blueArray[0]
            advantage += blueArray[1]
        }
        val blackDice = numBlack.text.toString().toInt()
        if (blackDice > 0) {
            val blackArray = rollBlack(blackDice)
            success -= blackArray[0]
            advantage -= blackArray[1]
        }
        val whiteDice = numWhite.text.toString().toInt()
        if (whiteDice > 0) {
            val whiteArray = rollWhite(whiteDice)
            light += whiteArray[0]
            dark += whiteArray[1]
        }
        var result = ""
        if (success > 0) {
            result += if (success == 1) "$success success\n" else "$success successes\n"
        } else if (success < 0) {
            val failure = success * -1
            result += if (failure == 1) "$failure failure\n" else "$failure failures\n"
        }
        if (advantage > 0) {
            result += if (advantage == 1) "$advantage advantage\n" else "$advantage advantages\n"
        } else if (advantage < 0) {
            val threat = advantage * -1
            result += "$threat threat\n"
        }
        if (triumph > 0) {
            result += if (triumph == 1) "$triumph triumph\n" else "$triumph triumphs\n"
        }
        if (despair > 0) {
            result += if (despair == 1) "$despair despair\n" else "$despair despairs\n"
        }
        if (light > 0) {
            result += "$light light\n"
        }
        if (dark > 0) {
            result += "$dark dark\n"
        }
        if (result.isNotEmpty()) {
            result = result.slice(0..(result.length - 2))
        } else {
            result = "No net successes or failures"
        }
        return result
    }

    private fun rollGreen(num: Int): Array<Int> {
        var success = 0
        var advantage = 0
        for (dice in 1..num) {
            when ((1..8).random()) {
                2, 3 -> success++
                4 -> success += 2
                5, 6 -> advantage++
                7 -> {
                    success++
                    advantage++
                }
                8 -> advantage += 2
            }
        }
        return arrayOf(success, advantage)
    }

    private fun rollPurple(num: Int): Array<Int> {
        var failure = 0
        var threat = 0
        for (dice in 1..num) {
            when ((1..8).random()) {
                2, 3 -> failure++
                4 -> failure += 2
                5, 6 -> threat++
                7 -> {
                    failure++
                    threat++
                }
                8 -> threat += 2
            }
        }
        return arrayOf(failure, threat)
    }

    private fun rollYellow(num: Int): Array<Int> {
        var success = 0
        var advantage = 0
        var triumph = 0
        for (dice in 1..num) {
            when ((1..12).random()) {
                2, 3 -> success++
                4, 5 -> success += 2
                6 -> advantage++
                7, 8, 9 -> {
                    success++
                    advantage++
                }
                10, 11 -> advantage += 2
                12 -> triumph++
            }
        }
        return arrayOf(success, advantage, triumph)
    }

    private fun rollRed(num: Int): Array<Int> {
        var failure = 0
        var threat = 0
        var despair = 0
        for (dice in 1..num) {
            when ((1..12).random()) {
                2, 3 -> failure++
                4, 5 -> failure += 2
                6, 7 -> threat++
                8, 9 -> {
                    failure++
                    threat++
                }
                10, 11 -> threat += 2
                12 -> despair++
            }
        }
        return arrayOf(failure, threat, despair)
    }

    private fun rollBlue(num: Int): Array<Int> {
        var success = 0
        var advantage = 0
        for (dice in 1..num) {
            when ((1..6).random()) {
                3 -> success++
                4 -> {
                    success++
                    advantage++
                }
                5 -> advantage += 2
                6 -> advantage++
            }
        }
        return arrayOf(success, advantage)
    }

    private fun rollBlack(num: Int): Array<Int> {
        var failure = 0
        var threat = 0
        for (dice in 1..num) {
            when ((1..6).random()) {
                3, 4 -> failure++
                5, 6 -> threat++
            }
        }
        return arrayOf(failure, threat)
    }

    private fun rollWhite(num: Int): Array<Int> {
        var light = 0
        var dark = 0
        for (dice in 1..num) {
            when ((1..12).random()) {
                1, 2, 3, 4, 5, 6 -> dark++
                7 -> dark += 2
                8, 9 -> light++
                10, 11, 12 -> light += 2
            }
        }
        return arrayOf(light, dark)
    }
}
