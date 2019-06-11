package com.escodro.alkaa.ui.task.alarm.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.escodro.alkaa.R
import com.escodro.core.extension.getNotificationManager

/**
 * [NotificationChannel] to send Task notifications in Android O and above.
 */
class TaskNotificationChannel(context: Context) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.app_name)
            val description = context.getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_HIGH

            NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
                context.getNotificationManager()?.createNotificationChannel(this)
            }
        }
    }

    /**
     * Gets the [TaskNotificationChannel] id.
     *
     * @return the [TaskNotificationChannel] id
     */
    fun getChannelId() = CHANNEL_ID

    companion object {

        const val CHANNEL_ID = "task_notification_channel"
    }
}
