package com.ryosuke_mita.anatadare

import android.app.*
import android.content.Context
import android.content.Intent
import android.telecom.Call
import android.telecom.CallScreeningService
import android.util.Log
import androidx.core.app.NotificationCompat

const val CHANNEL_ID = "anatadare_notification_id"
class IncomingCallSearchService: CallScreeningService() {
    override fun onScreenCall(detail: Call.Details) {
        Log.d(this.javaClass.name, "call coming")
        val number = detail.handle.schemeSpecificPart
        val channel = NotificationChannel(CHANNEL_ID, "Anatadare Channel", NotificationManager.IMPORTANCE_HIGH)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        channel.enableVibration(true)
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

        val resultIntent = Intent(this, WebViewActivity::class.java).apply {
            putExtra(INCOMING_PHONE_NUMBER, number)
        }
        val resultPendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_IMMUTABLE)
        }
        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply { setContentIntent(resultPendingIntent) }
        val notification = builder.setContentTitle("着信中")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("${number} を検索するよ")
            .build()

        manager.notify(0, notification)
    }
}