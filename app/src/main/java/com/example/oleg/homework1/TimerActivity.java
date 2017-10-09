package com.example.oleg.homework1;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class TimerActivity extends AppCompatActivity {

    private final static int SECONDS_TIMER = 1000;
    private final static String KEY_TIMER = "timer";
    private final static String KEY_BUTTON = "button";
    private final static String KEY_TEXT = "text";

    private Button button;
    private long timerCount;
    private TextView textView;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        class Timer extends CountDownTimer {

            Timer(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long l) {
                timerCount = (SECONDS_TIMER - l/MainActivity.MILLIS_PER_SECOND);
                String number_text = NumberToWord.
                        convert((int) timerCount);
                textView.setText(number_text);
            }

            @Override
            public void onFinish() {
                if (textView.getText().toString().equals(NumberToWord.convert(SECONDS_TIMER))) {
                    textView.setText(NumberToWord.thousand);
                }
                timerCount = 0;
                button.setText(R.string.start);
            }
        }

        String buttonText;
        if (textView == null ) textView = (TextView) findViewById(R.id.time_text);
        if (savedInstanceState != null) {
            timerCount = savedInstanceState.getLong(KEY_TIMER);
            timer = new Timer((SECONDS_TIMER - timerCount) * MainActivity.MILLIS_PER_SECOND,
                    MainActivity.MILLIS_PER_SECOND);
            buttonText = savedInstanceState.getString(KEY_BUTTON);
            textView.setText(savedInstanceState.getString(KEY_TEXT));
            if (getResources().getString(R.string.stop).equals(buttonText)) timer.start();
        } else {
            timerCount = 0;
            buttonText = getResources().getString(R.string.start);
        }

        if (button == null) {
            button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (button.getText().toString().equals(getResources().getString(R.string.start))) {
                        button.setText(R.string.stop);
                        timer = new Timer((SECONDS_TIMER - timerCount) * MainActivity.MILLIS_PER_SECOND,
                                MainActivity.MILLIS_PER_SECOND);
                        timer.start();
                    } else {
                        timer.onFinish();
                        timer.cancel();
                    }
                }
            });
        }
        button.setText(buttonText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(KEY_TIMER, timerCount);
        outState.putString(KEY_BUTTON, button.getText().toString());
        outState.putString(KEY_TEXT, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        if (timer != null ) timer.cancel();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (timer != null && button!=null && button.getText()
                .toString().equals(getResources().getString(R.string.stop))) timer.start();
    }
}
