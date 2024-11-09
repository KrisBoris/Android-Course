package com.example.app38_coroutines

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app38_coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counter: Int = 0

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

        binding.btnIncrement.setOnClickListener {
            // counter++.toString() doesn't increment after first click
            binding.tvCounter.text = (++counter).toString()
        }

        binding.btnDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                // After app shuts down, to operation goes on for little while
                for(i in 1..1_000_000)
                    Log.i("DOWNLOAD", "Downloading $i in ${Thread.currentThread().name}")
            }
        }
    }
}