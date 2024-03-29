package com.app.eyetime.services;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.app.eyetime.MainActivity;
import com.app.eyetime.ScreenReceiver;

public class JobIntentService extends android.support.v4.app.JobIntentService {

    private static final int JOB_ID = 1000;

    public static void enqueueWork(Context ctx, Intent intent) {
        enqueueWork(ctx, JobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
         NotifyService notifyService = new NotifyService();
        notifyService.showNotification();

        ScreenReceiver.setAlarm(false, MainActivity.reminderInterval);
        stopSelf();
    }
}
