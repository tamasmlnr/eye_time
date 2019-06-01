package com.app.eyetime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        np = findViewById(R.id.numberPicker);
        np.setMinValue(1);
        np.setMaxValue(30);
    }

    public void setValues(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                String.format("Reminder set to %s minutes", np.getValue())
                , Toast.LENGTH_SHORT);
        toast.show();
    }
}

