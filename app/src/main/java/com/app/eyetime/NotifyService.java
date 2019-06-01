package com.app.eyetime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

class NotifyService {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    void createNotificationChannel(NotificationManager mNotifyManager) {
        this.mNotifyManager = mNotifyManager;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Mascot Notification", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    void setNotification(int intervalInMinutes, Context appContext) {
        final NotificationCompat.Builder notifyBuilder = getNotificationBuilder(appContext);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
            }
            //TODO minutes instead of seconds - currently for testing
        }, 0, intervalInMinutes * 1000);
    }

    private NotificationCompat.Builder getNotificationBuilder(Context appContext) {
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(appContext, PRIMARY_CHANNEL_ID)
                .setContentTitle("You've been notified!")
                .setContentText("This is your notification text.")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_eye);
        return notifyBuilder;
    }

}
