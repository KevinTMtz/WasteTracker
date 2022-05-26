package com.bruwus.wastetracker.ui.utils.browser

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import com.bruwus.wastetracker.R

object InAppBrowser {
    fun open(ctx: Context, url: String) {
        val builder = CustomTabsIntent.Builder()

        val shareIcon = BitmapFactory.decodeResource(
            ctx.resources,
            R.drawable.ic_baseline_share_24,
        )
        val shareIntent = Intent(
            ctx,
            ShareBroadcastReceiver::class.java,
        )
        val pendingIntent = PendingIntent.getBroadcast(
            ctx,
            0,
            shareIntent,
            PendingIntent.FLAG_IMMUTABLE,
        )
        builder.setActionButton(shareIcon, "Share via...", pendingIntent)

        val params = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(ctx.getColor(R.color.blue))
            .build()
        builder.setColorSchemeParams(CustomTabsIntent.COLOR_SCHEME_DARK, params)

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(ctx, Uri.parse(url))
    }
}