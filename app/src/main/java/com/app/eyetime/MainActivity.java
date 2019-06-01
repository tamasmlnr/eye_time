package com.app.eyetime;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker np;
    private Button button_set;
    private NotifyManager notifyManager = new NotifyManager();
    private static NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        np = findViewById(R.id.numberPicker);
        button_set = findViewById(R.id.button_set);
        np.setMinValue(1);
        np.setMaxValue(30);
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        notifyManager.createNotificationChannel(mNotifyManager);
    }

    public void setValues(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Reminder set to %s minutes", np.getValue())
                , Toast.LENGTH_SHORT);
        toast.show();
    }

    public void sendNotification(View view) {

    }

}

