package com.example.sgr_train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sgr_train.databinding.ActivityLoginBinding


private lateinit var binding: ActivityLoginBinding
class Login_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInBtn.setOnClickListener {
            val loginIntent= Intent(applicationContext, MainActivity::class.java)
            startActivity(loginIntent)
        }

        binding.signUp.setOnClickListener {
            val signupIntent= Intent(applicationContext, Signup_Activity::class.java)
            startActivity(signupIntent)
        }
    }
}