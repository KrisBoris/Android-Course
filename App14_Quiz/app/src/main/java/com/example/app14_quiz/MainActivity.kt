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
        arrayOf("Application Programming Interface", "Android Programming Interface", "Android Package Information",
            arrayOf("2010", "2008", "2006")
        )
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

    private fun resetButtonColor() {
        binding.apply {
            btnAnswer1.setBackgroundColor(Color.rgb(50, 59, 96))
            btnAnswer2.setBackgroundColor(Color.rgb(50, 59, 96))
            btnAnswer3.setBackgroundColor(Color.rgb(50, 59, 96))
        }
    }

    private fun showResult() {
        Toast.makeText(this, "Result: ${score} out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.btnReset.isEnabled = true
    }
}