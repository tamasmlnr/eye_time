package com.app.eyetime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {

    public static final String CUSTOM_INTENT = "com.test.intent.action.ALARM";
    public static long interval;

    @Override
    public void onReceive(Context context, Intent intent) {
         MyJobIntentService.enqueueWork(context, intent);
    }

    public static void cancelAlarm() {
        AlarmManager alarm = (AlarmManager) GlobalApplication.getAppContext().getSystemService(Context.ALARM_SERVICE);

        /* cancel any pending alarm */
        alarm.cancel(getPendingIntent());
    }

    public static void setAlarm(boolean force, int time) {
        cancelAlarm();
        AlarmManager alarm = (AlarmManager) GlobalApplication.getAppContext().getSystemService(Context.ALARM_SERVICE);

        // EVERY X MINUTES
//        long delay = (1000 * 60 * 1);
        //TODO * 60, currently in seconds
        long delay = (1000 * time);
        long when = System.currentTimeMillis();
        if (!force) {
            when += delay;
        }
        interval = delay;
        /* fire the broadcast */
        alarm.set(AlarmManager.RTC_WAKEUP, when, getPendingIntent());
    }

    private static PendingIntent getPendingIntent() {
        Intent alarmIntent = new Intent(GlobalApplication.getAppContext(), ScreenReceiver.class);
        alarmIntent.setAction(CUSTOM_INTENT);

        return PendingIntent.getBroadcast(GlobalApplication.getAppContext(), 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}