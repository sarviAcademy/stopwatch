package com.example.amir.stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    int seconds=0;
    boolean running=false;
    boolean wasRunnig=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState!=null)
        {
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("isRunning");
            wasRunnig=savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("isRunning",running);
        outState.putBoolean("wasRunning",wasRunnig);

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunnig=running;
        running=false;
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(wasRunnig==true)
        {
            running=true;
        }
    }


    void runTimer(){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hour = seconds / 3600;
                int min = (seconds % 3600) / 60;
                int sec = seconds % 60;
                TextView txt = findViewById(R.id.txt);
                txt.setText(String.format(Locale.getDefault(), "%d:%02d:%02d", hour, min, sec));
                if (running == true) {
                    seconds++;
                }
                handler.postDelayed(this,1000);

            }
        });

        }






    public void clickStart(View view) {
        running=true;


    }

    public void clickStop(View view) {
        running=false;
    }

    public void clickRestart(View view) {
        seconds=0;
        running=false;
    }
}
