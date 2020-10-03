package com.example.stuart.eggtimer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {


    MediaPlayer mplayer;
    CountDownTimer countDownTimer;

    SeekBar timerSeekBar;
    TextView cuurentTime;
    Button goButton;
    Boolean counterIsActive = false;

    public void resetTimer()
    {
        cuurentTime.setText("0:30");
        timerSeekBar.setProgress(30);
        countDownTimer.cancel();
        goButton.setText("Go!");
        timerSeekBar.setEnabled(true);
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft)
    {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes *60;


        String secondString = Integer.toString(seconds);

        if(seconds <=9)
        {
            secondString = "0" + secondString;
        }


        cuurentTime.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void go(View view)
    {
      if(counterIsActive == false) {
          counterIsActive = true;
          timerSeekBar.setEnabled(false);
          goButton.setText("Stop");

          countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
              public void onTick(long millisecondsUntilDone) {

                  //Countdown is counting down (every second)
                  Log.i("Seconds left", String.valueOf(millisecondsUntilDone / 1000));
                  updateTimer((int) millisecondsUntilDone / 1000);


              }

              public void onFinish() {
                  resetTimer();
                  Log.i("Done!", "Countdown Timer Finished");
                  mplayer.start();
              }

          }.start();

      }
        else
      {
        resetTimer();
      }

    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mplayer = MediaPlayer.create(this, R.raw.airhorn);
        timerSeekBar = (SeekBar) findViewById(R.id.timerSeekBar);

        goButton = (Button) findViewById(R.id.goButton);

        cuurentTime = (TextView) findViewById(R.id.timer);


        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                updateTimer(progress);



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }
}
