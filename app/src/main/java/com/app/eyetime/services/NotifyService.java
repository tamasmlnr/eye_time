package com.app.eyetime.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;

import com.app.eyetime.GlobalApplication;
import com.app.eyetime.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotifyService {

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;

    public NotifyService() {
        Context context = GlobalApplication.getAppContext();
        NotificationManager mNotifyManager = (NotificationManager)
                context.getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel(mNotifyManager);
    }

    public void createNotificationChannel(NotificationManager mNotifyManager) {
        this.mNotifyManager = mNotifyManager;
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "EyeTime", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("EyeTime Notification");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    void showNotification() { ;
        mNotifyManager.notify(NOTIFICATION_ID, getNotificationBuilder().build());
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        return new NotificationCompat.Builder(GlobalApplication.getAppContext(), PRIMARY_CHANNEL_ID)
                .setContentTitle("EyeTime")
                .setContentText("Rest your eyes, look far away!")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_eye);
    }

}
