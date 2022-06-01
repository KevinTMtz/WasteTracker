package com.bruwus.wastetracker.utils.navigation

import android.app.Activity
import android.content.Intent
import com.bruwus.wastetracker.MainActivity
import com.bruwus.wastetracker.ui.landingpage.LandingPageActivity

fun goToMainActivity(activity: Activity) {
    val intent = Intent(activity, MainActivity::class.java)

    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

    activity.startActivity(intent)
}

fun exitMainActivity(activity: Activity) {
    val intent = Intent(activity, LandingPageActivity::class.java)
    activity.startActivity(intent)
    activity.finish()
}