package com.bruwus.wastetracker.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bruwus.wastetracker.databinding.ActivityAuthBinding
import com.bruwus.wastetracker.utils.general.makeToast
import com.bruwus.wastetracker.utils.navigation.goToMainActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.authSignUpButton.setOnClickListener {
            signUp()
        }

        binding.authToLoginButton.setOnClickListener {
            changeToLogin()
        }

        binding.authLoginButton.setOnClickListener {
            login()
        }

        binding.authToSignUpButton.setOnClickListener {
            changeToSignUp()
        }
    }

    private fun signUp() {
        val name = binding.authNameText.text.toString()
        val email = binding.authEmailText.text.toString()
        val password = binding.authPasswordText.text.toString()
        val confirmPassword = binding.authConfirmPasswordText.text.toString()

        if (name.isEmpty() || email.isEmpty()) {
            makeToast(this, "Input all information", Toast.LENGTH_SHORT)
            return
        }

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            makeToast(this, "Enter your password", Toast.LENGTH_SHORT)
            return
        }

        if (password != confirmPassword) {
            makeToast(this, "Passwords do not match", Toast.LENGTH_SHORT)
            return
        }

        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val profileUpdates = userProfileChangeRequest {
                    displayName = name
                }

                Firebase.auth.currentUser?.let { user ->
                    user.updateProfile(profileUpdates)
                        .addOnSuccessListener {
                            makeToast(this, "Signed up", Toast.LENGTH_SHORT)
                            goToMainActivity(this)
                        }
                        .addOnFailureListener {
                            makeToast(this, it.message.toString(), Toast.LENGTH_SHORT)
                        }
                }
            }
            .addOnFailureListener {
                makeToast(this, it.message.toString(), Toast.LENGTH_SHORT)
            }
    }

    private fun login() {
        val email = binding.authEmailText.text.toString()
        val password = binding.authPasswordText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            makeToast(this, "Input all information", Toast.LENGTH_SHORT)
            return
        }

        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                makeToast(this, "Logged in", Toast.LENGTH_SHORT)
                goToMainActivity(this)
            }
            .addOnFailureListener {
                makeToast(this, it.message.toString(), Toast.LENGTH_SHORT)
            }
    }

    private fun changeToLogin() {
        binding.authNameText.visibility = View.GONE
        binding.authSignUpGroup.visibility = View.GONE
        binding.authLoginGroup.visibility = View.VISIBLE
        binding.authTitleTextView.text = "Login"
    }

    private fun changeToSignUp() {
        binding.authNameText.visibility = View.VISIBLE
        binding.authLoginGroup.visibility = View.GONE
        binding.authSignUpGroup.visibility = View.VISIBLE
        binding.authTitleTextView.text = "Sign up"
    }

    override fun onStart() {
        super.onStart()

        Firebase.auth.currentUser?.let {
            goToMainActivity(this)
        }
    }
}