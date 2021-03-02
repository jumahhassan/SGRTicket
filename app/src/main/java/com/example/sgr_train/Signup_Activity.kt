package com.example.sgr_train

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.sgr_train.databinding.ActivitySignupBinding

private lateinit var binding: ActivitySignupBinding
class Signup_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.signUpBtn.setOnClickListener {
            val signupIntent= Intent(applicationContext, Login_Activity::class.java)
            startActivity(signupIntent)
        }

        binding.logIn.setOnClickListener {
            val loginIntent= Intent(applicationContext, Login_Activity::class.java)
            startActivity(loginIntent)
        }
    }
}