package com.app.eyetime;

import android.app.NotificationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker np;
    private NotifyService notifyService = new NotifyService();
    public static int reminderInterval;
    private TextView countdown;
    private static CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        np = findViewById(R.id.numberPicker);
        countdown = findViewById(R.id.countdown);
        np.setMinValue(1);
        np.setMaxValue(30);
        NotificationManager mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notifyService.createNotificationChannel(mNotifyManager);
    }

    public void showInfoToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(),
                text,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setProperties(View view) {
        ScreenReceiver.cancelAlarm();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        reminderInterval = np.getValue();
        showInfoToast(String.format("Reminder set to %s minutes", np.getValue()));
        ScreenReceiver.setAlarm(false, reminderInterval);
        reverseTimer(np.getValue(), countdown);
    }

    public void reverseTimer(final int Seconds, final TextView tv) {

        countDownTimer = new CountDownTimer(Seconds * 1000 * 60 + 1000, 1000) {
            long timeLeft = ScreenReceiver.interval;

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (timeLeft / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tv.setText("Next reminder : " + String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
                timeLeft = timeLeft - 1000;
            }

            public void onFinish() {
                countDownTimer.start();
                timeLeft = ScreenReceiver.interval;
            }

        }.start();
    }

    public void cancelReminders(View view) {
        ScreenReceiver.cancelAlarm();
        showInfoToast("Reminders canceled!");
        countDownTimer.cancel();
        countdown.setText("No reminders set");
    }
}
