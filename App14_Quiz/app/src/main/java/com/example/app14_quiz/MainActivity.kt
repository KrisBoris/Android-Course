package com.example.app14_quiz

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app14_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val questions = arrayOf("What is the built in database in Android Studio?",
        "What is a full form of API in Android Development?",
        "In which year, first Android was released by Google?")

    private val options = arrayOf(arrayOf("MySQL", "SQLite", "Firebase"),
        arrayOf("Application Programming Interface", "Android Programming Interface", "Android Package Information"),
        arrayOf("2010", "2008", "2006")
    )

    private val correctAnswers = arrayOf(1, 0, 2)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        displayQuestion()

        binding.apply {
            btnAnswer1.setOnClickListener {
                checkAnswer(0)
            }
            btnAnswer2.setOnClickListener {
                checkAnswer(1)
            }
            btnAnswer3.setOnClickListener {
                checkAnswer(2)
            }
            btnReset.setOnClickListener {
                restartQuiz()
            }
        }
    }

    private fun correctButtonColor(buttonIndex : Int) {
        when (buttonIndex) {
            0 -> binding.btnAnswer1.setBackgroundColor(Color.GREEN)
            1 -> binding.btnAnswer2.setBackgroundColor(Color.GREEN)
            2 -> binding.btnAnswer3.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColor(buttonIndex : Int) {
        when (buttonIndex) {
            0 -> binding.btnAnswer1.setBackgroundColor(Color.RED)
            1 -> binding.btnAnswer2.setBackgroundColor(Color.RED)
            2 -> binding.btnAnswer3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonsColors() {
        binding.apply {
            btnAnswer1.setBackgroundColor(Color.rgb(50, 59, 96))
            btnAnswer2.setBackgroundColor(Color.rgb(50, 59, 96))
            btnAnswer3.setBackgroundColor(Color.rgb(50, 59, 96))
        }
    }

    private fun enableButtons() {
        binding.apply {
            btnAnswer1.isEnabled = true
            btnAnswer2.isEnabled = true
            btnAnswer3.isEnabled = true
        }
    }

    private fun disableButtons() {
        binding.apply {
            btnAnswer1.isEnabled = false
            btnAnswer2.isEnabled = false
            btnAnswer3.isEnabled = false
        }
    }

    private fun showResult() {
        Toast.makeText(this, "Result: ${score} out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.btnReset.isEnabled = true
    }

    private fun displayQuestion() {
        binding.apply {
            tvQuestion.text = questions[currentQuestionIndex]
            btnAnswer1.text = options[currentQuestionIndex][0].toString()
            btnAnswer2.text = options[currentQuestionIndex][1].toString()
            btnAnswer3.text = options[currentQuestionIndex][2].toString()
        }
        resetButtonsColors()
        enableButtons()
    }

    private fun checkAnswer(selectedAnswerIndex : Int) {
        if(selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            score++
            correctButtonColor(selectedAnswerIndex)
        }
        else {
            correctButtonColor(correctAnswers[currentQuestionIndex])
            wrongButtonColor(selectedAnswerIndex)
        }

        if(currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            disableButtons()
            binding.tvQuestion.postDelayed({displayQuestion()}, 1000)
        }
        else {
            showResult()
            disableButtons()
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.btnReset.isEnabled = false
    }
}