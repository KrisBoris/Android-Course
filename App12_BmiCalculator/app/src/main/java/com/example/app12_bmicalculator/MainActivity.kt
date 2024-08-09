package com.example.app12_bmicalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app12_bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
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
        binding.apply {

            btnSubmit.setOnClickListener {
                val height = etHeight.text.toString().toFloatOrNull()
                val weight = etWeight.text.toString().toFloatOrNull()
                val result = calcBmi(height, weight)
                if(result != null) {
                    val category = bmiCategory(result)
                    displayResult(result, category)
                }
                else
                    Toast.makeText(this@MainActivity, "Invalid input", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun calcBmi(height : Float?, weight : Float?) : Float? {
        return if(height != null && weight != null) {
            weight / ((height / 100) * (height / 100))
        } else
            null
    }

    private fun bmiCategory(bmi : Float) : String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "American"
        }
    }

    private fun displayResult(result : Float, category : String) {
        binding.tvResult.text = String.format("%.2f -> %s", result, category)
    }
}