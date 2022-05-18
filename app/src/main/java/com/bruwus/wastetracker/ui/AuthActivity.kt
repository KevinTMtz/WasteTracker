package com.bruwus.wastetracker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bruwus.wastetracker.MainActivity
import com.bruwus.wastetracker.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.authSignUpButton.setOnClickListener {
            goToHome()
        }

        binding.authToLoginButton.setOnClickListener {
            changeToLogin()
        }

        binding.authLoginButton.setOnClickListener {
            goToHome()
        }

        binding.authToSignUpButton.setOnClickListener {
            changeToSignUp()
        }
    }

    private fun goToHome() {
        val intent = Intent(this, MainActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        startActivity(intent)
    }

    private fun changeToLogin() {
        binding.authSignUpGroup.visibility = View.GONE
        binding.authLoginGroup.visibility = View.VISIBLE
        binding.authTitleTextView.text = "Login"
    }

    private fun changeToSignUp() {
        binding.authLoginGroup.visibility = View.GONE
        binding.authSignUpGroup.visibility = View.VISIBLE
        binding.authTitleTextView.text = "Sign up"
    }
}