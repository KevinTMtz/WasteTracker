package com.bruwus.wastetracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bruwus.wastetracker.databinding.ActivityLandingPageBinding
import com.bruwus.wastetracker.utils.navigation.goToMainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()

        super.onCreate(savedInstanceState)

        binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToAuthButton.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()

        Firebase.auth.currentUser?.let {
            goToMainActivity(this)
        }
    }
}