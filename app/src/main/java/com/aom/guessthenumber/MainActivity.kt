package com.aom.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var challengeNumber: Int = 0
    var scoreNumber: Int = 0
    var guessCount: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // หา Button โดยใช้ ID ชื่อว่า "guessNumberButton"
        val guessNumberButton = findViewById<Button>(R.id.guessNumberButton)
        // หา Button โดยใช้ ID ชื่อว่า "newGameButton"
        val newGameButton = findViewById<Button>(R.id.newGameButton)

        // ตั้ง Event onClick
        guessNumberButton.setOnClickListener {
            // Action ตอนกดปุ่ม "Guess the Number!
            onGuessing()
        }
        newGameButton.setOnClickListener {
            newGame()
        }
        toggleHintLayout(false)
        newGame()
    }

    fun toggleHintLayout(toggle: Boolean) {
        var hintLayout = findViewById<LinearLayout>(R.id.hintLayout)
        if(toggle)
            hintLayout.visibility = View.VISIBLE
        else
            hintLayout.visibility = View.INVISIBLE
    }

    fun onGuessing() {
        // หา Input โดยใช้ ID ชื่อว่า "guessNumberInput"
        var guessNumberInput = findViewById<EditText>(R.id.guessNumberInput)
        // หา Text โดยใช้ ID ชื่อว่า "hintValueText"
        var hintText = findViewById<TextView>(R.id.hintValueText)
        // รับค่าจาก guessNumberInput และแปลงเป็นตัวเลขเพื่อจะนำมาเปรียบเทียบ
        val guessNumber = guessNumberInput.getText().toString()

        if(guessNumber.isEmpty()) return newGame()
        guessCount++
        toggleHintLayout(true)
        if(guessNumber.toInt() < challengeNumber)
            hintText.setText("Lower")
        else if(guessNumber.toInt() > challengeNumber)
            hintText.setText("Higher")
        else
            correct()
    }

    fun newGame(resetScore: Boolean = true) {
        var guessNumberInput = findViewById<EditText>(R.id.guessNumberInput)
        toggleHintLayout(false)
        if(resetScore) updateScore(0)
        challengeNumber = (0 until 1000).random()
        updateAnswerHint()
        guessNumberInput.getText().clear()
        guessCount = 0
    }

    fun correct() {
        updateScore(scoreNumber+1)
        Toast.makeText(
            applicationContext,
            "YOU WIN! (You have guessed " + guessCount.toString() + " times.)",
            Toast.LENGTH_SHORT
        ).show()
        toggleHintLayout(false)
        newGame(false)
    }

    fun updateAnswerHint() {
        var answerText = findViewById<TextView>(R.id.answerHint)
        answerText.setText(challengeNumber.toString())
    }

    fun updateScore(value: Int) {
        scoreNumber = value
        var scoreText = findViewById<TextView>(R.id.scoreValue)
        scoreText.setText(scoreNumber.toString())
    }
}