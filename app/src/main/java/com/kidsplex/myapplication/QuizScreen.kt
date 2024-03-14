package com.kidsplex.myapplication

import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.util.Locale



class QuizScreen : AppCompatActivity() {
    private lateinit var prev:ExtendedFloatingActionButton
    private lateinit var questionTxtView: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var next: ExtendedFloatingActionButton
    private lateinit var finish: ExtendedFloatingActionButton
    private lateinit var submit: ExtendedFloatingActionButton
    private lateinit var scoreTextView: TextView
    private val questions = arrayOf(
        "The staple food of the Vedic Aryan was?",
        "The tropic of cancer does not pass through which of these Indian states ?",
        "Which river of India is called Vridha Ganga?",
        "Which one of the following river flows between Vindhyan and Satpura ranges?",
        "The Central Rice Research Station is situated in?",
        "Who among the following wrote Sanskrit grammar?",
        "Which among the following headstreams meets the Ganges in last?",
        "The metal whose salts are sensitive to light is?",
        "River Luni originates near Pushkar and drains into which one of the following?",
        "Which one of the following rivers originates in Brahmagiri range of Western Ghats?"
    )
    private val options = arrayOf(
        arrayOf("Barley and rice","Milk and its products","Rice and pulses","Vegetables and fruits"),
        arrayOf("Madhya Pradesh","West Bengal","Rajasthan","Odisha"),
        arrayOf("Krishna","Godavari","Kaveri","Narmada"),
        arrayOf("Narmada", "Mahanadi", "Son", "Netravati"),
        arrayOf("Chennai", "Cuttack", "Bangalore", "Quilon"),
        arrayOf("Kalidasa","Charak","Panini","Aryabhatt"),
        arrayOf("Alaknanda","Pindar","Mandakini","Bhagirathi"),
        arrayOf("Zinc","Silver","Copper","Aluminum"),
        arrayOf("Rann of Kachchh","Arabian Sea","Gulf of Cambay","Lake Sambhar"),
        arrayOf("Pennar","Cauvery","Krishna","Tapti")
    )

    private val correctAnswers = arrayOf(1, 3, 1,0,1,2,3,1,0,1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_screen)
        radioGroup = findViewById(R.id.radioGroup)
        questionTxtView = findViewById(R.id.questionText)
        next = findViewById(R.id.next)
        finish = findViewById(R.id.fnsh)
        submit = findViewById(R.id.sbmt_btn)
        scoreTextView = findViewById(R.id.score)
        prev = findViewById(R.id.prev)
        scoreTextView.text = "Score: 0"
        showQuestion()
        submit.setOnClickListener {
            val selectedOption = radioGroup.checkedRadioButtonId
            if (selectedOption != -1) {
                val selectedOptionIndex = radioGroup.indexOfChild(findViewById(selectedOption))
                if (selectedOptionIndex == correctAnswers[currentQuestionIndex]) {
                    score += 10
                    scoreTextView.text = "Score: $score"
                    showFeedback("You got it correct! 😉😎 : ${options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]}.")
                }else{
                    showFeedback("OOPS! ☹️😢 : Answer is ${options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]}.")
                }
            }
        }

        next.setOnClickListener {
            radioGroup.clearCheck()
            if (currentQuestionIndex < questions.size-1) {
                currentQuestionIndex++
                showQuestion()
                showFeedback("")
            } else {
                val intent = Intent(this, ScoreScreen::class.java)
                intent.putExtra("score", score)
                startActivity(intent)
                }
            }

        prev.setOnClickListener {
            radioGroup.clearCheck()
            if(currentQuestionIndex>=1) {
                currentQuestionIndex--
                showQuestion()
                showFeedback("")
            }
            else{
                Toast.makeText(this, "No question present", Toast.LENGTH_SHORT).show()
            }
        }

        finish.setOnClickListener {
            val intent = Intent(this, ScoreScreen::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
        }
    }
    private fun showQuestion() {
        questionTxtView.text = questions[currentQuestionIndex]
        for (i in 0 until radioGroup.childCount) {
            val radioButton = radioGroup.getChildAt(i) as RadioButton
            radioButton.text = options[currentQuestionIndex][i]
        }
    }

    private fun showFeedback(message: String) {
        val feedbackTextView = findViewById<TextView>(R.id.fdbk)
        feedbackTextView.text = message
    }
}