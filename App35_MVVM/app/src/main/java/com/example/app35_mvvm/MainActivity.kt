package com.example.app35_mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app35_mvvm.databinding.ActivityMainBinding
import com.example.app35_mvvm.viewmodel.CalculateViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CalculateViewModel

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

        viewModel = ViewModelProvider(this).get(CalculateViewModel::class.java)

        binding.btnCalculate.setOnClickListener {
            val result = viewModel.calculateSum(
                binding.etNumber1.text.toString().toIntOrNull() ?: 0,
                binding.etNumber2.text.toString().toIntOrNull() ?: 0,
            )
            binding.tvResultValue.text = "${result.sum}"
        }
    }
}