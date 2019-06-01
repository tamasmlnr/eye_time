package com.app.eyetime;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

public class MyJobIntentService extends JobIntentService {

    /* Give the Job a Unique Id */
    private static final int JOB_ID = 1000;

    public static void enqueueWork(Context ctx, Intent intent) {
        enqueueWork(ctx, MyJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        /* your code here */
        NotifyService notifyService = new NotifyService();
        notifyService.showNotification();


        /* reset the alarm */
        ScreenReceiver.setAlarm(false, MainActivity.reminderInterval);
        stopSelf();
    }



}