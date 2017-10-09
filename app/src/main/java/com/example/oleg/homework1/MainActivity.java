package com.example.oleg.homework1;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final int MILLIS_PER_SECOND = 1000;
    private static final int SECONDS_TIMER = 3;
    private static final String KEY_TIMER = "timer";
    private CountDownTimer timer;
    private long timer_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            timer_count = savedInstanceState.getLong(KEY_TIMER);
        } else {
            timer_count = SECONDS_TIMER * MILLIS_PER_SECOND;
        }
        if (timer != null) { timer.cancel(); }
        timer = new CountDownTimer(timer_count, MILLIS_PER_SECOND) {
            @Override
            public void onTick(long l) {
                timer_count = l;
            }
            @Override
            public void onFinish() {
                startActivity(new Intent(MainActivity.this, TimerActivity.class));
                
            }
        };
        timer.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_TIMER, timer_count);
    }

    @Override
    protected void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (timer != null) timer.start();
    }
}
