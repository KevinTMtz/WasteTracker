package com.bruwus.wastetracker.utils.general

import android.app.Activity
import android.widget.Toast

fun makeToast(activity: Activity, message: String, toastLength: Int) {
    Toast.makeText(activity, message, toastLength).show()
}