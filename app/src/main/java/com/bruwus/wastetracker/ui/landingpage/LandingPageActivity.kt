package com.bruwus.wastetracker.ui.landingpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bruwus.wastetracker.ui.landingpage.compose.LandingPageContent
import com.bruwus.wastetracker.utils.navigation.goToMainActivity
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LandingPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppCompatTheme {
                LandingPageContent()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        Firebase.auth.currentUser?.let {
            goToMainActivity(this)
        }
    }
}