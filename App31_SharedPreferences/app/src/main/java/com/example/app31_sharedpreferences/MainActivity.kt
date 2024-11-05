package com.example.app31_sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app31_sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
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

        sharedPreferences = getSharedPreferences("note_data", Context.MODE_PRIVATE)

        binding.btnSave.setOnClickListener {
            val note = binding.etEnteredText.text.toString()
            val sharedEdit = sharedPreferences.edit()
            sharedEdit.putString("note", note)
            sharedEdit.apply()
            Toast.makeText(this@MainActivity, "Note saved", Toast.LENGTH_SHORT).show()
            binding.etEnteredText.text.clear()
        }

        binding.btnDisplay.setOnClickListener {
            val storedNote = sharedPreferences.getString("note", "")
            binding.tvSavedText.text = storedNote
        }
    }
}