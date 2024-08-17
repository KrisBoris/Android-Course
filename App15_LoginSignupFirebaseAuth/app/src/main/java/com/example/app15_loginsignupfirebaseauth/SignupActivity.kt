package com.example.app15_loginsignupfirebaseauth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app15_loginsignupfirebaseauth.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        firebaseAuth = FirebaseAuth.getInstance()

        binding.apply {

            btnSignup.setOnClickListener {
                val email = etSignupEmail.text.toString()
                val password = etSignupPassword.text.toString()

                if(email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this@SignupActivity) {task ->
                            if(task.isSuccessful) {
                                Toast.makeText(this@SignupActivity, "Signup Successful", Toast.LENGTH_LONG).show()
                                val intent = Intent(this@SignupActivity, Login_Activity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else {
                                Toast.makeText(this@SignupActivity, "Signup Failed", Toast.LENGTH_LONG).show()
                            }
                        }
                }
                else
                    Toast.makeText(this@SignupActivity, "Enter email and password", Toast.LENGTH_LONG).show()
            }
            tvLogin.setOnClickListener {
                startActivity(Intent(this@SignupActivity, Login_Activity::class.java))
                finish()
            }
        }
    }
}