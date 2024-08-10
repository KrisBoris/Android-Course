package com.example.app13_stopwatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app13_stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var isRunning = false
    private var timerSeconds = 0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds++
            val hours = timerSeconds / 3600
            val minutes = (timerSeconds % 3600) / 60
            val seconds = timerSeconds % 60

            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.tvTimer.text = time

            handler.postDelayed(this, 1000)
        }
    }

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
            btnStart.setOnClickListener {
                startTimer()
            }
            btnStop.setOnClickListener {
                stopTimer()
            }
            btnReset.setOnClickListener {
                resetTimer()
            }
        }
    }

    private fun startTimer() {
        if(!isRunning) {
            isRunning = true
            handler.postDelayed(runnable, 1000)
            binding.apply {
                btnStart.isEnabled = false
                btnStop.isEnabled = true
                btnReset.isEnabled = true
            }
        }
    }

    private fun stopTimer() {
        if(isRunning) {
            isRunning = false
            handler.removeCallbacks(runnable)
            binding.apply {
                btnStart.isEnabled = true
                btnStart.text = "Resume"
                btnStop.isEnabled = false
                btnReset.isEnabled = true
            }
        }
    }

    private fun resetTimer() {
        stopTimer()
        timerSeconds = 0
        binding.apply {
            tvTimer.text = "00:00:00"
            btnStart.text = "Start"
            btnReset.isEnabled = false
        }
    }
}