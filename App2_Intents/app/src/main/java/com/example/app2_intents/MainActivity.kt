package com.example.app2_intents

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app2_intents.databinding.ActivityMainBinding

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
        // Get motivated
        val url = "https://www.youtube.com/watch?v=NOZONW-UK0w"
        // Get rickrolled
//        val url = "https://www.youtube.com/watch?v=dQw4w9WgXcQ"

        binding.apply {
            btnExplicit.setOnClickListener {
                val exIntent = Intent(this@MainActivity, SecondActivity::class.java)
                startActivity(exIntent)
                finish()
            }
            btnImplicit.setOnClickListener {
                val imIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(imIntent)
            }
        }
    }
}