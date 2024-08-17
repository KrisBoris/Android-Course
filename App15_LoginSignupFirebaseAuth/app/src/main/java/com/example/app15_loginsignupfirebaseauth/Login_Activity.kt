package com.example.app15_loginsignupfirebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app15_loginsignupfirebaseauth.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {
            btnLogin.setOnClickListener {
                val email = etLoginEmail.text.toString()
                val password = etLoginPassword.text.toString()

                if(email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@Login_Activity) {task ->
                            if(task.isSuccessful) {
                                Toast.makeText(this@Login_Activity, "Login successful", Toast.LENGTH_LONG).show()
                                val intent = Intent(this@Login_Activity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else
                                Toast.makeText(this@Login_Activity, "Unable to login", Toast.LENGTH_LONG).show()
                        }
                }
                else
                    Toast.makeText(this@Login_Activity, "Enter login and password", Toast.LENGTH_LONG).show()
            }
            tvRegister.setOnClickListener {
                startActivity(Intent(this@Login_Activity, SignupActivity::class.java))
                finish()
            }
        }
    }
}