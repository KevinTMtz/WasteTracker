package com.bruwus.wastetracker.ui.utils.feedback

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.bruwus.wastetracker.R

class LoadingIndicator(context: Context) {
    private var dialog: Dialog

    init {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setView(R.layout.loading_indicator)

        dialog = builder.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun show(show: Boolean) {
        if (show) dialog.show() else dialog.dismiss()
    }
}