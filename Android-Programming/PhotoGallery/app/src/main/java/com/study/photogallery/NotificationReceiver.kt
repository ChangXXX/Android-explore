package com.study.photogallery

import android.app.Activity
import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat

private const val TAG = "NotificationReceiver"

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, p1: Intent) {
        Log.i(TAG, "received result: $resultCode")
        if (resultCode != Activity.RESULT_OK) {

            return
        }

        val requestCode = p1.getIntExtra(Pollworker.REQUEST_CODE, 0)
        val notification: Notification =
            p1.getParcelableExtra(Pollworker.NOTIFICATION)!!

        val notificationManager = NotificationManagerCompat.from(p0)
        notificationManager.notify(requestCode, notification)
    }
}
